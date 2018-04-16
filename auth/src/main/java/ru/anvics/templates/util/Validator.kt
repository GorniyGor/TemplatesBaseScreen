package ru.anvics.templates.util

import io.reactivex.Completable
import ru.anvics.templates.util.Throwables.ValidatingThrowable

/**
 * Created by Gor on 12.04.2018.
 *
 *  T - тип объекта, данные в котором нужно проверить
 */

abstract class Validator<in T, in V: ValidatingThrowable>(private val customThrowable: V) {

    abstract fun compared(vThrowable: V, data: T)

    fun validate(data: T): Completable {
        compared(customThrowable, data)
        return if( Validators.isValid(customThrowable.toArray()) ) Completable.complete()
        else Completable.error(customThrowable)
    }
}