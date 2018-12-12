package com.example.vlad.data.doctors

import android.content.ContentValues
import android.util.Log
import com.example.vlad.db.DbHelper
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class DoctorsRepositoryImp(private val dbHelper: DbHelper) : DoctorsRepository {
    val db = dbHelper.readableDatabase
    val contentValues = ContentValues()
    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }
    override fun insertEntryToDb(doctor: Doctor) {
        val randomId = Random()
        val user = FirebaseAuth.getInstance().currentUser
        val uid: String? = user?.uid
        Log.d("USERUID", uid)
        contentValues.clear()
        contentValues.put("patientid", uid)
        contentValues.put("patientid", uid)
        contentValues.put("doctorid", doctor.uid)
        db?.insert("entries", null, contentValues)
        val tabel = "entries"
        val columns = arrayOf("id", "patientid")
        val cursor = db.query(tabel, columns, null, null, null, null, null)
        val doctorIdColumnIndex = cursor.getColumnIndex("id")
        val patientIdColumnIndex = cursor.getColumnIndex("patientid")
        cursor.moveToFirst()
        Log.d("ENTRY",cursor.getString(doctorIdColumnIndex))
    }


    override fun getDoctors(): ArrayList<Doctor> {
        val doctors = ArrayList<Doctor>()
        val table = "doctor as DC inner join qualification as QL on DC.idqualif = QL.id inner join specialization as SP on DC.idspecial = SP.id"
        val columns = arrayOf("DC.uid as Uid", "DC.name as Name", "DC.surname as Surname", "DC.patronymic as Patronymic", "QL.name as Qualification", "SP.name as Specialization")
        val cursor = db.query(table, columns, null, null, null, null, null, null)
        val uidColumnIndex = cursor.getColumnIndex("Uid")
        val nameColumnIndex = cursor.getColumnIndex("Name")
        val surnameColumnIndex = cursor.getColumnIndex("Surname")
        val patronymicColumnIndex = cursor.getColumnIndex("Patronymic")
        val qualificationColumnIndex = cursor.getColumnIndex("Qualification")
        val specializationColumnIndex = cursor.getColumnIndex("Specialization")
        while (cursor.moveToNext()) {
            doctors.add(Doctor(cursor.getString(uidColumnIndex), cursor.getString(surnameColumnIndex), cursor.getString(nameColumnIndex), cursor.getString(patronymicColumnIndex), cursor.getString(qualificationColumnIndex), cursor.getString(specializationColumnIndex)))
        }
        Log.d("DOCTORS", doctors.toString())
        return doctors
    }
}