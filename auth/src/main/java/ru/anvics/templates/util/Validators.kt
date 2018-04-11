package ru.anvics.templates.util

import android.util.Patterns

/**
 * Created by Gor on 10.04.2018.
 */
object Validators {

    fun isValid(vararg param: Validator): Boolean = param.all { it.isValid }

    fun phone(s: CharSequence): Validator =
            Validator(Patterns.PHONE.matcher(s).matches(),
                    "Неверный формат телефона")

    fun email(s: CharSequence): Validator =
            Validator(Patterns.EMAIL_ADDRESS.matcher(s).matches(),
                    "Неверный формат почты")

    fun name(s: CharSequence): Validator =
            Validator(s.matches(Regex("^([A-Za-z]+)(\\s[A-Za-z]+)*\\s?$")),
                    "Неверный формат имени")

    fun login(s: CharSequence): Validator =
            Validator(s.matches(Regex("^([A-Za-z0-9]+)(\\s[A-Za-z0-9]+)*\\s?$")),
                    "Неверный формат логина")

    fun password(s: CharSequence): Validator =
            Validator(s.matches(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$")),
                    "# a digit must occur at least once\n" +
                            "# a lower case letter must occur at least once\n" +
                            "# an upper case letter must occur at least once\n" +
                            "# a special character must occur at least once you can replace with your special characters\n" +
                            "# no whitespace allowed in the entire string\n" +
                            "# anything, at least six places though")

    fun confirmPassword(s1: CharSequence, s2: CharSequence): Validator =
            Validator((s1 == s2), "Пароли не совпадают")

}