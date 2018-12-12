package com.example.vlad.data.addcard

import android.content.ContentValues
import android.util.Log
import com.example.vlad.db.DbHelper
import com.example.vlad.network.AuthAction
import com.example.vlad.network.FirebaseAuthentication
import com.example.vlad.presentation.fragment.addcard.PatientCard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class AddCardRepositoryImp @Inject constructor(private val firebaseAuthentication: FirebaseAuthentication, private val dbHelper: DbHelper) : AddCardRepository {
    val database = FirebaseDatabase.getInstance()
    override fun createDiagnosisAndCard(patientCard: PatientCard) {
        val email = patientCard.email.replace('@', '|').replace('.', '|')
        val searchReference = database.getReference(email)
        searchReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
             val patientUid =  p0.child("uid").value.toString()
                createDiagnosis(patientCard)
                createPatiendCard(patientCard,patientUid)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("NASHEL", "Net")
            }
        })
    }

    val db = dbHelper.writableDatabase
    fun createDiagnosis(patientCard: PatientCard) {
        val contentValues = ContentValues()
        contentValues.clear()
        contentValues.put("id", patientCard.diagnosis.id)
        contentValues.put("name", patientCard.diagnosis.diagnosis)
        contentValues.put("ambulat", patientCard.diagnosis.ambulatorHealth)
        contentValues.put("dispuchet", patientCard.diagnosis.dispUchet)
        contentValues.put("srokpoter", patientCard.diagnosis.srokPoteriTrudoSposob)
        db.insert("diagnosis", null, contentValues)
        createPatiendCard(patientCard)
    }

    private fun createPatiendCard(patientCard: PatientCard) {
        val contentValues = ContentValues()
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        contentValues.put("patientid",uid)
        contentValues.put("begindate", patientCard.beginDate)
        contentValues.put("enddate", patientCard.beginDate)
        contentValues.put("doctorid", patientCard.doctorId)
        contentValues.put("diagnosisid", patientCard.diagnosis.id)
        db.insert("patientcard", null, contentValues)
    }
    private fun createPatiendCard(patientCard: PatientCard,patientUid:String) {
        val contentValues = ContentValues()
        contentValues.put("patientid",patientUid )
        contentValues.put("begindate", patientCard.beginDate)
        contentValues.put("enddate", patientCard.beginDate)
        contentValues.put("doctorid", patientCard.doctorId)
        contentValues.put("diagnosisid", patientCard.diagnosis.id)
        db.insert("patientcard", null, contentValues)
    }

    override fun searchPatient(emailPatient: String): PublishSubject<AuthAction> {
        val searchState = PublishSubject.create<AuthAction>()
        val email = emailPatient.replace('@', '|').replace('.', '|')
        val searchReference = database.getReference(email)
        searchReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.child("uid").value == null) {
                    searchState.onNext(AuthAction.UNSUCCESSFUL)
                } else searchState.onNext(AuthAction.SUCCESSFUL)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("NASHEL", "Net")
            }
        })
        return searchState
    }

    override fun insertPatientToDb(cardPatient: PatientCard) {

        val contentValues = ContentValues()
        signUp(cardPatient.email, cardPatient.dateOfBirth).subscribe {
            if (it == AuthAction.SUCCESSFUL) {
                val user = FirebaseAuth.getInstance().currentUser
                val uid = user?.uid
                Log.d("PATIENTNOTFOUND", uid)
                contentValues.clear()
                Log.d("INCHANGE", cardPatient.email)
                contentValues.put("uid", uid)
                contentValues.put("surname", cardPatient.surname)
                contentValues.put("name", cardPatient.name)
                contentValues.put("patronymic", cardPatient.patronymic)
                contentValues.put("dateofbirth", cardPatient.dateOfBirth)
                contentValues.put("idsocial", cardPatient.socialStatus)
                db?.insert("patient", null, contentValues)
                createDiagnosis(cardPatient)
            }
        }
    }

    override fun signUp(email: String, pass: String): PublishSubject<AuthAction> {
        firebaseAuthentication.signUp(email, pass)
        return firebaseAuthentication.subscribeToSignUp()
    }

}