package com.example.vlad.domain.main

import com.example.vlad.data.doctors.Doctor
import com.example.vlad.data.main.MainRepositoryImp
import com.example.vlad.data.main.User
import javax.inject.Inject

class MainInteractorImp @Inject constructor(private val repository: MainRepositoryImp) : MainInteractor {
    override fun getDoctorInfo(): Doctor {
       return repository.loadDootorInfo()
    }

    override fun getUserInfo(): User {
        return repository.getUserInfo()
    }

}