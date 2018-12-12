package com.example.vlad.data.doctors

import com.google.android.gms.dynamic.LifecycleDelegate
import java.util.ArrayList

interface DoctorsRepository {
    fun getDoctors():ArrayList<Doctor>
    fun insertEntryToDb(doctor:Doctor)
}