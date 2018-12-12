package com.example.vlad.DI

import android.app.Application
import com.example.vlad.DI.application.AppComponent
import com.example.vlad.DI.application.AppModule
import com.example.vlad.DI.application.DaggerAppComponent
import com.example.vlad.DI.screen.addcard.AddCardComponent
import com.example.vlad.DI.screen.addcard.AddCardModule
import com.example.vlad.DI.screen.auth.AuthComponent
import com.example.vlad.DI.screen.auth.AuthModule
import com.example.vlad.DI.screen.doctors.DoctorsComponent
import com.example.vlad.DI.screen.doctors.DoctorsModule
import com.example.vlad.DI.screen.entries.EntriesComponent
import com.example.vlad.DI.screen.entries.EntriesModule
import com.example.vlad.DI.screen.main.MainComponent
import com.example.vlad.DI.screen.main.MainModule

class AmbulanceApp : Application() {
    lateinit var appComponent: AppComponent
    var authComponent: AuthComponent? = null
    var entriesComponent: EntriesComponent? = null
    var mainComponent: MainComponent? = null
    var doctorsComponent: DoctorsComponent? = null
    var addCardComponent: AddCardComponent? = null
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
    fun plusMainComponent():MainComponent? {
        if(mainComponent==null){
            mainComponent = appComponent.plusMainComponent(MainModule())
        }
        return mainComponent
    }
    fun plusDoctorsComponent():DoctorsComponent? {
        if(doctorsComponent==null){
            doctorsComponent = appComponent.plusDoctorsComponent(DoctorsModule())
        }
        return doctorsComponent
    }
    fun plusEntriesComponent():EntriesComponent? {
        if(entriesComponent==null){
            entriesComponent = appComponent.plusEntriesComponent(EntriesModule())
        }
        return entriesComponent
    }
    fun plusAddCardComponent():AddCardComponent? {
        if(addCardComponent==null){
            addCardComponent = appComponent.plusAddCardComponent(AddCardModule())
        }
        return addCardComponent
    }
    override fun onCreate() {
        super.onCreate()
        initDagger()
    }
}