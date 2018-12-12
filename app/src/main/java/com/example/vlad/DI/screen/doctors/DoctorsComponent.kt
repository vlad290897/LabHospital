package com.example.vlad.DI.screen.doctors

import com.example.vlad.presentation.fragment.doctors.view.DoctorsFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(DoctorsModule::class))
interface DoctorsComponent {
    fun inject(doctorsFragment: DoctorsFragment)
}