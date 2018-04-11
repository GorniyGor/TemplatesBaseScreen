package ru.anvics.templates.presentation.presenters

import android.os.Handler
import io.reactivex.disposables.CompositeDisposable
import ru.anvics.templates.data.repositories.TemplateRepository
import ru.anvics.templates.domain.interactors.TemplateInteractor
import ru.anvics.templates.presentation.views.iviews.TemplateView

class TemplatePresenter : BasePresenter<TemplateView>() {

    private val interactor = TemplateInteractor(TemplateRepository)
    /*v*/
    private val disposables = CompositeDisposable()


    //--Здесь рабочие методы
    /*v*/
    fun doSomethingSingl() {
        interactor.somethingUC()
                .doOnSubscribe {
                    view?.showProgress()
                }
                .doOnEvent { _, _ ->
                    Handler().postDelayed({
                        view?.hideProgress()
                    }, 2000)
                     }
                .subscribe(
                        {
//                            view?.setText(it)
                            //TODO("not implemented")
                        },
                        {
                            view?.error(it.message.toString())
                        }
                )
    }

    fun doSomethingComp() {
        interactor.somethingUC2()
                .doOnSubscribe { view?.showProgress() }
                .doOnEvent { view?.hideProgress() }
                .subscribe(
                        {
                            //TODO("not implemented")
                        },
                        {
                            view?.error(it.message.toString())
                        }
                )
    }

    //!!!
    fun doSomethingObs() {
        disposables.add(
                interactor.somethingUC3()
                .doOnSubscribe { view?.showProgress() }
                .doOnEach { view?.hideProgress() }
                .subscribe(
                        {
                            //TODO("not implemented")
                        },
                        {
                            view?.error(it.message.toString())
                        }
                )
        )
    }

    override fun destroy() {
        view = null
        /*v*/
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}