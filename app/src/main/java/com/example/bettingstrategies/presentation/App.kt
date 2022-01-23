package com.example.bettingstrategies.presentation

import android.app.Application
import android.content.Context

class App: Application() {

    private var appContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        /* If you has other classes that need context object to initialize when application is created,
         you can use the appContext here to process. */
    }

    fun getAppContext(): Context? {
        return appContext
    }
}