package ru.anvics.baseproject.domain.interactors

import io.reactivex.Observable
import ru.anvics.baseproject.domain.ExampleEntity
import ru.anvics.baseproject.domain.repository.IExampleRepository

class GetEntityById(
        private val repository: IExampleRepository
) : BaseUseCase<ExampleEntity>() {
    private var id: Long = 0

    fun setId(id: Long) {
        this.id = id
    }

    override fun buildUseCaseObservable(): Observable<ExampleEntity> {
        return repository.getEntityById(id)
    }
}