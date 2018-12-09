package com.example.vlad.presentation.activity.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vlad.domain.auth.AuthenticationInteractorImp
import javax.inject.Inject
@InjectViewState
class MainPresenter @Inject constructor(val authenticationInteractor: AuthenticationInteractorImp):MvpPresenter<MainView>(){
    fun signUp(email:String,pass:String){
        viewState.showMainScreen()
    }
}