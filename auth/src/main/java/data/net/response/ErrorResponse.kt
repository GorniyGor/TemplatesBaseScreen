package com.impacto.vms.data.net.response

import com.google.gson.annotations.SerializedName
import data.net.entities.ErrorPOJO
import data.net.response.BaseResponse

class ErrorResponse(
        @field:SerializedName("errors")
        val errors: List<ErrorPOJO> = emptyList()
) : BaseResponse()
