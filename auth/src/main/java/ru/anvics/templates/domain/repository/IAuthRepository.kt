package ru.anvics.templates.domain.repository

import io.reactivex.Completable
import ru.anvics.templates.domain.entities.RegistrationInfo

interface IAuthRepository {
    //--Здесь рабочие методы
    fun signIn(pair: Pair<String, String>): Completable
    fun register(data: RegistrationInfo): Completable
    fun restorePassword(contact: String): Completable
}