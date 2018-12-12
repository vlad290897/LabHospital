package com.example.vlad.DI.screen.main

import com.example.vlad.presentation.activity.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}