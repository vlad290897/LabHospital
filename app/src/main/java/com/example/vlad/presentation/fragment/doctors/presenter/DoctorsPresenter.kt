package com.example.vlad.presentation.fragment.doctors.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vlad.data.doctors.Doctor
import com.example.vlad.domain.doctors.DoctorsInteractorImp
import com.example.vlad.presentation.fragment.doctors.view.DoctorsView
import javax.inject.Inject

@InjectViewState
class DoctorsPresenter @Inject constructor(private val doctorsInteractor: DoctorsInteractorImp) : MvpPresenter<DoctorsView>() {
    fun loadDoctors() {
        val doctorsList = doctorsInteractor.getDoctors()
        viewState.displayDoctors(doctorsList)
    }
    fun insertEntry(doctor:Doctor){
        doctorsInteractor.insertEntryToDb(doctor)
    }
}