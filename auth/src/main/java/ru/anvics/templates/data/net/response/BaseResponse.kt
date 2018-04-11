package ru.anvics.templates.data.net.response

import com.google.gson.annotations.SerializedName

open class BaseResponse(
        @field:SerializedName("result")
        val result: String = ""
)
