package com.example.vlad.data.addcard

import com.example.vlad.network.AuthAction
import com.example.vlad.presentation.fragment.addcard.PatientCard
import io.reactivex.subjects.PublishSubject

interface AddCardRepository {
    fun signUp(email: String, pass: String): PublishSubject<AuthAction>
    fun insertPatientToDb(patientCard: PatientCard)
    fun searchPatient(email: String):PublishSubject<AuthAction>
    fun createDiagnosisAndCard(patientCard: PatientCard)
}