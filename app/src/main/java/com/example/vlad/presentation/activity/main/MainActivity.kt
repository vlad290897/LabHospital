package com.example.vlad.presentation.activity.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.vlad.DI.AmbulanceApp
import com.example.vlad.R
import com.example.vlad.presentation.fragment.addcard.view.AddCardFragment
import com.example.vlad.presentation.fragment.doctors.view.DoctorsFragment
import com.example.vlad.presentation.fragment.entries.view.EntriesFragment
import com.example.vlad.presentation.fragment.patientcards.PatientCardFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView {
    override fun showUserInfoIntoNavHeader(name: String, surname: String, patronymic: String, date: String, socialStatus: String) {
        val header = nav_view.getHeaderView(0)
        val tvHeaderName = header.findViewById<TextView>(R.id.user_name)
        val tvHeaderSurname = header.findViewById<TextView>(R.id.user_surname)
        val tvHeaderPatronymic = header.findViewById<TextView>(R.id.user_patronymic)
        val tvHeaderDate = header.findViewById<TextView>(R.id.user_dateOfBirth)
        val tvHeaderStatus = header.findViewById<TextView>(R.id.user_socialStatus)
        tvHeaderName.text = name
        tvHeaderSurname.text = surname
        tvHeaderPatronymic.text = patronymic
        tvHeaderDate.text = date
        tvHeaderStatus.text = socialStatus
    }

    @Inject
    lateinit var daggerPresenter: MainPresenter

    @InjectPresenter
    lateinit var moxyPresenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = daggerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AmbulanceApp).plusMainComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance()
        val uid = user?.uid
        var admLVL = 0
        Log.d("UID", uid)
        val reference = database.getReference(uid!!)
        fab.setOnClickListener {
            val addCardFragment = AddCardFragment()
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, addCardFragment)
            fragmentTransaction.commit()
        }

        val toggle = object : ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                if (admLVL == 0) {
                    moxyPresenter.getUserInfo()
                }else moxyPresenter.getDoctorInfo()
            }
        }
        drawer_layout.addDrawerListener(toggle)

        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.child("Admin LVL").value.toString() == "1") {
                    admLVL = 1
                    fab.visibility = View.VISIBLE
                }

            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("ERR", "NO UID")
            }

        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance()
        val uid = user?.uid
        val reference = database.getReference(uid!!)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.child("Admin LVL").value.toString() == "1") {
                    menuInflater.inflate(R.menu.main, menu)
                }

            }

            override fun onCancelled(p0: DatabaseError) {
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val fragmentTransaction = fragmentManager.beginTransaction()
        when (item.itemId) {

            R.id.entries -> {
                val entriesFragment = EntriesFragment()
                fragmentTransaction.replace(R.id.container, entriesFragment)
                fragmentTransaction.commit()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val doctorsFragment = DoctorsFragment()
        val patientCardFragment = PatientCardFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.nav_doctors -> {
                fragmentTransaction?.replace(R.id.container, doctorsFragment)
            }
            R.id.patient_cards -> {
                fragmentTransaction?.replace(R.id.container, patientCardFragment)
            }

        }
        fragmentTransaction.commit()
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}