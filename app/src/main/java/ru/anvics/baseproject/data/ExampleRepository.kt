package ru.anvics.baseproject.data

import io.reactivex.Observable
import ru.anvics.baseproject.domain.ExampleEntity
import ru.anvics.baseproject.domain.repository.IExampleRepository

object ExampleRepository : IExampleRepository {
    override fun getEntityById(id: Long): Observable<ExampleEntity> {
        return Observable.just(ExampleEntity(id, "Title"))
    }
}