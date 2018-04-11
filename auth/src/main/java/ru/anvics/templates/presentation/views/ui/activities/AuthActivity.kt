package ru.anvics.templates.presentation.views.ui.activities

import android.os.Bundle
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_auth.*
import ru.anvics.templates.presentation.navigators.AuthNavigator
import ru.anvics.templates.R
import ru.anvics.templates.presentation.presenters.AuthPresenter
import ru.anvics.templates.presentation.views.iviews.AuthView
import ru.anvics.templates.util.ProgressDialog
import ru.anvics.templates.util.Validators

class AuthActivity : BaseActivity(), AuthView {

    private var presenter = AuthPresenter()
    private var progress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        presenter.attachView(this)
        progress = ProgressDialog(this)

        btnSignIn.setOnClickListener {
            presenter.signIn(Pair(etLogin.text.toString(), etPassword.text.toString()))
        }

        btnRegister.setOnClickListener {
            AuthNavigator.navigateToRegistration(this)
        }

        btnForgotPassword.setOnClickListener {
            AuthNavigator.navigateToPasswordRestore(this)
        }

        val loginObserver = RxTextView.textChanges(etLogin)
                .map { it.isNotEmpty() && Validators.isLogin(it) } //Нужно кидать какое-то сообщение об ошибке
        val passwordObserver = RxTextView.textChanges(etPassword)
                .map { it.isNotEmpty() }

        Observable.combineLatest(
                loginObserver,
                passwordObserver,
                BiFunction<Boolean, Boolean, Boolean> { a, b -> a && b }
        ).subscribe {
            btnSignIn.isEnabled = it
        }
    }


    override fun onLoginSuccess() {
        //TODO("not implemented")
        finish()
    }

    override fun error(message: String) {
        //TODO("not implemented")
    }
}
