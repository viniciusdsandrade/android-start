package com.example.loginappwithjetpackcompose

import android.app.Application
import com.example.loginappwithjetpackcompose.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class LoginAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@LoginAppApplication)
            modules(
                firebaseModule
            )
        }
    }
}