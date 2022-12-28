package com.example.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubcriberDAO {

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubscriber(subcriber: Subcriber):Long

    @Update
    suspend fun updateSubscriber(subcriber: Subcriber)

    @Delete
    suspend fun deleteSubscriber(subcriber: Subcriber)

    @Query("DELETE FROM subcriber_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM subcriber_data_table")
    fun getAllSubscriber():LiveData<List<Subcriber>>


}