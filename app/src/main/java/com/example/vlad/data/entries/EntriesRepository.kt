package com.example.vlad.data.entries

import com.example.vlad.data.authentification.Patient
import java.util.ArrayList

interface EntriesRepository {
    fun loadEntries():ArrayList<com.example.vlad.data.entries.Patient>
    fun deleteEntry(idEntry:String)
}