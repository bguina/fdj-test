package com.jefferson.bestfdjtest.errors.domain.repository

interface IExceptionRepository {
    fun reportException(
        exception: Throwable,
    )
}
