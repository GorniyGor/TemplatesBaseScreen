package ru.anvics.baseproject.presentation.presenters

import ru.anvics.baseproject.presentation.views.BaseView

interface BasePresenter<in V : BaseView> {
    fun attachView(view: V)
    fun destroy()
}