package ru.anvics.templates.presentation.views.iviews

import ru.anvics.templates.util.Throwables.SignInThrowable

interface AuthView : BaseView {
    fun onInvalidFields(throwable: SignInThrowable)
    fun onLoginSuccess()

    //--Здесь рабочие методы
//    fun setText(parameter: AuthEntity)
}