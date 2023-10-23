package com.jefferson.bestfdjtest.android

import timber.log.Timber

object DebugHelper {
    fun allowDebug(allow: Boolean) {
        if (allow) {
            Timber.plant(Timber.DebugTree())

            System.setProperty(
                kotlinx.coroutines.DEBUG_PROPERTY_NAME,
                kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON
            )
        } else {
            Timber.uprootAll()

            System.setProperty(
                kotlinx.coroutines.DEBUG_PROPERTY_NAME,
                kotlinx.coroutines.DEBUG_PROPERTY_VALUE_OFF
            )
        }
    }
}
