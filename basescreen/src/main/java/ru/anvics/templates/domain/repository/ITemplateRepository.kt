package ru.anvics.templates.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.anvics.templates.domain.entities.TemplateEntity

interface ITemplateRepository {
    //--Здесь рабочие методы
    /*v*/
    fun something(): Single<TemplateEntity>
    fun something2(): Completable
    fun something3(): Observable<TemplateEntity>
}