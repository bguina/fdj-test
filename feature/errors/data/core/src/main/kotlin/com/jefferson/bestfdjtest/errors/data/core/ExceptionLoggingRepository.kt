package com.jefferson.bestfdjtest.errors.data.core

import com.jefferson.bestfdjtest.errors.domain.repository.IExceptionRepository
import timber.log.Timber
import javax.inject.Inject

class ExceptionLoggingRepository @Inject constructor(
) : IExceptionRepository {

    override fun reportException(exception: Throwable) {
        Timber.tag(TAG).e("could not report exception, reportException is not implemented")
        Timber.tag(TAG).e(exception)
        // TODO crashlytics
    }

    companion object {
        private const val TAG: String = "ExceptionRepository"
    }
}
