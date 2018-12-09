package com.example.vlad.presentation.activity.signin

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vlad.domain.auth.AuthenticationInteractorImp
import com.example.vlad.network.AuthAction
import javax.inject.Inject

@InjectViewState
class SignInPresenter @Inject constructor(val authenticationInteractorImp: AuthenticationInteractorImp) : MvpPresenter<SignInView>() {
    fun onStart(){
        val subscribe = authenticationInteractorImp.subscribeToSignIn()
        subscribe.subscribe {
            if(it == AuthAction.SUCCESSFUL){
                viewState.hideProgress()
                viewState.showMainActivity()
            }
            else{
                viewState.hideProgress()
                viewState.showError()
            }
        }
    }
    fun signIn(email: String, pass: String) {
        viewState.showProgress()
        authenticationInteractorImp.signIn(email, pass)
    }
}