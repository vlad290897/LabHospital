package com.example.vlad.domain.entries

import com.example.vlad.data.entries.Patient
import java.util.ArrayList

interface EntriesInteractor {
    fun getEntries():ArrayList<Patient>
    fun deleteEntry(idEntry:String)
}