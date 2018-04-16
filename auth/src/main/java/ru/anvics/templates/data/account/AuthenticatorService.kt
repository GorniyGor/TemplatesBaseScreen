package ru.anvics.templates.data.account

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by Gor on 13.04.2018.
 */
class AuthenticatorService: Service() {
    override fun onBind(intent: Intent?): IBinder = Authenticator(this).iBinder
}