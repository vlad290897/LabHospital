package com.example.vlad.domain.doctors

import com.example.vlad.data.doctors.Doctor
import java.util.ArrayList

interface DoctorsInteractor {
    fun getDoctors():ArrayList<Doctor>
    fun insertEntryToDb(doctor: Doctor)
}