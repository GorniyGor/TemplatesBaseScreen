package ru.anvics.templates.data.net

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ru.anvics.templates.data.net.request.RegistrationRequest
import ru.anvics.templates.data.net.response.AuthResponse
import ru.anvics.templates.data.net.response.BaseResponse

/**
 * Created by Gor on 10.04.2018.
 */
interface PublicApi {
    @FormUrlEncoded
    @POST("authorization")
    fun auth(
            @Field("email") email: String,
            @Field("password") password: String): Single<AuthResponse>

    @FormUrlEncoded
    @POST("registration")
    fun registration(@Body body: RegistrationRequest): Single<AuthResponse>

    @FormUrlEncoded
    @POST("restorePassword")
    fun restorePassword(@Field("email") email: String): Single<BaseResponse>
}