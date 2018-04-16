package ru.anvics.templates.data.net

import android.util.Log
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.anvics.templates.com.impacto.vms.data.net.response.ErrorResponse
import ru.anvics.templates.data.account.AccountManagerHolder
import ru.anvics.templates.data.net.response.BaseResponse
import ru.anvics.templates.data.repositories.AuthRepository
import java.util.concurrent.TimeUnit

/**
 * Created by Gor on 10.04.2018.
 */
object ApiHolder {
    private val URL_BASE = "https://ru.omoloke.com/api/v2/"
    private val OK_CODE = 2
    private val FAIL_CODE = 4
    private val CODE_TOKEN_ERROR = 401

    private val loggingInterceptor = Interceptor({
        val request = it.request()
        val response = it.proceed(request)
        val body = response.body()!!.string()
        response.newBuilder()
                .body(ResponseBody.create(response.body()!!.contentType(), body))
                .build()
    })

    private val errorHandler = Interceptor({
        val request = it.request()
        val response = it.proceed(request)
        val body = response.body()!!.string()
        val parser = Gson()
        Log.i("MyTag", body)
        val res = parser.fromJson(body, BaseResponse::class.java)

        if (res.result == "error") {
            val error = parser.fromJson(body, ErrorResponse::class.java)
            if (error.errors.first().code == CODE_TOKEN_ERROR) {
                if (updateToken() == OK_CODE) {
                    val builder = request.newBuilder()
                            .removeHeader("X-Auth-Token")
                            .addHeader("X-Auth-Token", AccountManagerHolder.getAuthToken())
                            .build()
                    it.proceed(builder)
                } else {
                    throw Throwable(error.errors.first().message)
                }
                it.proceed(request)
            } else {
                throw Throwable(error.errors.first().message)
            }
        } else {
            response.newBuilder()
                    .body(ResponseBody.create(response.body()!!.contentType(), body))
                    .build()
        }
    })

    private fun updateToken(): Int {
        return if (AccountManagerHolder.hasAccount()) {
            val loginData = Pair<String, String>(
                    AccountManagerHolder.getAccount().name,
                    AccountManagerHolder.getPassword())
            val refreshResponse = AuthRepository.refreshToken(loginData).blockingGet()
            if (refreshResponse.result == "success") {
                AccountManagerHolder.setAuthToken(refreshResponse.token)
                OK_CODE
            } else {
                AccountManagerHolder.setAuthToken("")
                FAIL_CODE
            }
        } else {
            FAIL_CODE
        }
    }

    val publicApi: PublicApi by lazy {
        val client = OkHttpClient().newBuilder()
                .addInterceptor(errorHandler)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()

        Retrofit.Builder()
                .client(client)
                .baseUrl(URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PublicApi::class.java)
    }

    val privateApi: PrivateApi by lazy {
        val client = OkHttpClient()
                .newBuilder()
                .addInterceptor {
                    val request = it.request()
                    val build = request.newBuilder()
                    val newRequest = build.addHeader("X-Auth-Token",
                            AccountManagerHolder.getAuthToken()).build()
                    it.proceed(newRequest)
                }
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(errorHandler)
                .addInterceptor(loggingInterceptor)
                .build()

        Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PrivateApi::class.java)
    }
}