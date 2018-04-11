package ru.anvics.templates.util.Throwables

import ru.anvics.templates.util.Validator

/**
 * Created by Gor on 11.04.2018.
 */
class SignInThrowable : Throwable(){
    var login = Validator()
    var password = Validator()
}