package com.example.vlad.presentation.fragment.doctors.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.vlad.data.doctors.Doctor
import java.util.ArrayList

@StateStrategyType(AddToEndSingleStrategy::class)
interface DoctorsView:MvpView {
    fun displayDoctors(doctorsList: ArrayList<Doctor>)
}