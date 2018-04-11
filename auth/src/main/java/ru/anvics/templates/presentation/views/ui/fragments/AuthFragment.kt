package ru.anvics.templates.presentation.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.anvics.templates.R
import ru.anvics.templates.presentation.presenters.AuthPresenter
import ru.anvics.templates.presentation.views.iviews.AuthView
import ru.anvics.templates.util.ProgressDialog

class AuthFragment : BaseFragment(), AuthView {
    override fun onLoginSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
//    override fun setText(parameter: AuthEntity) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    private val presenter = AuthPresenter()
    /*v*/
    private var progress: ProgressDialog? = null

    companion object {
        @JvmStatic
        fun newInstance(): AuthFragment {
            val fragmentLogin = AuthFragment()
            val args = Bundle()
            fragmentLogin.arguments = args
            return fragmentLogin
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_auth, container, false)
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