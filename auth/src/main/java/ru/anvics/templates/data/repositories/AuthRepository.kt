package ru.anvics.templates.data.repositories

import io.reactivex.Completable
import ru.anvics.templates.domain.entities.RegistrationInfo
import ru.anvics.templates.domain.repository.IAuthRepository

object AuthRepository : IAuthRepository {
    override fun restorePassword(contact: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun register(data: RegistrationInfo): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //--Здесь рабочие методы

    override fun signIn(pair: Pair<String, String>): Completable {
//        return ApiHolder.publicApi.auth(pair.first, pair.second).flatMap {
//            it.token
//            ///
//            Completable.fromObservable(it)
//        }
        return Completable.complete()
    }


}