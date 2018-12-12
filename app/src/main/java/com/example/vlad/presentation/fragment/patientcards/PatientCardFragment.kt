package com.example.vlad.presentation.fragment.patientcards

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpFragment
import com.example.vlad.R
import com.example.vlad.db.DbHelper
import com.example.vlad.presentation.fragment.doctors.view.CardAdapter
import com.example.vlad.presentation.fragment.doctors.view.DoctorsAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.doctors_list.*
import kotlinx.android.synthetic.main.patient_cards_fragment.*
import java.util.*

class PatientCardFragment : MvpFragment() {
    var adapter: CardAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.patient_cards_fragment, container, false)
        val user = FirebaseAuth.getInstance().currentUser
        val userUid = user?.uid
        val diagnosisList = ArrayList<CommonPatientCard>()
        val dbHelper = DbHelper(activity!!.applicationContext)
        val db = dbHelper.writableDatabase
        val table = "patientcard as CARD inner join diagnosis as DIAGNOS on CARD.diagnosisid = DIAGNOS.id inner join doctor as DOCTOR on CARD.doctorid = DOCTOR.uid inner join qualification as QUALIF on DOCTOR.idqualif = QUALIF.id inner join specialization as SPECIAL on DOCTOR.idspecial = SPECIAL.id"
        val columns = arrayOf("DOCTOR.name as Name", "DOCTOR.surname as Surname", "DOCTOR.patronymic as Patronymic", "QUALIF.name as Qualification", "SPECIAL.name as Specialization", "DIAGNOS.name as Deagnosis", "DIAGNOS.ambulat as Ambulat", "DIAGNOS.dispuchet as Dispuchet", "DIAGNOS.srokpoter as Srokpoter", "CARD.begindate as Begindate", "CARD.enddate as Enddate")
        val selection = "patientid = ?"
        val selectionArg = arrayOf(userUid)
        val cursor = db.query(table, columns, selection, selectionArg, null, null, null, null)
        val NameColumnIndex = cursor.getColumnIndex("Name")
        val surnameColumnIndex = cursor.getColumnIndex("Surname")
        val patronymicColumnIndex = cursor.getColumnIndex("Patronymic")
        val qualificationColumnIndex = cursor.getColumnIndex("Qualification")
        val specializationColumnIndex = cursor.getColumnIndex("Specialization")
        val diagnosisColumnIndex = cursor.getColumnIndex("Deagnosis")
        val ambulatColumnIndex = cursor.getColumnIndex("Ambulat")
        val dispuchetColumnIndex = cursor.getColumnIndex("Dispuchet")
        val srokpoterColumnIndex = cursor.getColumnIndex("Srokpoter")
        val begindateColumnIndex = cursor.getColumnIndex("Begindate")
        val enddateColumnIndex = cursor.getColumnIndex("Enddate")

        Log.d("CARDFRAGMENT", "CARDFRAGMENT")
        while (cursor.moveToNext()) {
            val name = cursor.getString(NameColumnIndex)
            val surname = cursor.getString(surnameColumnIndex)
            val patronymic = cursor.getString(patronymicColumnIndex)
            val qualification = cursor.getString(qualificationColumnIndex)
            val specialization = cursor.getString(specializationColumnIndex)
            val diagnosis = cursor.getString(diagnosisColumnIndex)
            val ambulat = cursor.getString(ambulatColumnIndex)
            val dispuchet = cursor.getString(dispuchetColumnIndex)
            val srokpoter = cursor.getString(srokpoterColumnIndex)
            val begindate = cursor.getString(begindateColumnIndex)
            val enddate = cursor.getString(enddateColumnIndex)
            diagnosisList.add(CommonPatientCard(userUid!!, surname, name, patronymic, qualification, specialization, diagnosis, srokpoter, ambulat, dispuchet, begindate, enddate))
            Log.d("PATIENTSCARD", diagnosisList.toString())
        }
        val context = activity
                val rv = v.findViewById<RecyclerView>(R.id.rv_patient_cards)
        rv.layoutManager = LinearLayoutManager(context)
        adapter = CardAdapter(diagnosisList)
        rv.adapter = adapter
        return v
    }
}