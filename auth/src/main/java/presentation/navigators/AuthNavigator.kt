package presentation.navigators

import android.content.Context
import android.content.Intent
import presentation.views.ui.activities.RegistrationActivity
import presentation.views.ui.activities.RegistrationActivity.Companion.URL_EXTRA
import presentation.views.ui.activities.RestorePasswordActivity
import presentation.views.ui.activities.WebViewActivity

/**
 * Created by Gor on 10.04.2018.
 */
object AuthNavigator {
    fun navigateToRegistration(context: Context) {
        context.startActivity(Intent(context, RegistrationActivity::class.java))
    }
    fun navigateToPasswordRestore(context: Context) {
        context.startActivity(Intent(context, RestorePasswordActivity::class.java))
    }

    fun navigateToWebView(context: Context, url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(URL_EXTRA, url)
        context.startActivity(intent)
    }
}