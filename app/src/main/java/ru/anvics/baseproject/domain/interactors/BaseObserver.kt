package ru.anvics.baseproject.domain.interactors

import io.reactivex.observers.DisposableObserver

abstract class BaseObserver<T> : DisposableObserver<T>() {
    override fun onError(e: Throwable) {}

    override fun onNext(t: T) {}

    override fun onComplete() {}
}