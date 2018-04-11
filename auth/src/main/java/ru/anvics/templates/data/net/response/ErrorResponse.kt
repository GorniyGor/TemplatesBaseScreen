package ru.anvics.templates.com.impacto.vms.data.net.response

import com.google.gson.annotations.SerializedName
import ru.anvics.templates.data.net.entities.ErrorPOJO
import ru.anvics.templates.data.net.response.BaseResponse

class ErrorResponse(
        @field:SerializedName("errors")
        val errors: List<ErrorPOJO> = emptyList()
) : BaseResponse()
