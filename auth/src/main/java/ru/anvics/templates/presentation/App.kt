package ru.anvics.templates.presentation

import android.app.Application
import com.chibatching.kotpref.Kotpref
import ru.anvics.templates.data.account.AccountManagerHolder

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(applicationContext)
        AccountManagerHolder.init(this)
    }
}