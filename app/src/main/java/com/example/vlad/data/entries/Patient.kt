package com.example.vlad.data.entries

data class Patient(
        val id: String,
        val uid: String,
        val surname: String,
        val name: String,
        val patronymic: String,
        val dateOfBirth: String,
        val SocialStatus: String)