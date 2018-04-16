package ru.anvics.templates.util.Throwables

import ru.anvics.templates.util.Verifiable

/**
 * Created by Gor on 11.04.2018.
 */
class RegistrationThrowable: ValidatingThrowable(){

    var login = Verifiable()
    var email = Verifiable()
    var phone = Verifiable()
    var name = Verifiable()
    var confirmingPassword = Verifiable()

    override fun toArray(): Array<Verifiable> {
        return arrayOf(
                login, email, phone, name, confirmingPassword
        )
    }
}