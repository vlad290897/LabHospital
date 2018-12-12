package com.example.vlad.domain.doctors

import com.example.vlad.data.doctors.Doctor
import com.example.vlad.data.doctors.DoctorsRepositoryImp
import java.util.ArrayList
import javax.inject.Inject

class DoctorsInteractorImp @Inject constructor(private val doctorsRepository: DoctorsRepositoryImp) : DoctorsInteractor {
    override fun insertEntryToDb(doctor: Doctor) {
        doctorsRepository.insertEntryToDb(doctor)
    }

    override fun getDoctors():ArrayList<Doctor> {
       return doctorsRepository.getDoctors()
    }

}