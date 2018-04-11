package ru.anvics.templates.presentation.presenters

import ru.anvics.templates.data.repositories.AuthRepository
import ru.anvics.templates.domain.entities.RegistrationInfo
import ru.anvics.templates.domain.interactors.AuthInteractor
import ru.anvics.templates.presentation.views.iviews.RegistrationView

class RegistrationPresenter : BasePresenter<RegistrationView>() {

    private val interactor = AuthInteractor(AuthRepository)

    //--Здесь рабочие методы

    fun register(data: RegistrationInfo) {
        interactor.register(data)
                .doOnSubscribe { view?.showProgress() }
                .doOnEvent { view?.hideProgress() }
                .subscribe(
                        {
                            view?.onRegisteredSuccess()
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