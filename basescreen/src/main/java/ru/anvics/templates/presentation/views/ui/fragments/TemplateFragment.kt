package ru.anvics.templates.presentation.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.anvics.templates.R
import ru.anvics.templates.presentation.presenters.TemplatePresenter
import ru.anvics.templates.presentation.views.iviews.TemplateView
import ru.anvics.templates.util.ProgressDialog

class TemplateFragment : BaseFragment(), TemplateView {
//    override fun setText(parameter: TemplateEntity) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    private val presenter = TemplatePresenter()
    /*v*/
    private var progress: ProgressDialog? = null

    companion object {
        @JvmStatic
        fun newInstance(): TemplateFragment {
            val fragmentLogin = TemplateFragment()
            val args = Bundle()
            fragmentLogin.arguments = args
            return fragmentLogin
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_template, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*v*/
        progress = ProgressDialog(activity!!)

    }

    override fun error(message: String) {
        //TODO("not implemented")
    }
}