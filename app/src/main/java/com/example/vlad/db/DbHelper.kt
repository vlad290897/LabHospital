package com.example.vlad.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.vlad.data.Patient
import java.util.ArrayList

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
                +");")
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
                +");")


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
        contentValues.clear()
        contentValues.put("uid", "myID")
        contentValues.put("surname", "Челядинов")
        contentValues.put("name", "Иван")
        contentValues.put("patronymic", "Челядинов")
        contentValues.put("idqualif", 1)
        contentValues.put("idspecial", 1)
        db?.insert("doctor", null, contentValues)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}