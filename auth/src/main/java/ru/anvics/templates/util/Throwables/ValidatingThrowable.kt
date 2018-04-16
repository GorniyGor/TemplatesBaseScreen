package ru.anvics.templates.util.Throwables

import ru.anvics.templates.util.Verifiable

/**
 * Created by Gor on 12.04.2018.
 */
abstract class ValidatingThrowable: Throwable() {
    abstract fun toArray(): Array<Verifiable>
}