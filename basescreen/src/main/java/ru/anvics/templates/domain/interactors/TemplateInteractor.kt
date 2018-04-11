package ru.anvics.templates.domain.interactors

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.anvics.templates.domain.entities.TemplateEntity
import ru.anvics.templates.domain.repository.ITemplateRepository

/**
 * Created by Gor on 04.04.2018.
 */
class TemplateInteractor(private val repository: ITemplateRepository){

    //--Здесь рабочие методы
    /*v*/
    fun somethingUC(): Single<TemplateEntity> {
        return repository.something()
    }

    fun somethingUC2(): Completable {
        return repository.something2()
    }
    fun somethingUC3(): Observable<TemplateEntity> {
        return repository.something3()
    }

}