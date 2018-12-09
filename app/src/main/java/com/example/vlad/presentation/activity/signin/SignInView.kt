package com.example.vlad.presentation.activity.signin

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SignInView:MvpView {
    fun showMainActivity()
    fun showError()
    fun showProgress()
    fun hideProgress()
}