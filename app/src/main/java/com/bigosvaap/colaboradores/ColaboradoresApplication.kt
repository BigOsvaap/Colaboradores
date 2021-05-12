package com.bigosvaap.colaboradores

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ColaboradoresApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

    }
}
