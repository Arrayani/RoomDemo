package com.example.roomdemo

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.db.Subcriber
import com.example.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel (private val repository: SubscriberRepository) : ViewModel(), Observable {

    val subscribers = repository.subcribers
    private var isUpdateOrDelete = false
    private lateinit var subcriberToUpdateOrDelete : Subcriber

    @Bindable
    val inputName = MutableLiveData<String>()
    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get()=statusMessage


    // kenapa pake init, karena rencana nya tulisan text button nya
    // dapat berubah secara dinamis
    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate(){
        if(isUpdateOrDelete){
            subcriberToUpdateOrDelete.name = inputName.value!!
            subcriberToUpdateOrDelete.email = inputEmail.value!!
            update(subcriberToUpdateOrDelete)
        }else{

        }

        val name:String = inputName.value!!
        val email:String = inputEmail.value!!
        insert(Subcriber(0,name,email))  // kenapa 0, karena autoincrement dbs / autogenerate id
        inputName.value = null  //buat clear field
        inputEmail.value = null //buat clear field
    }

    fun clearAllOrDelete(){
        if (isUpdateOrDelete){
            delete(subcriberToUpdateOrDelete)
        }else{
            clearAll()
        }

    }
//    fun insert(subcriber: Subcriber){
//        viewModelScope.launch {
//            repository.insert(subcriber)
//        }
//    }

    fun insert(subcriber: Subcriber): Job =
        viewModelScope.launch {
            repository.insert(subcriber)

            statusMessage.value= Event("Subsciber Inserted Successfully")
        }

    fun update(subcriber: Subcriber): Job =
        viewModelScope.launch {
            repository.update(subcriber)

            inputName.value = null
            inputEmail.value =  null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"

            statusMessage.value=Event("Subsciber Updated Successfully")
        }

    fun delete(subcriber: Subcriber): Job =
        viewModelScope.launch {
            repository.delete(subcriber)

            inputName.value = null
            inputEmail.value =  null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"

            statusMessage.value=Event("Subsciber Delete Successfully")
        }

    fun clearAll(): Job =
        viewModelScope.launch {
            repository.deleteAll()
            statusMessage.value=Event("All Subscibers Deleted Successfully")
        }

    fun initUpdateAndDelete(subcriber: Subcriber){
    //.value itu kalo di java getvalues, ini karena viewModel bertipe mutablelivedata
    //makanya menggunakan cara .value atau .getvalue kalo di java
    //ingat!!! ini cara memasukan nilai/value ke tipe mutableLivedata

        inputName.value = subcriber.name
        inputEmail.value =  subcriber.email
        isUpdateOrDelete = true
        subcriberToUpdateOrDelete = subcriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}