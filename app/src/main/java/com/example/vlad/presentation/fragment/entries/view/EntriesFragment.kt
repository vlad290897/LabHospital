package com.example.vlad.presentation.fragment.entries.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.vlad.DI.AmbulanceApp
import com.example.vlad.R
import com.example.vlad.data.entries.Patient
import com.example.vlad.presentation.fragment.entries.presenter.EntriesPresenter
import kotlinx.android.synthetic.main.entries_list.*
import java.util.*
import javax.inject.Inject

class EntriesFragment : MvpFragment(), EntriesView {
    override fun displayEnties(entriesList: ArrayList<Patient>) {
        val context = activity
        rv_entries.layoutManager = LinearLayoutManager(context)
        adapter = EntriesAdapter(entriesList)
        rv_entries.adapter = adapter
        adapter?.subscribeToDelete()?.subscribe {
            moxyPresenter.deleteEntry(it)
        }
        adapter?.subscribeToUpdateDelete()?.subscribe{
            adapter?.removeEntry(it)
        }

    }

    var adapter: EntriesAdapter? = null
    @Inject
    lateinit var daggerPresenter: EntriesPresenter
    @InjectPresenter
    lateinit var moxyPresenter: EntriesPresenter

    @ProvidePresenter
    fun providePresenter(): EntriesPresenter = daggerPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        moxyPresenter.loadEntries()
        return inflater?.inflate(R.layout.entries_list, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (activity.application as AmbulanceApp).plusEntriesComponent()?.inject(this)
    }
}