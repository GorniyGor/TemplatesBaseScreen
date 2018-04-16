package ru.anvics.templates.data.account

import android.accounts.Account
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.content.Context
import ru.anvics.templates.R

/**
 * Created by Gor on 12.04.2018.
 */

@SuppressLint("MissingPermission")
object AccountManagerHolder {
    private var manager: AccountManager? = null
    private var accountType: String = ""
    private var tokenType: String = ""

    fun init(context: Context){
        manager = AccountManager.get(context)
        accountType = context.resources.getString(R.string.account_type)
        tokenType = context.resources.getString(R.string.auth_token_type)
    }

    fun getAccount(): Account{
        checkManager()

        return manager!!.getAccountsByType(accountType).first()
    }

    fun setAccount(login: String, password: String){
        checkManager()

        val account = Account(login, accountType)
        manager!!.addAccountExplicitly(account, password, null)
    }

    fun removeAccount(){
        checkManager()

        val accounts = manager!!.getAccountsByType(accountType)
        for (account in accounts)
        manager!!.removeAccount(account, null, null)
    }

    fun hasAccount(): Boolean {
        checkManager()


        val accounts = manager!!.getAccountsByType(accountType)
        return accounts.isNotEmpty()
    }

    fun setAuthToken(authToken: String){
        checkManager()

        val account = getAccount()
        manager!!.setAuthToken(account, tokenType, authToken)
    }

    fun getAuthToken(): String{
        checkManager()

        val account = getAccount()
        return manager!!.blockingGetAuthToken(account, tokenType, false)
    }

    fun getPassword(): String {
        checkManager()

        val account = getAccount()
        return manager!!.getPassword(account)
    }

    private fun checkManager(){
        if(manager == null) throw IllegalStateException("AccountManagerHolder must be initial")
    }
}