package ru.anvics.templates.data.net.request

/**
 * Created by Gor on 10.04.2018.
 */
data class RegistrationRequest(
        //По дефолту те же SerializedName, что и у нашей entity
        val login: String,
        val email: String,
        val phone: String,
        val name: String,
        val password: String,
        val confirmingPassword: String
)