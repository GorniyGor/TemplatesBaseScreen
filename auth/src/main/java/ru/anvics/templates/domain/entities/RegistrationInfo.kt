package ru.anvics.templates.domain.entities

/**
 * Created by Gor on 10.04.2018.
 */
data class RegistrationInfo(
        /*v*/
        val login: String,
        val email: String,
        val phone: String,
        val name: String,
        val password: String,
        val confirmingPassword: String
)