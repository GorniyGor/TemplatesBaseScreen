package ru.anvics.templates.presentation.views.ui.activities

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_registration.*
import ru.anvics.templates.R
import ru.anvics.templates.domain.entities.RegistrationInfo
import ru.anvics.templates.presentation.navigators.AuthNavigator
import ru.anvics.templates.presentation.presenters.RegistrationPresenter
import ru.anvics.templates.presentation.views.iviews.RegistrationView
import ru.anvics.templates.util.ProgressDialog

/**
 * Created by Gor on 10.04.2018.
 */
class RegistrationActivity : BaseActivity(), RegistrationView {

    private var presenter = RegistrationPresenter()
    private var progress: ProgressDialog? = null

    companion object {
        const val URL_EXTRA = "extra_url"
        const val TERM_URL = "http://google.com"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        presenter.attachView(this)
        progress = ProgressDialog(this)

        /*v*/
        //--Делаем слово "Условия" ссылкой
        makeLink(/*v*/checkboxTerm, "Условия", this)
        //---

        /*v*/
        val loginObservable = RxTextView.textChanges(etLogin)
                .map { it.isNotEmpty() }

        Observable.combineLatest(
                arrayOf(/*v*/loginObservable),
                { it.all { it is Boolean && it } }
        ).subscribe { btnRegister.isEnabled = it }

        btnRegister.setOnClickListener {
            presenter.register(
                    RegistrationInfo(
                            /*v*/
                            etLogin.text.toString(),
                            etEmail.text.toString(),
                            etPhone.text.toString(),
                            etName.text.toString(),
                            etPassword.text.toString(),
                            etConfirmingPassword.text.toString()
                    )
            )
        }
    }


    override fun onRegisteredSuccess() {
        //TODO("not implemented")
        finish()
    }

    override fun error(message: String) {
        //TODO("not implemented")
    }

    fun makeLink(view: TextView, linkText: String, context: Context){
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                AuthNavigator.navigateToWebView(context, TERM_URL)
            }
        }
        val spannableString = SpannableString(view.text)
        val startIndexOfLink = view.text.indexOf(linkText)
        spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + linkText.length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)

        view.movementMethod = LinkMovementMethod.getInstance()
        view.setText(spannableString, TextView.BufferType.SPANNABLE)
    }
}