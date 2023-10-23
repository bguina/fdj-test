package com.jefferson.bestfdjtest.android.di

import com.jefferson.bestfdjtest.android.DebugHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class ReleaseModule {

    init {
        DebugHelper.allowDebug(false)
    }

    @Provides
    fun provideBaseHttpClient(
    ): OkHttpClient = OkHttpClient.Builder()
        .build()
}
