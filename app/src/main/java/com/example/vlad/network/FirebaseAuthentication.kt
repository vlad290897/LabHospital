package com.example.vlad.network

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import io.reactivex.subjects.PublishSubject

class FirebaseAuthentication() {
    val database = FirebaseDatabase.getInstance()
    val signInState = PublishSubject.create<AuthAction>()
    val signUpState = PublishSubject.create<AuthAction>()
    val auth = FirebaseAuth.getInstance()
    fun signUp(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        signUpState.onNext(AuthAction.SUCCESSFUL)
                        val admLvl = 0
                        val user = FirebaseAuth.getInstance().currentUser
                        val uid = user!!.uid
                        val replaceEmail = email.replace('@','|').replace('.','|')
                        val myUserRef: DatabaseReference = database.getReference(replaceEmail)
                        myUserRef.child("uid").setValue(uid)
                        val chechAdmRef: DatabaseReference = database.getReference(uid)
                        chechAdmRef.child("Admin LVL").setValue(admLvl)
                    } else signUpState.onNext(AuthAction.UNSUCCESSFUL)
                }
    }

    fun signIn(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful)
                        signInState.onNext(AuthAction.SUCCESSFUL)
                    else signInState.onNext(AuthAction.UNSUCCESSFUL)
                }
    }

    fun subscribeToSignIn(): PublishSubject<AuthAction> {
        return signInState
    }
    fun subscribeToSignUp():PublishSubject<AuthAction>{
        return signUpState
    }

}