package com.example.vlad.data.authentification

data class Patient(val uid:String,
                   val surname:String,
                   val name:String,
                   val patronymic:String,
                   val dateOfBirth:String,
                   val idOfSocialStatus:Int)