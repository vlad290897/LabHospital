package com.example.vlad.DI

import android.app.Application
import com.example.vlad.DI.application.AppComponent
import com.example.vlad.DI.application.AppModule
import com.example.vlad.DI.application.DaggerAppComponent
import com.example.vlad.DI.screen.auth.AuthComponent
import com.example.vlad.DI.screen.auth.AuthModule

class AmbulanceApp : Application() {
    lateinit var appComponent: AppComponent
    var authComponent: AuthComponent? = null
    fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun plusAuthComponent():AuthComponent? {
        if(authComponent==null){
        authComponent = appComponent.plusAuthComponent(AuthModule())
        }
        return authComponent
    }
    override fun onCreate() {
        super.onCreate()
        initDagger()
    }
}