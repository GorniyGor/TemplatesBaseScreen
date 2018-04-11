package ru.anvics.templates.domain.repository

import domain.entities.RegistrationInfo
import io.reactivex.Completable

interface IAuthRepository {
    //--Здесь рабочие методы
    fun signIn(pair: Pair<String, String>): Completable
    fun register(data: RegistrationInfo): Completable
    fun restorePassword(contact: String): Completable
}