package ru.anvics.templates.presentation.views.iviews


interface BaseView {
    fun error(message: String)
    fun showProgress()
    fun hideProgress()
}