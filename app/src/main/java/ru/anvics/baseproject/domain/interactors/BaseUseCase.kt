package ru.anvics.baseproject.domain.interactors

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<T>(
        private val threadExecutor: Scheduler = Schedulers.io(),
        private val postExecutionThread: Scheduler = AndroidSchedulers.mainThread()
) {
    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(): Observable<T>

    fun execute(observer: DisposableObserver<T>) {
        val observable = buildUseCaseObservable()
                .subscribeOn(threadExecutor)
                .observeOn(postExecutionThread)
        disposables.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}