package ru.anvics.templates.domain.interactors

import io.reactivex.Completable
import ru.anvics.templates.domain.entities.RegistrationInfo
import ru.anvics.templates.domain.repository.IAuthRepository
import ru.anvics.templates.util.Throwables.RegistrationThrowable
import ru.anvics.templates.util.Throwables.RestorePasswordThrowable
import ru.anvics.templates.util.Throwables.SignInThrowable
import ru.anvics.templates.util.Validator
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
        return restoreValidate(contact).andThen(repository.restorePassword(contact))
    }


    private fun signInValidate(p: Pair<String, String>): Completable {
        val signThrowable = SignInThrowable()
        signThrowable.login = Validators.login(p.first)
        signThrowable.password = Validators.login(p.second)
        return if(
                Validators.isValid(signThrowable.login, signThrowable.password)
                ) Completable.complete()
        else Completable.error(signThrowable)
    }

    private fun registrationValidate(data: RegistrationInfo): Completable {
        val regThrowable = RegistrationThrowable()
        regThrowable.login = Validators.login(data.login)
        regThrowable.email = Validators.email(data.email)
        regThrowable.phone = Validators.phone(data.phone)
        regThrowable.name = Validators.name(data.name)
        regThrowable.confirmingPassword = Validators.confirmPassword(data.password, data.confirmingPassword)
        return if(
                Validators.isValid(
                        regThrowable.login, regThrowable.email, regThrowable.login,
                        regThrowable.phone, regThrowable.name, regThrowable.confirmingPassword)
                ) Completable.complete()
        else Completable.error(regThrowable)
    }

    private fun restoreValidate(s: String): Completable {
        val restoreThrowable = RestorePasswordThrowable()
        restoreThrowable.contact = Validators.email(s)
        val validator = Validator() // пытаюсь из data class сделать лист, чтобы в isValid не перечислять поля
        return if(
                restoreThrowable.contact.isValid
        ) Completable.complete()
        else Completable.error(restoreThrowable)
    }
}