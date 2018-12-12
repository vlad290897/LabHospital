package com.example.vlad.presentation.fragment.entries.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vlad.domain.entries.EntriesInteractorImp
import com.example.vlad.presentation.fragment.entries.view.EntriesView
import javax.inject.Inject

@InjectViewState
class EntriesPresenter @Inject constructor(private val interactor: EntriesInteractorImp) : MvpPresenter<EntriesView>() {

fun loadEntries(){
    val entriesList = interactor.getEntries()
    viewState.displayEnties(entriesList)
}
    fun deleteEntry(idEntry:String){
        interactor.deleteEntry(idEntry)
    }
}