package ru.anvics.templates.presentation.views.ui.activities

import android.os.Bundle
import ru.anvics.templates.R
import ru.anvics.templates.presentation.presenters.TemplatePresenter
import ru.anvics.templates.presentation.views.iviews.TemplateView
import ru.anvics.templates.util.ProgressDialog

class TemplateActivity : BaseActivity(), TemplateView {
//    override fun setText(parameter: TemplateEntity) {
//        tvEntityTitle.text = parameter.text
//    }

    private var presenter = TemplatePresenter()
    /*v*/
    private var progress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template)
        presenter.attachView(this)
        /*v*/
        progress = ProgressDialog(this)
    }


    override fun error(message: String) {
        //TODO("not implemented")
    }
}
