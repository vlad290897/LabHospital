package com.example.vlad.presentation.fragment.addcard.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vlad.domain.addcard.AddCardInteractorImp
import com.example.vlad.presentation.fragment.addcard.PatientCard
import com.example.vlad.presentation.fragment.addcard.view.AddCardView
import javax.inject.Inject

@InjectViewState
class AddCardPresenter @Inject constructor(private val addCardInteractor: AddCardInteractorImp) : MvpPresenter<AddCardView>() {
    fun createPatientCard(patientCard: PatientCard) {
        addCardInteractor.createPatientCard(patientCard)
        viewState.showToast()
    }
}