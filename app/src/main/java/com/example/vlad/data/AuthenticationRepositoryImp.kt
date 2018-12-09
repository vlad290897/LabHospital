package com.example.vlad.data

import android.util.Log
import com.example.vlad.data.AuthenticationRepository
import com.example.vlad.network.AuthAction
import com.example.vlad.network.FirebaseAuthentication
import io.reactivex.subjects.PublishSubject

class AuthenticationRepositoryImp(private val firebaseAuth: FirebaseAuthentication) : AuthenticationRepository {
    override fun insertPatientToDb() {
        Log.d("INSERTPATIENT","INSERTED")
    }

    override fun subscribeToSignIn(): PublishSubject<AuthAction> {
        return firebaseAuth.subscribeToSignIn()
    }

    override fun subscribeToSignUp(): PublishSubject<AuthAction> {
      return firebaseAuth.subscribeToSignUp()
    }

    override fun signUp(email: String, pass: String) {
        firebaseAuth.signUp(email, pass)
    }

    override fun signIn(email: String, pass: String) {
        firebaseAuth.signIn(email, pass)
    }

}