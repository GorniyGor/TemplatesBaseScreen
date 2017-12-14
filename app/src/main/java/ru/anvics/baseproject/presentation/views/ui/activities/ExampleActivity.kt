package ru.anvics.baseproject.presentation.views.ui.activities

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.anvics.baseproject.R
import ru.anvics.baseproject.domain.ExampleEntity
import ru.anvics.baseproject.presentation.presenters.ExamplePresenter
import ru.anvics.baseproject.presentation.views.ExampleView

class ExampleActivity : BaseActivity(), ExampleView {
    private val presenter = ExamplePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)

        btnGetEntity.setOnClickListener {
            presenter.getEntityById(100)
        }
    }

    override fun setEntity(entity: ExampleEntity) {
        tvEntityId.text = entity.id.toString()
        tvEntityTitle.text = entity.title
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.destroy()
    }
}
