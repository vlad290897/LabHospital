package com.example.vlad.presentation.fragment.addcard.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.vlad.DI.AmbulanceApp
import com.example.vlad.R
import com.example.vlad.presentation.fragment.addcard.Diagnosis
import com.example.vlad.presentation.fragment.addcard.PatientCard
import com.example.vlad.presentation.fragment.addcard.presenter.AddCardPresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.add_card_fragment.*
import kotlinx.android.synthetic.main.sign_up_activity.*
import java.util.*
import javax.inject.Inject

class AddCardFragment : MvpFragment(), AddCardView {
    override fun showToast() {
        val toast = Toast.makeText(activity,"Вы поставили диагноз",Toast.LENGTH_LONG)
        toast.show()
    }

    @Inject
    lateinit var daggerPresenter: AddCardPresenter
    @InjectPresenter
    lateinit var moxyPresenter: AddCardPresenter

    @ProvidePresenter
    fun providePresenter(): AddCardPresenter = daggerPresenter


    var btn: Button? = null
    var dispanser: Switch? = null
    var ambulator: Switch? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.add_card_fragment, container, false)
        btn = v?.findViewById<Button>(R.id.addcard_createDiagnosis)
        dispanser = v?.findViewById(R.id.dispanser)
        ambulator = v?.findViewById(R.id.ambulator)
       createPatientCard()
        return v
    }

    private fun createPatientCard() {
        val randomId = Random()
        var dispanserUchet: String = "Нет"
        var ambulatorLech: String = "Нет"

        dispanser?.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked)
                    dispanserUchet = "Да"
                else dispanserUchet = "Нет"
            }
        })
        ambulator?.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked)
                    ambulatorLech = "Да"
                else ambulatorLech = "Нет"
            }
        })
        btn?.setOnClickListener {
            val id = randomId.nextInt(0..1000)
            val selectedSocialStatus: Int = social_status.selectedItemPosition
            val dateOfBirth = addcard_dateOfBirth.text.toString()
            val email = addcard_email.text.toString()
            val surname = addcard_surname.text.toString()
            val name = addcard_name.text.toString()
            val patronymic = addcard_patronymic.text.toString()
            val diagnosis = addcard_diagnosis.text.toString()
            val srokPoteriTrudosposob = addcard_sroktrudosposob.text.toString()
            val dateBegin = addcard_begin.text.toString()
            val dateEnd = addcard_end.text.toString()
            val user = FirebaseAuth.getInstance().currentUser
            val doctorUid = user?.uid.toString()
            val card = PatientCard(name,selectedSocialStatus,surname, patronymic,dateOfBirth,email, dateBegin, dateEnd, doctorUid, Diagnosis(id, diagnosis, srokPoteriTrudosposob, ambulatorLech, dispanserUchet))
            moxyPresenter.createPatientCard(card)
            Toast.makeText(activity,"Вы поставили диагноз",Toast.LENGTH_LONG)
        }
    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (activity.application as AmbulanceApp).plusAddCardComponent()?.inject(this)
    }
}