package ru.anvics.templates.presentation.presenters

import ru.anvics.templates.data.repositories.AuthRepository
import ru.anvics.templates.domain.interactors.AuthInteractor
import ru.anvics.templates.presentation.views.iviews.RestorePasswordView
import ru.anvics.templates.util.Throwables.RestorePasswordThrowable

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
                            if(it is RestorePasswordThrowable) view?.onInvalidFields(it)
                            else view?.error(it.message.toString())
                        }
                )
    }

    override fun destroy() {
        view = null
    }
}