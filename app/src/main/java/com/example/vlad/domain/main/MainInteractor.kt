package com.example.vlad.domain.main

import com.example.vlad.data.doctors.Doctor
import com.example.vlad.data.main.User

interface MainInteractor {
    fun getUserInfo(): User
    fun getDoctorInfo():Doctor
}