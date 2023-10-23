package com.jefferson.bestfdjtest.errors.domain.interactor

import com.jefferson.bestfdjtest.errors.domain.repository.IExceptionRepository
import java.util.concurrent.CancellationException
import javax.inject.Inject

class ReportExceptionUseCase @Inject constructor(
    private val exceptionRepository: IExceptionRepository,
){
    operator fun invoke(
        exception: Throwable
    ) {
        when (exception) {
            is CancellationException -> {
                // do nothing
            }
            else -> {
                exceptionRepository.reportException(exception)
            }
        }
    }
}
