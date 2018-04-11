package ru.anvics.templates.presentation.presenters

import data.repositories.AuthRepository
import ru.anvics.templates.domain.interactors.AuthInteractor
import ru.anvics.templates.presentation.views.iviews.RestorePasswordView

class RestorePasswordPresenter : BasePresenter<RestorePasswordView>() {

    private val interactor = AuthInteractor(AuthRepository)

    //--Здесь рабочие методы

    fun restore(contact: String) {
        interactor.restorePassword(contact)
                .doOnSubscribe { view?.showProgress() }
                .doOnEvent { view?.hideProgress() }
                .subscribe(
                        {
                            view?.success()
                        },
                        {
                            view?.error(it.message.toString())
                        }
                )
    }

    override fun destroy() {
        view = null
    }
}