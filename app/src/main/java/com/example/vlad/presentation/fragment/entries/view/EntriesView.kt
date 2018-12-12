package com.example.vlad.presentation.fragment.entries.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.vlad.data.entries.Patient
import java.util.ArrayList

@StateStrategyType(AddToEndSingleStrategy::class)
interface EntriesView:MvpView{
    fun displayEnties(entriesList:ArrayList<Patient>)
}