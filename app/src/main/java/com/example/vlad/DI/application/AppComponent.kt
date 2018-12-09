package com.example.vlad.DI.application

import android.app.Application
import android.content.Context
import com.example.vlad.DI.screen.auth.AuthComponent
import com.example.vlad.DI.screen.auth.AuthModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun inject(app:Application)
    fun provideContext():Context
    fun plusAuthComponent(authModule: AuthModule):AuthComponent
}