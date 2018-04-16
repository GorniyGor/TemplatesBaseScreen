package ru.anvics.templates.data.net.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
        @field:SerializedName("token")
        val token: String = "" ) : BaseResponse()