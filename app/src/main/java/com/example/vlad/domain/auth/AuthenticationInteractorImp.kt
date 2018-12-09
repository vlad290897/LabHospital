package com.example.vlad.domain.auth

import android.content.ContentValues
import android.util.Log
import com.example.vlad.data.AuthenticationRepositoryImp
import com.example.vlad.db.DbHelper
import com.example.vlad.network.AuthAction
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class AuthenticationInteractorImp @Inject constructor(private val repository: AuthenticationRepositoryImp,private val dbHelper: DbHelper) : AuthenticationInteractor {
    override fun signUp(email: String, pass: String, surname: String, name: String, patronymic: String, idsocial: Int, date: String) {
        patientSubject.onNext(PatientEntity(email, surname, name, patronymic, idsocial, date))
        repository.signUp(email, pass)
    }
    val patientSubject = PublishSubject.create<PatientEntity>()
    lateinit var subject: PublishSubject<AuthAction>
    fun subscribeToSignUp(): PublishSubject<AuthAction> {
        var patientEntity:PatientEntity?=null
        patientSubject.subscribe{
            patientEntity=it
        }
        subject = repository.subscribeToSignUp()
        subject.subscribe {
            if (it == AuthAction.SUCCESSFUL&&patientEntity!=null)
                insertToDb(patientEntity)
        }

        return repository.subscribeToSignUp()
    }

    fun subscribeToSignIn(): PublishSubject<AuthAction> {
        return repository.subscribeToSignIn()
    }

    override fun signIn(email: String, pass: String) {
        repository.signIn(email, pass)
    }

    fun insertToDb(patientEntity: PatientEntity?) {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues()
        val database = FirebaseDatabase.getInstance()
        val email = patientEntity?.email?.replace('@', '|')?.replace('.', '|')
        val searchReference = database.getReference(email!!)
        Log.d("DATEMYBIRTH", patientEntity.date)
        searchReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                contentValues.put("uid",p0.child("uid").value.toString())
                contentValues.put("surname",patientEntity.surname)
                contentValues.put("name",patientEntity.name)
                contentValues.put("patronymic",patientEntity.patronymic)
                contentValues.put("dateofbirth",patientEntity.date)
                contentValues.put("idsocial",patientEntity.idOfSocialStatus)
                db?.insert("patient",null,contentValues)

                Log.d("SEARCHDB", "SEARCH" + p0.child("uid").value)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("SEARCHDB", "SEARCH fail")
            }
        })
    }

}