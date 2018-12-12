package com.example.vlad.presentation.fragment.entries.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.vlad.R
import com.example.vlad.data.doctors.Doctor
import com.example.vlad.data.entries.Patient
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.doctors_item.view.*

class EntriesAdapter(val entriesList:ArrayList<Patient>) : RecyclerView.Adapter<EntriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.entries_item,parent,false)
        return ViewHolder(v)
    }


    fun clearItems(){
        entriesList.clear()
        notifyDataSetChanged()
    }
fun removeEntry(position:Int){
    entriesList.removeAt(position)
    notifyDataSetChanged()
}
    override fun getItemCount(): Int {
        return entriesList.size
    }
val subjectDelete = PublishSubject.create<String>()
val updateDelete = PublishSubject.create<Int>()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.delete.setOnClickListener {
            subjectDelete.onNext(entriesList[position].id)
            updateDelete.onNext(position)
        }
        holder.name.text = entriesList[position].name
        holder.surname.text = entriesList[position].surname
        holder.patronymic.text = entriesList[position].patronymic
        holder.dateOfBirth.text = entriesList[position].dateOfBirth
        holder.socialStatus.text = entriesList[position].SocialStatus
    }
fun subscribeToDelete():PublishSubject<String>{
    return subjectDelete
}
    fun subscribeToUpdateDelete():PublishSubject<Int>{
        return updateDelete
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var name: TextView
        lateinit var surname: TextView
        lateinit var patronymic: TextView
        lateinit var dateOfBirth: TextView
        lateinit var socialStatus: TextView
        lateinit var delete: Button

        init {
            name=itemView.findViewById(R.id.entry_name)
            surname=itemView.findViewById(R.id.entry_surname)
            patronymic=itemView.findViewById(R.id.entry_patronymic)
            dateOfBirth=itemView.findViewById(R.id.entry_dateOfBirth)
            socialStatus=itemView.findViewById(R.id.entry_status)
            delete = itemView.findViewById(R.id.entry_delete)
        }
    }


}