package com.example.vlad.presentation.fragment.doctors.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.vlad.R
import com.example.vlad.data.doctors.Doctor
import com.example.vlad.presentation.fragment.doctors.presenter.DoctorsPresenter
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.doctors_item.view.*

class DoctorsAdapter(val doctorsList:ArrayList<Doctor>) : RecyclerView.Adapter<DoctorsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.doctors_item,parent,false)
        return ViewHolder(v)
    }

    val subjectEntry = PublishSubject.create<Doctor>()

    fun clearItems(){
        doctorsList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return doctorsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.entry.setOnClickListener{
            holder.entry.entry.isEnabled = false
            holder.entry.text = "Вы записаны к врачу"
            subjectEntry.onNext(doctorsList[position])
        }
        holder.name.text = doctorsList[position].name
        holder.surname.text = doctorsList[position].surname
        holder.patronymic.text = doctorsList[position].patronymic
        holder.qualification.text = doctorsList[position].qualification
        holder.specialization.text = doctorsList[position].specialization
    }
fun subscribeToClickOnItem():PublishSubject<Doctor>{
    return subjectEntry
}
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: TextView
        lateinit var surname: TextView
        lateinit var patronymic: TextView
        lateinit var qualification: TextView
        lateinit var specialization: TextView
        lateinit var entry: Button

        init {
            name=itemView.findViewById(R.id.doctor_name)
            surname=itemView.findViewById(R.id.doctor_surname)
            patronymic=itemView.findViewById(R.id.doctor_patronymic)
            qualification=itemView.findViewById(R.id.doctor_qualification)
            specialization=itemView.findViewById(R.id.doctor_specialization)
            entry = itemView.findViewById(R.id.entry)
        }
    }


}