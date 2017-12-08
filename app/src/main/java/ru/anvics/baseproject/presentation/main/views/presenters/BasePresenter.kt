package ru.anvics.baseproject.presentation.main.views.presenters

import ru.anvics.baseproject.presentation.main.views.BaseView

interface BasePresenter<in V : BaseView> {
    fun attachView(view: V)
    fun destroy()
}