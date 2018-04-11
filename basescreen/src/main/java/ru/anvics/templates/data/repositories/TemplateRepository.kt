package ru.anvics.templates.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.anvics.templates.domain.entities.TemplateEntity
import ru.anvics.templates.domain.repository.ITemplateRepository

object TemplateRepository : ITemplateRepository {

    //--Здесь рабочие методы

    override fun something3(): Observable<TemplateEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun something2(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun something(): Single<TemplateEntity> {
        return Single.just(TemplateEntity("Бла-бла-бла"))
    }


}