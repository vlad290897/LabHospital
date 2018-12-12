package com.example.vlad.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.vlad.db.storage.DoctorStorage

class DbHelper(context: Context) : SQLiteOpenHelper(context, "polyclinic", null, 1) {
    val contentValues = ContentValues()
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("DBINSERT", "DATABASE CREATED")
        //ПАЦИЕНТЫ
        db?.execSQL("create table status ("
                + "id integer primary key, "
                + "name text"
                + ");")
        db?.execSQL("create table patient ("
                + "uid text primary key, "
                + "surname text not null, "
                + "name text not null, "
                + "patronymic text not null, "
                + "dateofbirth text, "
                + "idsocial integer"//id социального статуса
                + ");")
        //ВРАЧИ
        db?.execSQL("create table qualification ("
                + "id integer primary key, "
                + "name text"
                + ");")
        db?.execSQL("create table specialization ("
                + "id integer primary key, "
                + "name text"
                + ");")
        db?.execSQL("create table doctor ("
                + "uid text primary key, "
                + "surname text not null, "
                + "name text not null, "
                + "patronymic text not null, "
                + "idqualif integer, "//id квалификации статуса
                + "idspecial integer"//id специализации статуса
                + ");")
        //ЗАПИСИ
        db?.execSQL("create table entries ("
                + "id integer primary key, "
                + "doctorid text, "
                + "patientid text"
                + ");")
        //КАРТОЧКА ПАЦИЕНТА
        db?.execSQL("create table diagnosis ("
                + "id text primary key, "
                + "name text, "
                + "ambulat text, "
                + "dispuchet text, "
                + "srokpoter text"
                + ");")
        db?.execSQL("create table patientcard ("
                + "patientid text primary key, "
                + "begindate text, "
                + "enddate text, "
                + "doctorid text, "
                + "diagnosisid text"
                + ");")

        val statusId = arrayOf(0, 1, 2, 3, 4)
        val statusName: Array<String> = arrayOf("Учащийся", "Работающий", "Временно неработающий", "Инвалид", "Пенсионер")
        for (id in statusId) {
            contentValues.clear()
            Log.d("DBINSERT", statusName[id])
            contentValues.put("id", id)
            contentValues.put("name", statusName[id])
            db?.insert("status", null, contentValues)
        }
        val specializationId = arrayOf(0, 1, 2, 3, 4)
        val specializationName: Array<String> = arrayOf("Терапевт", "Невропотолог", "Педиатр", "Хирург", "Психолог")
        for (id in specializationId) {
            contentValues.clear()
            Log.d("DBINSERT", specializationName[id])
            contentValues.put("id", id)
            contentValues.put("name", specializationName[id])
            db?.insert("specialization", null, contentValues)
        }
        val qualificationId = arrayOf(0, 1, 2)
        val qualificationName: Array<String> = arrayOf("1-я категория", "2-я категория", "3-я категория")
        for (id in qualificationId) {
            contentValues.clear()
            contentValues.put("id", id)
            contentValues.put("name", qualificationName[id])
            db?.insert("qualification", null, contentValues)
        }
        val doctors = DoctorStorage.getDoctors()

        for (doctor in doctors) {
            contentValues.clear()
            contentValues.put("uid", doctor.uid)
            contentValues.put("surname", doctor.surname)
            contentValues.put("name", doctor.name)
            contentValues.put("patronymic", doctor.patronymic)
            contentValues.put("idqualif", doctor.qualification)
            contentValues.put("idspecial", doctor.specialization)
            db?.insert("doctor", null, contentValues)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}