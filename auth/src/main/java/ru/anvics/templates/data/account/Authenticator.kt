package ru.anvics.templates.data.account

import android.accounts.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import ru.anvics.templates.R
import ru.anvics.templates.data.repositories.AuthRepository
import ru.anvics.templates.presentation.views.ui.activities.AuthActivity

/**
 * Created by Gor on 13.04.2018.
 */
class Authenticator(private val context: Context): AbstractAccountAuthenticator(context) {

    @Throws(NetworkErrorException::class)
    override fun addAccount(response: AccountAuthenticatorResponse?, accountType: String?,
                            authTokenType: String?, requiredFeatures: Array<out String>?,
                            options: Bundle?): Bundle {
        val manager = AccountManager.get(context)
        val accounts = manager.getAccountsByType(context.resources.getString(R.string.account_type))

        if(accounts.isNotEmpty()){
            val error = "Можно добавить лишь один аккаунт"
            val bundle = Bundle()
            bundle.putInt(AccountManager.KEY_ERROR_CODE, 1)
            bundle.putString(AccountManager.KEY_ERROR_MESSAGE, error)

            Handler().post {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }

            return bundle
        }

        val intent = Intent(context, AuthActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle
    }


    @Throws(NetworkErrorException::class)
    override fun getAuthToken(response: AccountAuthenticatorResponse?, account: Account?,
                              authTokenType: String?, options: Bundle?): Bundle {
        val manager = AccountManager.get(context)
        var token = manager.peekAuthToken(account, authTokenType)


        //получение токена (token) от сервера через авторизацию
        if(token.isNullOrEmpty()){
            val password = manager.getPassword(account)
            if(!password.isNullOrEmpty()) {
                val loginData = Pair<String, String>(account!!.name, password)
                token = AuthRepository.refreshToken(loginData).map { it.token }.blockingGet()
            }
        }

        if (token.isNotEmpty()) {
            val bundle = Bundle()
            bundle.putString(AccountManager.KEY_ACCOUNT_NAME, account?.name)
            bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, account?.type)
            bundle.putString(AccountManager.KEY_AUTHTOKEN, token)
            return bundle
        }

        val intent = Intent(context, AuthActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle
    }

    @Throws(NetworkErrorException::class)
    override fun getAuthTokenLabel(authTokenType: String?): String { throw UnsupportedOperationException() }
    @Throws(NetworkErrorException::class)
    override fun confirmCredentials(response: AccountAuthenticatorResponse?, account: Account?, options: Bundle?): Bundle {
        throw UnsupportedOperationException() }
    @Throws(NetworkErrorException::class)
    override fun updateCredentials(response: AccountAuthenticatorResponse?, account: Account?, authTokenType: String?, options: Bundle?): Bundle {
        throw UnsupportedOperationException() }
    @Throws(NetworkErrorException::class)
    override fun hasFeatures(response: AccountAuthenticatorResponse?, account: Account?, features: Array<out String>?): Bundle {
        throw UnsupportedOperationException() }
    @Throws(NetworkErrorException::class)
    override fun editProperties(response: AccountAuthenticatorResponse?, accountType: String?): Bundle {
        throw UnsupportedOperationException()}
}