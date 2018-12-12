package com.example.vlad.DI.application

import android.app.Application
import android.content.Context
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
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun inject(app:Application)
    fun provideContext():Context
    fun plusEntriesComponent(entriesModule: EntriesModule):EntriesComponent
    fun plusAddCardComponent(addCardModule: AddCardModule):AddCardComponent
    fun plusAuthComponent(authModule: AuthModule):AuthComponent
    fun plusMainComponent(mainModule: MainModule):MainComponent
    fun plusDoctorsComponent(doctorsModule: DoctorsModule): DoctorsComponent
}