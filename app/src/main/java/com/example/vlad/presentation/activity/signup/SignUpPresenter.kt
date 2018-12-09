package com.example.vlad.presentation.activity.signup

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vlad.domain.auth.AuthenticationInteractorImp
import com.example.vlad.network.AuthAction
import javax.inject.Inject

@InjectViewState
class SignUpPresenter @Inject constructor(private val authenticationInteractorImp: AuthenticationInteractorImp) : MvpPresenter<SignUpView>() {

    fun signUp(email: String, pass: String,surname:String,name:String,patronymic:String,idSocial:Int,date:String){
        viewState.showProgress()
        authenticationInteractorImp.signUp(email, pass,surname,name,patronymic,idSocial,date)
    }

    fun onStart() {
        val subscription = authenticationInteractorImp.subscribeToSignUp()
        subscription.subscribe { t: AuthAction? ->
            Log.d("TAG", t.toString())
            if (t == AuthAction.SUCCESSFUL) {
                viewState.showMainActivity()
                viewState.hideProgress()

            } else {
                viewState.showError()
                viewState.hideProgress()
            }
        }

    }

}
