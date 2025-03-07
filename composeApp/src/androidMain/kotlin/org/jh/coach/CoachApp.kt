package org.jh.coach

import android.app.Application
import di.KoinInitializer

class CoachApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}
