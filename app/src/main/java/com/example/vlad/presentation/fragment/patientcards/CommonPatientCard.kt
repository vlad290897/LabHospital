package com.example.vlad.presentation.fragment.patientcards

import com.example.vlad.presentation.fragment.addcard.Diagnosis

data class CommonPatientCard(val uid:String,
                             val surname:String,
                             val name:String,
                             val patronymic:String,
                             val qualification:String,
                             val specialization:String,
                             val diagnosis:String,
                             val srokPoteriTrudoSposob:String,
                             val ambulatorHealth:String,
                             val dispUchet:String,
                             val beginDate:String,
                             val endDate:String
)