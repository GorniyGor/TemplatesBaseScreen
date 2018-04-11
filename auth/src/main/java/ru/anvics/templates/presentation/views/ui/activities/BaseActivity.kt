package ru.anvics.templates.presentation.views.ui.activities

import android.support.v7.app.AppCompatActivity
import ru.anvics.templates.presentation.views.iviews.BaseView
import ru.anvics.templates.util.ProgressDialog

abstract class BaseActivity : AppCompatActivity(), BaseView{
    private val progress by lazy {
        ProgressDialog(this)
    }

    override fun showProgress() {
        progress.show()
    }
    override fun hideProgress() {
        progress.dismiss()
    }
}