package com.jefferson.bestfdjtest.errors.presentation.di

import com.jefferson.bestfdjtest.errors.data.core.ExceptionLoggingRepository
import com.jefferson.bestfdjtest.errors.domain.repository.IExceptionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        ExceptionsModule.BindsModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
class ExceptionsModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {
        @Binds
        fun provideExceptionsRepository(
            exceptionsRepository: ExceptionLoggingRepository
        ): IExceptionRepository
    }
}
