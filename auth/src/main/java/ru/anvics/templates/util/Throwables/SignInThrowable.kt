package ru.anvics.templates.util.Throwables

import ru.anvics.templates.util.Verifiable

/**
 * Created by Gor on 11.04.2018.
 */
class SignInThrowable : ValidatingThrowable(){
    override fun toArray(): Array<Verifiable> {
        return arrayOf(login, password)
    }

    var login = Verifiable()
    var password = Verifiable()
}