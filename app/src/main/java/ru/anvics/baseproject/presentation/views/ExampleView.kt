package ru.anvics.baseproject.presentation.views

import ru.anvics.baseproject.domain.ExampleEntity

interface ExampleView : BaseView {
    fun setEntity(entity: ExampleEntity)
}