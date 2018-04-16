package ru.anvics.templates.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.anvics.templates.data.net.response.AuthResponse
import ru.anvics.templates.domain.entities.RegistrationInfo

interface IAuthRepository {
    fun refreshToken(pair: Pair<String, String>): Single<AuthResponse>
    //--Здесь рабочие методы
    fun signIn(pair: Pair<String, String>): Completable
    fun register(data: RegistrationInfo): Completable
    fun restorePassword(contact: String): Completable
}