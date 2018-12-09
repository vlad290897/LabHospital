package com.example.vlad.presentation.activity.signin

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.vlad.DI.AmbulanceApp
import com.example.vlad.R
import com.example.vlad.db.DbHelper
import com.example.vlad.presentation.activity.common.EditTexObservable
import com.example.vlad.presentation.activity.main.MainActivity
import com.example.vlad.presentation.activity.signup.SignUp
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.sign_in_activity.*
import javax.inject.Inject


class SignIn : MvpAppCompatActivity(), SignInView {
    override fun showMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun showError() {
        Toast.makeText(this, "УПС,ЧТО-ТО ПОШЛО НЕ ТАК", Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        signin_progress.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgress() {
        signin_progress.visibility = ProgressBar.INVISIBLE
    }

    val database = FirebaseDatabase.getInstance()
    @Inject
    lateinit var daggerPresenter: SignInPresenter

    @InjectPresenter
    lateinit var moxyPresenter: SignInPresenter

    @ProvidePresenter
    fun providePresenter(): SignInPresenter = daggerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AmbulanceApp).plusAuthComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)
        val dbHelper = DbHelper(this)
        val sqLiteDatabase = dbHelper.writableDatabase

        val projection = arrayOf<String>("uid", "surname", "name", "patronymic", "idqualif","idspecial")
        var cursor: Cursor? = null
        cursor = sqLiteDatabase.query("doctor",projection,null,null,null,null,null)
        val uidColumnIndex = cursor.getColumnIndex("uid")
        val nameColumnIndex = cursor.getColumnIndex("name")
        val specialColumnIndex = cursor.getColumnIndex("idspecial")
        while (cursor.moveToNext()){
            Log.d("DOCTOR",cursor.getString(uidColumnIndex)+cursor.getString(nameColumnIndex)+cursor.getString(specialColumnIndex))

        }

//        val table = "patient as PT inner join status as ST on PT.idsocial = ST.id"
//        val columns = arrayOf("PT.name as Name", "ST.name as Status")
//        cursor = sqLiteDatabase.query(table, columns, null, null, null, null, null);
//        val uidColumnIndex = cursor.getColumnIndex("Name")
//        val socialColumnIndex = cursor.getColumnIndex("Status")
//        while (cursor.moveToNext()) {
//            val currentUid = cursor.getString(uidColumnIndex)
            //      val currentSurname = cursor.getString(surnameColumnIndex)
            //        val currentName = cursor.getString(nameColumnIndex)
//           val currentPatronymic = cursor.getString(patronymicColumnIndex)
            //         val currentDate = cursor.getString(dateColumnIndex)
//            val currentSocial = cursor.getString(socialColumnIndex)
//            Log.d("MYCURSOR", currentUid + currentSocial)
//        }
//        val cursor = sqLiteDatabase.query("patient",projection,null,null,null,null,null)
//try{
//        val uidColumnIndex = cursor.getColumnIndex("uid")
//        val surnameColumnIndex = cursor.getColumnIndex("surname")
//        val nameColumnIndex = cursor.getColumnIndex("name")
//        val patronymicColumnIndex = cursor.getColumnIndex("patronymic")
//        val dateColumnIndex = cursor.getColumnIndex("dateofbirth")
//        val socialColumnIndex = cursor.getColumnIndex("idsocial")
//        Log.d("MYCURSOR", "asd")
//        while(cursor.moveToNext()) {
//            val currentUid = cursor.getString(uidColumnIndex)
//            val currentSurname = cursor.getString(surnameColumnIndex)
//            val currentName = cursor.getString(nameColumnIndex)
//            val currentPatronymic = cursor.getString(patronymicColumnIndex)
//            val currentDate = cursor.getString(dateColumnIndex)
//            val currentSocial = cursor.getString(socialColumnIndex)
//            Log.d("MYCURSOR", currentUid + currentSurname+ currentName+currentSocial)
//        }}finally {
//            cursor.close()
//        }
        moxyPresenter.onStart()

        btnSign_up.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        val subscribeEtEmail = EditTexObservable.editTextObservable(ET_Email_Sign_In)
        val subscribeEtPass = EditTexObservable.editTextObservable(ET_Pass_Sign_In)
        val commonEtObservable = Observable.combineLatest(subscribeEtEmail, subscribeEtPass, object : BiFunction<String, String, Boolean> {
            override fun apply(t1: String, t2: String): Boolean {
                return !t1.isEmpty() && !t2.isEmpty()
            }
        })
        commonEtObservable.subscribe {
            btn_sign_in.isEnabled = it == true
        }
        btn_sign_in.setOnClickListener {
            moxyPresenter.signIn(ET_Email_Sign_In.text.toString(), ET_Pass_Sign_In.text.toString())
        }
    }
}