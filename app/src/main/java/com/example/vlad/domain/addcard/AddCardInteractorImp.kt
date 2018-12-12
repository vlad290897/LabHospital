package com.example.vlad.domain.addcard

import com.example.vlad.data.addcard.AddCardRepository
import com.example.vlad.data.addcard.AddCardRepositoryImp
import com.example.vlad.network.AuthAction
import com.example.vlad.presentation.fragment.addcard.PatientCard
import javax.inject.Inject

class AddCardInteractorImp @Inject constructor(private val addCardRepository: AddCardRepositoryImp):AddCardInteractor {
    override fun createPatientCard(patientCard: PatientCard) {
        addCardRepository.searchPatient(patientCard.email).subscribe{
            if(it == AuthAction.UNSUCCESSFUL){
                addCardRepository.insertPatientToDb(patientCard)
            }else addCardRepository.createDiagnosisAndCard(patientCard)
        }
    }


}