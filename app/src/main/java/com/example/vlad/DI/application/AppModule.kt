package com.example.vlad.DI.application

import android.app.Application
import android.content.Context
import com.example.vlad.db.DbHelper
import com.example.vlad.network.FirebaseAuthentication
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app:Application) {
    @Provides
    fun provideDbHelper(context:Context): DbHelper = DbHelper(context)
    @Provides
    fun provideContext():Context = app
    @Provides
    fun provideFirebaseAuth():FirebaseAuthentication = FirebaseAuthentication()
}