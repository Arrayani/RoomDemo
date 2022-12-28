package com.example.roomdemo.db

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subcriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase(){

    abstract val subcriberDAO : SubcriberDAO

    companion object{
        @Volatile
        private var INSTANCE : SubscriberDatabase?=null
         fun getInstance(context: Context):SubscriberDatabase{
             synchronized(this){
                 var instance : SubscriberDatabase? = INSTANCE
                 if (instance==null){
                     instance = Room.databaseBuilder(
                         context.applicationContext,
                         SubscriberDatabase::class.java,
                         "subscriber_data_database"
                     ).build()
                 }
                 return instance
             }
         }
    }
}