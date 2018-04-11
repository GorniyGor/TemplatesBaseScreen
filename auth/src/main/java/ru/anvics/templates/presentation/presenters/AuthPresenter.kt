package ru.anvics.templates.presentation.presenters

import ru.anvics.templates.data.repositories.AuthRepository
import ru.anvics.templates.domain.interactors.AuthInteractor
import ru.anvics.templates.presentation.views.iviews.AuthView

class AuthPresenter : BasePresenter<AuthView>() {

    private val interactor = AuthInteractor(AuthRepository)

    //--Здесь рабочие методы

    fun signIn(pair: Pair<String, String>) {
        interactor.signIn(pair)
                .doOnSubscribe { view?.showProgress() }
                .doOnEvent { view?.hideProgress() }
                .subscribe(
                        {
                            view?.onLoginSuccess()
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