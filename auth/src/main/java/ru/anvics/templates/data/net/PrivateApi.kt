package ru.anvics.templates.data.net

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import ru.anvics.templates.data.net.response.BaseResponse

/**
 * Created by Gor on 13.04.2018.
 */
interface PrivateApi {
    @POST("user/logout")
    fun logout(): Single<BaseResponse>

    @GET("user")
    fun getUser(): Single<BaseResponse>
}