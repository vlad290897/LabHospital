package com.example.vlad.presentation.fragment.doctors.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.vlad.R
import com.example.vlad.data.doctors.Doctor
import com.example.vlad.presentation.fragment.patientcards.CommonPatientCard
import io.reactivex.subjects.PublishSubject

class CardAdapter(val cardsList: ArrayList<CommonPatientCard>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.patient_cards_item, parent, false)
        return ViewHolder(v)
    }

    val subjectEntry = PublishSubject.create<Doctor>()

    fun clearItems() {
        cardsList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cardsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = cardsList[position].name
        holder.surname.text = cardsList[position].surname
        holder.patronymic.text = cardsList[position].patronymic
        holder.qualification.text = cardsList[position].qualification
        holder.specialization.text = cardsList[position].specialization
        holder.diagnosis.text = cardsList[position].diagnosis
        holder.ambullech.text = cardsList[position].ambulatorHealth
        holder.sroktrudosposob.text = cardsList[position].srokPoteriTrudoSposob
        holder.dispuchet.text = cardsList[position].dispUchet
        holder.begindate.text = cardsList[position].beginDate
        holder.enddate.text = cardsList[position].endDate
    }

    fun subscribeToClickOnItem(): PublishSubject<Doctor> {
        return subjectEntry
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: TextView
        lateinit var surname: TextView
        lateinit var patronymic: TextView
        lateinit var qualification: TextView
        lateinit var specialization: TextView
        lateinit var diagnosis: TextView
        lateinit var ambullech: TextView
        lateinit var sroktrudosposob: TextView
        lateinit var dispuchet: TextView
        lateinit var begindate: TextView
        lateinit var enddate: TextView

        init {
            name = itemView.findViewById(R.id.patient_cards_doctor_name)
            surname = itemView.findViewById(R.id.patient_cards_doctor_surname)
            patronymic = itemView.findViewById(R.id.patient_cards_doctor_patronymic)
            qualification = itemView.findViewById(R.id.patient_cards_doctor_qualification)
            specialization = itemView.findViewById(R.id.patient_cards_doctor_specialization)
            diagnosis = itemView.findViewById(R.id.patient_cards_diagnosis)
            ambullech = itemView.findViewById(R.id.patient_cards_ambulatorlech)
            sroktrudosposob = itemView.findViewById(R.id.patient_cards_sroktrudosposob)
            dispuchet = itemView.findViewById(R.id.patient_cards_dispuchet)
            begindate = itemView.findViewById(R.id.patient_cards_beginDate)
            enddate = itemView.findViewById(R.id.patient_cards_endDate)
        }
    }
}