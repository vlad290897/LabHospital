package com.example.vlad.db.storage

data class Doctor(val uid:String,
                  val surname:String,
                  val name:String,
                  val patronymic:String,
                  val qualification:Int,
                  val specialization:Int
                  )