package ru.anvics.templates.util

import android.util.Patterns

/**
 * Created by Gor on 10.04.2018.
 */
object Validators {

    fun isPhone(s: CharSequence): Boolean = Patterns.PHONE.matcher(s).matches()

    fun isEmail(s: CharSequence): Boolean = Patterns.EMAIL_ADDRESS.matcher(s).matches()

    fun isName(s: CharSequence): Boolean = s.matches(Regex("^([A-Za-z]+)(\\s[A-Za-z]+)*\\s?$"))

    fun isLogin(s: CharSequence): Boolean = s.matches(Regex("^([A-Za-z0-9]+)(\\s[A-Za-z0-9]+)*\\s?$"))

    fun isConfirmPassword(s1: CharSequence, s2: CharSequence): Boolean = (s1 == s2)

}