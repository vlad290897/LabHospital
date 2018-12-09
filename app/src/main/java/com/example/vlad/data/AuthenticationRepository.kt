package com.example.vlad.data

import com.example.vlad.network.AuthAction
import io.reactivex.subjects.PublishSubject

interface AuthenticationRepository {
    fun insertPatientToDb()
    fun subscribeToSignIn():PublishSubject<AuthAction>
    fun subscribeToSignUp():PublishSubject<AuthAction>
    fun signUp(email:String,pass:String)
    fun signIn(email: String,pass: String)
}