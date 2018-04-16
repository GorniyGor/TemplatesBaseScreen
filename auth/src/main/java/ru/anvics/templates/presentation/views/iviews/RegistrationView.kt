package ru.anvics.templates.presentation.views.iviews

import ru.anvics.templates.util.Throwables.RegistrationThrowable

interface RegistrationView : BaseView {
    fun onInvalidFields(throwable: RegistrationThrowable)
    fun onRegisteredSuccess()

    //--Здесь рабочие методы
//    fun setText(parameter: AuthEntity)
}