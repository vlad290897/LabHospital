package com.example.vlad.presentation.fragment.doctors.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.vlad.DI.AmbulanceApp
import com.example.vlad.R
import com.example.vlad.data.doctors.Doctor
import com.example.vlad.presentation.fragment.doctors.presenter.DoctorsPresenter
import kotlinx.android.synthetic.main.doctors_list.*
import java.util.*
import javax.inject.Inject

class DoctorsFragment : MvpFragment(), DoctorsView {
    override fun displayDoctors(doctorsList: ArrayList<Doctor>) {
        val context = activity
        rv_doctors.layoutManager = LinearLayoutManager(context)
        adapter = DoctorsAdapter(doctorsList)
        rv_doctors.adapter = adapter
        adapter?.subscribeToClickOnItem()?.subscribe {
            moxyPresenter.insertEntry(it)
        }
    }

    var adapter: DoctorsAdapter? = null
    @Inject
    lateinit var daggerPresenter: DoctorsPresenter
    @InjectPresenter
    lateinit var moxyPresenter: DoctorsPresenter

    @ProvidePresenter
    fun providePresenter(): DoctorsPresenter = daggerPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        moxyPresenter.loadDoctors()
        return inflater?.inflate(R.layout.doctors_list, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (activity?.application as AmbulanceApp).plusDoctorsComponent()?.inject(this)
    }
}