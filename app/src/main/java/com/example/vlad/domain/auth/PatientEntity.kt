package com.example.vlad.domain.auth

data class PatientEntity(
        val email: String,
        val surname: String,
        val name: String,
        val patronymic: String,
        val idOfSocialStatus: Int,
        val date:String)