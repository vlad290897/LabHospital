package com.example.vlad.presentation.activity.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.vlad.DI.AmbulanceApp
import com.example.vlad.R
import com.example.vlad.presentation.activity.common.EditTexObservable
import com.example.vlad.presentation.activity.main.MainActivity
import io.reactivex.Observable
import io.reactivex.functions.Function5
import kotlinx.android.synthetic.main.sign_up_activity.*
import javax.inject.Inject
import android.app.DatePickerDialog
import android.app.Dialog
import android.widget.DatePicker
import android.app.DatePickerDialog.OnDateSetListener




class SignUp : MvpAppCompatActivity(), SignUpView {
    override fun hideProgress() {
        signup_progress.visibility = ProgressBar.INVISIBLE
    }

    override fun showProgress() {
        signup_progress.visibility = ProgressBar.VISIBLE
    }

    override fun showError() {
        Toast.makeText(this, "УПС,ЧТО-ТО ПОШЛО НЕ ТАК", Toast.LENGTH_LONG).show()
    }

    override fun showMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    var DIALOG_DATE = 1
    var myYear = 2011
    var myMonth = 2
    var myDay = 3

    @Inject
    lateinit var daggerPresenter: SignUpPresenter
    @InjectPresenter
    lateinit var moxyPresenter: SignUpPresenter

    @ProvidePresenter
    fun providePresenter(): SignUpPresenter = daggerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AmbulanceApp).plusAuthComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)
        moxyPresenter.onStart()
        dateOnClick()


        subscribeEditText()
        btn_sign_up.setOnClickListener {
            val selectedSocialStatus: Int = spin_status.selectedItemPosition
            Log.d("SPINNER", selectedSocialStatus.toString())
            val date = "$myDay/$myMonth/$myYear"
            moxyPresenter.signUp(ET_Email.text.toString(), ET_Pass.text.toString(), ET_surname.text.toString(), ET_name.text.toString(), ET_patronymic.text.toString(), selectedSocialStatus,date)
        }
    }

    private fun dateOnClick() {
        tv_date.setOnClickListener {
            showDialog(1)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        return if (id == DIALOG_DATE) {
            DatePickerDialog(this, myCallBack, myYear, myMonth, myDay)
        } else super.onCreateDialog(id)
    }

    var myCallBack: OnDateSetListener = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        myYear = year
        myMonth = monthOfYear
        myDay = dayOfMonth
        tv_date.setText("$myDay/$myMonth/$myYear")
    }

    private fun subscribeEditText() {
        val subscribeEtEmail = EditTexObservable.editTextObservable(ET_Email)
        val subscribeEtPass = EditTexObservable.editTextObservable(ET_Pass)
        val subscribeEtSurname = EditTexObservable.editTextObservable(ET_surname)
        val subscribeEtName = EditTexObservable.editTextObservable(ET_name)
        val subscribeEtPatronymic = EditTexObservable.editTextObservable(ET_patronymic)
       val isEmpty = Observable.combineLatest(subscribeEtEmail, subscribeEtPass, subscribeEtSurname, subscribeEtName, subscribeEtPatronymic, object : Function5<String, String, String, String, String, Boolean> {
            override fun apply(t1: String, t2: String, t3: String, t4: String, t5: String): Boolean {
                return !t1.isEmpty() && !t2.isEmpty() && !t3.isEmpty() && !t4.isEmpty() && !t5.isEmpty()
            }
        })
        isEmpty.subscribe {
            btn_sign_up.isEnabled = it == true
        }
    }

}