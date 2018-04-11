package ru.anvics.templates.util

/**
 * Created by Gor on 11.04.2018.
 */
data class Validator(
        var isValid: Boolean = false,
        var message: String = "Неверный формат"
)