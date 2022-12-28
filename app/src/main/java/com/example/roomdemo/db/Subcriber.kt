package com.example.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subcriber_data_table")
data class Subcriber (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name =  "subcriber_name")
    var id : Int,

    @ColumnInfo(name = "subcriber_id")
    var name : String,

    @ColumnInfo(name = "subcriber_email")
    var email : String
)