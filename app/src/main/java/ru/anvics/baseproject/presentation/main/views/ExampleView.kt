package ru.anvics.baseproject.presentation.main.views

import ru.anvics.baseproject.domain.ExampleEntity

interface ExampleView : BaseView {
    fun setEntity(entity: ExampleEntity)
}