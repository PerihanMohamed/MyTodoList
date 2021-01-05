package com.example.mytodolistkotlin.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "task_table")
@Parcelize
data class Tasks(


    val name : String,
    val important:Boolean = false ,
    val completed :Boolean = false ,

    @PrimaryKey(autoGenerate = true)
    val id :Int= 0,

    val created :Long = System.currentTimeMillis()

): Parcelable {

    val createdDateFormat : String
      get() = DateFormat.getDateTimeInstance().format(created)
}
