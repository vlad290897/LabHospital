package com.example.vlad.presentation.activity.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vlad.domain.main.MainInteractorImp
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(private val mainInteractor: MainInteractorImp) : MvpPresenter<MainView>() {
    fun getUserInfo() {
       val user = mainInteractor.getUserInfo()
        viewState.showUserInfoIntoNavHeader(user.name,user.surname,user.patronymic,user.date,user.socialStatus)
    }
    fun getDoctorInfo(){
        val doctor = mainInteractor.getDoctorInfo()
        viewState.showUserInfoIntoNavHeader(doctor.name,doctor.surname,doctor.patronymic,doctor.qualification,doctor.specialization)
    }
}