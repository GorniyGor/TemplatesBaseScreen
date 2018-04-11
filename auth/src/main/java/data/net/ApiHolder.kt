package data.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

    /*private val errorHandler = Interceptor({
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
                            .addHeader("X-Auth-Token", AuthPref.token)
                            .build()
                    it.proceed(builder)
                } else {
                    throw Throwable(error.errors.first().message)
                }
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
        val account = AccountManagerHolder.get().getAccountsByType(AccountManagerHolder.accountType)
        return if (account.isNotEmpty()) {
            val signIn = account.first().name
            val password = AccountManagerHolder.get().getPassword(account.first())
            val blockingFirst = publicApi.auth(signIn, password).blockingFirst()
            if (blockingFirst.result == "success") {
                AuthPref.token = blockingFirst.token
                OK_CODE
            } else {
                AuthPref.token = ""
                FAIL_CODE
            }
        } else {
            FAIL_CODE
        }
    }*/

    val publicApi: PublicApi by lazy {
        val client = OkHttpClient().newBuilder()
//                .addInterceptor(errorHandler)
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
}