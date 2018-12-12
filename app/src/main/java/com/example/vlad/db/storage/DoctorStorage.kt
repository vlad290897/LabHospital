package com.example.vlad.db.storage

import com.example.vlad.R

class DoctorStorage {
    companion object {
        fun getDoctors(): List<Doctor> {
            val list = arrayListOf<Doctor>()
            list.add(Doctor("JmgOSCFHA9OqvN4qZ4Y08zta7SD2","Челядинов","Иван","Александрович",0,0))
            list.add(Doctor("abFug0EgBMbx6cvwZH6HBTrpgDB2","Поминова","Алена","Алексеевна",1,1))
            list.add(Doctor("dtbIgizSnATFIhfgA1vPF5zITfn1","Пигарев","Владислав","Викторович",2,2))
            list.add(Doctor("iEraPIwWbuZkD6q4O5kQ10T04aa2","Евдокимов","Андрей","Иванович",0,3))
            list.add(Doctor("xPkyhZEd5UZbf3ahFhSznYwPFIm2","Цыганкова","Елизавета","Александровна",1,4))
            return list
        }
    }
}