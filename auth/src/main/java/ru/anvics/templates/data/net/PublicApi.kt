package ru.anvics.templates.data.net

import ru.anvics.templates.data.net.request.RegistrationRequest
import ru.anvics.templates.data.net.response.AuthResponse
import ru.anvics.templates.data.net.response.BaseResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by Gor on 10.04.2018.
 */
interface PublicApi {
    @FormUrlEncoded
    @POST("authorization")
    fun auth(
            @Field("email") email: String,
            @Field("password") password: String
    ): Observable<AuthResponse>

    @FormUrlEncoded
    @POST("registration")
    fun registration(@Body body: RegistrationRequest): Single<AuthResponse>

    @GET("forgotPassword")
    fun forgotPassword(
            @Query("email") email: String
    ): Observable<BaseResponse>
}