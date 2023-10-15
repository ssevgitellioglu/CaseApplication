package com.example.caseapplication.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
open class Application : Application() {

    @Inject
    protected lateinit var timberTree: Timber.Tree

    override fun onCreate() {
        super.onCreate()

        Timber.plant(timberTree)
    }
}