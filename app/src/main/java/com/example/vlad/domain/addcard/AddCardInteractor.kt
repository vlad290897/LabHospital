package com.example.vlad.domain.addcard

import com.example.vlad.presentation.fragment.addcard.PatientCard

interface AddCardInteractor {
    fun createPatientCard(patientCard:PatientCard)
}