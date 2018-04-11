package presentation.views.ui.activities

import android.os.Bundle
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_restore_password.*
import ru.anvics.templates.auth.R
import ru.anvics.templates.presentation.presenters.RestorePasswordPresenter
import ru.anvics.templates.presentation.views.iviews.RestorePasswordView
import util.ProgressDialog
import util.Validators

/**
 * Created by Gor on 10.04.2018.
 */
class RestorePasswordActivity : BaseActivity(), RestorePasswordView {

    private var presenter = RestorePasswordPresenter()
    private var progress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restore_password)
        presenter.attachView(this)
        progress = ProgressDialog(this)

        /*v*/
        val contactObservable = RxTextView.textChanges(etContact)
                .map { it.isNotEmpty() && /*v*/Validators.isEmail(it) }

        contactObservable.subscribe { if(it) btnRestore.isEnabled = it }

        btnRestore.setOnClickListener {
            presenter.restore(etContact.text.toString())
        }
    }


    override fun success() {
        //TODO("not implemented")
        onBackPressed()
    }

    override fun error(message: String) {
        //TODO("not implemented")
    }
}