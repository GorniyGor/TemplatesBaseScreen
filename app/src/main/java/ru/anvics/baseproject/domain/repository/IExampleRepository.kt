package ru.anvics.baseproject.domain.repository

import io.reactivex.Observable
import ru.anvics.baseproject.domain.ExampleEntity

interface IExampleRepository {
    fun getEntityById(id: Long): Observable<ExampleEntity>
}