package ru.anvics.templates.presentation.views.iviews

import ru.anvics.templates.util.Throwables.RestorePasswordThrowable

interface RestorePasswordView : BaseView {
    fun onInvalidFields(throwable: RestorePasswordThrowable)
    fun success()

    //--Здесь рабочие методы
}