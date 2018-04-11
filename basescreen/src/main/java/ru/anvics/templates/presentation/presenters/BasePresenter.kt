package ru.anvics.templates.presentation.presenters

import ru.anvics.templates.presentation.views.iviews.BaseView

abstract class BasePresenter<V : BaseView> {
    protected var view: V? = null

    open fun attachView(view: V) {
        this.view = view
    }

    open fun destroy() {}
}