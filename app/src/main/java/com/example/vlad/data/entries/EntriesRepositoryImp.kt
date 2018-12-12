package com.example.vlad.data.entries

import android.util.Log
import com.example.vlad.data.authentification.Patient
import com.example.vlad.db.DbHelper
import com.example.vlad.domain.auth.PatientEntity
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList
import javax.inject.Inject

class EntriesRepositoryImp @Inject constructor(private val dbHelper: DbHelper) : EntriesRepository {
    val db = dbHelper.writableDatabase
    override fun deleteEntry(idEntry: String) {
        val id = "0"
        Log.d("MITUT","MI TUT")
        db.delete("entries","id=$idEntry", null)
}

    override fun loadEntries(): ArrayList<com.example.vlad.data.entries.Patient> {
        val entriesList = ArrayList<com.example.vlad.data.entries.Patient>()
        val table = "entries as ENT inner join patient as PT inner join Status as ST on PT.idsocial = ST.id"
        val columns = arrayOf("ENT.id as ID","PT.uid as UID","PT.name as Name", "PT.surname as Surname", "PT.patronymic as Patronymic", "PT.dateofbirth as Date", "ST.name as Status")
        val selection = "doctorid = ?"
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        val selectionArgs = arrayOf(uid)
        val cursor = db.query(table, columns, selection, selectionArgs, null, null, null, null)
        val IDColumnIndex = cursor.getColumnIndex("ID")
        val uidColumnIndex = cursor.getColumnIndex("UID")
        val nameColumnIndex = cursor.getColumnIndex("Name")
        val surnameColumnIndex = cursor.getColumnIndex("Surname")
        val patronymicColumnIndex = cursor.getColumnIndex("Patronymic")
        val dateColumnIndex = cursor.getColumnIndex("Date")
        val statusColumnIndex = cursor.getColumnIndex("Status")
        while (cursor.moveToNext()){
            entriesList.add(com.example.vlad.data.entries.Patient(cursor.getString(IDColumnIndex),cursor.getString(uidColumnIndex),cursor.getString(surnameColumnIndex),cursor.getString(nameColumnIndex),cursor.getString(patronymicColumnIndex),cursor.getString(dateColumnIndex),cursor.getString(statusColumnIndex)))
        Log.d("ALLENTRIES",entriesList.toString())
        }

        return entriesList
    }
}