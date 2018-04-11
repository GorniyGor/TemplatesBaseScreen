package ru.anvics.templates.domain.interactors

import io.reactivex.Completable
import ru.anvics.templates.domain.entities.RegistrationInfo
import ru.anvics.templates.domain.repository.IAuthRepository
import ru.anvics.templates.util.Validators

/**
 * Created by Gor on 04.04.2018.
 */
class AuthInteractor(private val repository: IAuthRepository){

    //--Здесь рабочие методы

    fun signIn(pair: Pair<String, String>): Completable {
        return signInValidate(pair).andThen(repository.signIn(pair))
    }

    fun register(data: RegistrationInfo): Completable {
        return registrationValidate(data).andThen(repository.register(data))
    }

    fun restorePassword(contact: String): Completable {
        return repository.restorePassword(contact)
    }


    //--Стрёмно бросать обратно список неправильных полей
    /*private fun Any.check(validator: Validator<RegistrationInfo>): Completable {
        this as RegistrationInfo

    }

    interface Validator<T>{
        fun isValid(v)
    }*/

    private fun signInValidate(p: Pair<String, String>): Completable {
        return if(Validators.isLogin(p.first)) Completable.complete()
        else Completable.error(SignInThrowable())
    }
    inner class SignInThrowable(message: String = "Неверный формат логина"): Throwable(message = message)


    private fun registrationValidate(data: RegistrationInfo): Completable {
        val regThrowable = RegistrationThrowable()
        regThrowable.login = Validators.isLogin(data.login)
        regThrowable.email = Validators.isEmail(data.email)
        regThrowable.phone = Validators.isPhone(data.phone)
        regThrowable.name = Validators.isName(data.name)
        regThrowable.lastName = Validators.isName(data.lastName)
        regThrowable.confirmingPassword = Validators.isConfirmPassword(data.password, data.confirmingPassword)
        return if(regThrowable.isValid()) Completable.complete()
        else Completable.error(regThrowable)
    }
    inner class RegistrationThrowable: Throwable(){

        var login: Boolean = false
        var email: Boolean = false
        var phone: Boolean = false
        var name: Boolean = false
        var lastName: Boolean = false
        var confirmingPassword: Boolean  = false

        fun isValid(): Boolean = (login || email || name || lastName || phone || confirmingPassword)

    }


}