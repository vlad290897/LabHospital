package com.example.vlad.domain.entries

import com.example.vlad.data.entries.EntriesRepositoryImp
import com.example.vlad.data.entries.Patient
import java.util.ArrayList
import javax.inject.Inject

class EntriesInteractorImp @Inject constructor(private val repository: EntriesRepositoryImp) : EntriesInteractor {
    override fun deleteEntry(idEntry: String) {
        repository.deleteEntry(idEntry)
    }

    override fun getEntries(): ArrayList<Patient> {
        return repository.loadEntries()
    }
}