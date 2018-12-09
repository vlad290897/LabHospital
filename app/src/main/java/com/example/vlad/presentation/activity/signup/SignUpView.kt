package com.example.vlad.presentation.activity.signup

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SignUpView:MvpView {
    fun showMainActivity()
    fun showError()
    fun showProgress()
    fun hideProgress()
}