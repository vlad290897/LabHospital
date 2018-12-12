package com.example.vlad.presentation.activity.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView:MvpView {
    fun showUserInfoIntoNavHeader(name:String,surname:String,patronymic:String,date:String,socialStatus:String)
}