package com.example.vlad.data.main

import com.example.vlad.data.doctors.Doctor

interface MainRepository {
    fun getUserInfo():User
    fun loadDootorInfo():Doctor
}