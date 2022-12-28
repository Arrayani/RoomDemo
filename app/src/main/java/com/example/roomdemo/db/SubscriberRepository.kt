package com.example.roomdemo.db

class SubscriberRepository(private val dao: SubcriberDAO) {

    val subcribers = dao.getAllSubscriber() //ini ingat mereturn value nya berbentuk livedata

    suspend fun insert(subcriber: Subcriber){
        dao.insertSubscriber(subcriber)
    }
    suspend fun update(subcriber: Subcriber){
        dao.updateSubscriber(subcriber)
    }
    suspend fun delete(subcriber: Subcriber){
        dao.deleteSubscriber(subcriber)
    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }
}