package data.net.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Gor on 10.04.2018.
 */
class ErrorPOJO(
        @field:SerializedName("code")
        val code: Int = 0,

        @field:SerializedName("message")
        val message: String = ""
)