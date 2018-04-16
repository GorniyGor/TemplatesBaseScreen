package ru.anvics.templates.domain.interactors

import io.reactivex.Completable
import ru.anvics.templates.domain.entities.RegistrationInfo
import ru.anvics.templates.domain.repository.IAuthRepository
import ru.anvics.templates.presentation.SchedulersProvider
import ru.anvics.templates.util.Throwables.RegistrationThrowable
import ru.anvics.templates.util.Throwables.RestorePasswordThrowable
import ru.anvics.templates.util.Throwables.SignInThrowable
import ru.anvics.templates.util.Validator
import ru.anvics.templates.util.Validators

/**
 * Created by Gor on 04.04.2018.
 */
class AuthInteractor(private val repository: IAuthRepository){

    private val signInValidator = object:
            Validator<Pair<String, String>, SignInThrowable>(SignInThrowable()){
        override fun compared(vThrowable: SignInThrowable, data: Pair<String, String>) {
            vThrowable.login = Validators.login(data.first)
        }

    }

    private val registrationValidator = object:
            Validator<RegistrationInfo, RegistrationThrowable>(RegistrationThrowable()){
        override fun compared(vThrowable: RegistrationThrowable, data: RegistrationInfo) {
            vThrowable.login = Validators.login(data.login)
            vThrowable.email = Validators.email(data.email)
            vThrowable.phone = Validators.phone(data.phone)
            vThrowable.name = Validators.name(data.name)
            vThrowable.confirmingPassword = Validators.confirmPassword(data.password, data.confirmingPassword)
        }

    }

    private val restoreValidator = object:
            Validator<String, RestorePasswordThrowable>(RestorePasswordThrowable()){
        override fun compared(vThrowable: RestorePasswordThrowable, data: String) {
            vThrowable.contact = Validators.email(data)
        }

    }

    //--Здесь рабочие методы

    fun signIn(pair: Pair<String, String>): Completable {
        return signInValidator.validate(pair).andThen(repository.signIn(pair))
//        return repository.signIn(pair)
                .subscribeOn(SchedulersProvider.io())
                .observeOn(SchedulersProvider.ui())
    }

    fun register(data: RegistrationInfo): Completable {
        return registrationValidator.validate(data).andThen(repository.register(data))
//        return repository.register(data)
                .subscribeOn(SchedulersProvider.io())
                .observeOn(SchedulersProvider.ui())
    }

    fun restorePassword(contact: String): Completable {
        return restoreValidator.validate(contact).andThen(repository.restorePassword(contact))
//        return repository.restorePassword(contact)
                .subscribeOn(SchedulersProvider.io())
                .observeOn(SchedulersProvider.ui())
    }

}