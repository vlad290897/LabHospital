package com.example.vlad.presentation.fragment.addcard

data class PatientCard(
        val name:String,
        val socialStatus:Int,
        val surname:String,
        val patronymic:String,
        val dateOfBirth:String,
        val email:String,
        val beginDate:String,
        val endDate:String,
        val doctorId:String,
        val diagnosis:Diagnosis
)