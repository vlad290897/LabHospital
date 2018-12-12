package com.example.vlad.data.main

import android.database.Cursor
import android.util.Log
import com.example.vlad.data.doctors.Doctor
import com.example.vlad.db.DbHelper
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(private val dbHelper: DbHelper) : MainRepository {
    val auth = FirebaseAuth.getInstance().currentUser
    override fun loadDootorInfo(): Doctor {
        val db = dbHelper.readableDatabase
        val uid = auth?.uid
        val table = "doctor as DC inner join qualification as QL on DC.idqualif = QL.id inner join specialization as SP on DC.idspecial = SP.id"
        val columns = arrayOf("DC.uid as Uid", "DC.name as Name", "DC.surname as Surname", "DC.patronymic as Patronymic", "QL.name as Qualification", "SP.name as Specialization")
        val selection = "uid = ?"
        val selectionArgs = arrayOf(uid)

        val cursor = db.query(table, columns, selection, selectionArgs, null, null, null, null)
        val uidColumnIndex = cursor.getColumnIndex("Uid")
        val nameColumnIndex = cursor.getColumnIndex("Name")
        val surnameColumnIndex = cursor.getColumnIndex("Surname")
        val patronymicColumnIndex = cursor.getColumnIndex("Patronymic")
        val qualificationColumnIndex = cursor.getColumnIndex("Qualification")
        val specializationColumnIndex = cursor.getColumnIndex("Specialization")
        cursor.moveToFirst()
        val doctor = Doctor(cursor.getString(uidColumnIndex), cursor.getString(surnameColumnIndex), cursor.getString(nameColumnIndex), cursor.getString(patronymicColumnIndex), cursor.getString(qualificationColumnIndex), cursor.getString(specializationColumnIndex))

        Log.d("DOCTOR", doctor.toString())
        return doctor
    }

    override fun getUserInfo(): User {
        val db = dbHelper.writableDatabase
        val uid: String? = auth?.uid
        val table = "patient as PT inner join Status as ST on PT.idsocial = ST.id"
        val columns = arrayOf("PT.name as Name", "PT.surname as Surname", "PT.patronymic as Patronymic", "PT.dateofbirth as Date", "ST.name as Status")
        val selection = "uid = ?"
        val selectionArgs = arrayOf(uid)
        var cursor: Cursor? = null
        cursor = db.query(table, columns, selection, selectionArgs, null, null, null, null)
        cursor.moveToFirst()
        val nameColumnIndex = cursor.getColumnIndex("Name")
        val statusColumnIndex = cursor.getColumnIndex("Status")
        val patronymicColumnIndex = cursor.getColumnIndex("Patronymic")
        val dateColumnIndex = cursor.getColumnIndex("Date")
        val surnameColumnIndex = cursor.getColumnIndex("Surname")
        val user = User(cursor.getString(nameColumnIndex), cursor.getString(surnameColumnIndex), cursor.getString(patronymicColumnIndex), cursor.getString(dateColumnIndex), cursor.getString(statusColumnIndex))
        Log.d("USERINFO", user.name)
        return user
    }

}