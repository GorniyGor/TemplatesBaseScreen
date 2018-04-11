package ru.anvics.templates.util.Throwables

import ru.anvics.templates.util.Validator

/**
 * Created by Gor on 11.04.2018.
 */
class RegistrationThrowable: Throwable(){
    var login = Validator()
    var email = Validator()
    var phone = Validator()
    var name = Validator()
    var confirmingPassword = Validator()
}