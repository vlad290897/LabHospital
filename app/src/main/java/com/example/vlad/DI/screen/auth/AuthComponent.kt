package com.example.vlad.DI.screen.auth

import com.example.vlad.DI.screen.ScreenScope
import com.example.vlad.presentation.activity.signin.SignIn
import com.example.vlad.presentation.activity.signup.SignUp
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(AuthModule::class))
@ScreenScope
interface AuthComponent {
    fun inject(signUp: SignUp)
    fun inject(signIn: SignIn)
}