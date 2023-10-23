package com.jefferson.bestfdjtest.android.di

import com.jefferson.bestfdjtest.android.DebugHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class DebugModule {

    init {
        DebugHelper.allowDebug(true)
    }

    @Provides
    fun provideBaseHttpClient(
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BODY }
        )
        .build()
}
