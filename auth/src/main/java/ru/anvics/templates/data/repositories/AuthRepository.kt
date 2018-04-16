package ru.anvics.templates.data.repositories

import io.reactivex.Completable
import io.reactivex.Single
import ru.anvics.templates.data.account.AccountManagerHolder
import ru.anvics.templates.data.net.ApiHolder
import ru.anvics.templates.data.net.PublicApi
import ru.anvics.templates.data.net.request.RegistrationRequest
import ru.anvics.templates.data.net.response.AuthResponse
import ru.anvics.templates.domain.entities.RegistrationInfo
import ru.anvics.templates.domain.repository.IAuthRepository

object AuthRepository : IAuthRepository {

    private val publicApi: PublicApi = ApiHolder.publicApi

    override fun restorePassword(contact: String): Completable {
        return publicApi.restorePassword(contact).toCompletable()
//        return Completable.complete()
    }

    override fun register(data: RegistrationInfo): Completable {
        return publicApi.registration(
                RegistrationRequest(data.login, data.email, data.phone, data.name,
                        data.password, data.confirmingPassword))
                .doOnEvent { t1, t2 ->
                    AccountManagerHolder.setAccount(data.login, data.password)
                    AccountManagerHolder.setAuthToken(t1.token)
                }
                .toCompletable()
//        return Completable.complete()
    }

    override fun signIn(pair: Pair<String, String>): Completable {
        return publicApi.auth(pair.first, pair.second)
                .doOnEvent { t1, t2 ->
                    AccountManagerHolder.setAccount(pair.first, pair.second)
                    AccountManagerHolder.setAuthToken(t1.token)
                }
                .toCompletable()
//        return Completable.complete()
    }

    //Для методов, работающих с аккаунт-менеджеров
    override fun refreshToken(pair: Pair<String, String>): Single<AuthResponse> {
        return publicApi.auth(pair.first, pair.second)
                .doOnEvent { t1, t2 ->
                    AccountManagerHolder.setAccount(pair.first, pair.second)
                    AccountManagerHolder.setAuthToken(t1.token)
                }
    }

    fun isLogged(): Single<Boolean> {
        return if(AccountManagerHolder.hasAccount())
            ApiHolder.privateApi.getUser().map { it.result == "success" }
            else Single.just(false)
    }


}
