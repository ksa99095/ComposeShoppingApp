package ksr.android.compose.common

import android.app.Application
import android.content.Context

class GlobalApplication: Application() {
    companion object {
        lateinit var applicationCtx: Context
    }

    override fun onCreate() {
        super.onCreate()

        applicationCtx = applicationContext
    }
}