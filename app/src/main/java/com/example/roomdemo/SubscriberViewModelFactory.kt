package com.example.roomdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.db.SubscriberRepository



class SubscriberViewModelFactory(
    private val repository: SubscriberRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(repository) as T
        }


        throw IllegalArgumentException("Unknown View Model class")
    }

}






//@Suppress("UNCHECKED_CAST")
//class SubscriberViewModelFactory(private val repository: SubscriberRepository):ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//
//        //ini boiler code. ga usah pusing, copy aja modifikasi
//        if (modelClass.isAssignableFrom(SubcriberViewModel::class.java)){
//               //SubcriberViewModel itu adalah class SubcriberViewModel
//               //repositor itu nama variabel di atas "private val repository"
//        return SubcriberViewModel(repository) as T
//    }
//        throw java.lang.IllegalArgumentException("Uknown View Model class")
//    }
//
//}