package com.example.vlad.domain.auth

import com.example.vlad.network.AuthAction
import io.reactivex.subjects.PublishSubject

interface AuthenticationInteractor {
    fun signUp(email:String,pass:String,surname:String,name:String,patronymic:String,idsocial:Int,date:String)
    fun signIn(email: String,pass: String)
}