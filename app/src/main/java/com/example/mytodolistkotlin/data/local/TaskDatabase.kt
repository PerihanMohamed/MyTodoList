package com.example.mytodolistkotlin.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mytodolistkotlin.model.Tasks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Tasks::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    class TaskCallBack @Inject constructor(
            private val database: Provider<TaskDatabase> ,
            private val coroutineScope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

           val dao = database.get().taskDao()

            coroutineScope.launch {
                dao.insert(Tasks("Complete your code"))
                dao.insert(Tasks("Dont Give Up"))
                dao.insert(Tasks("i know you are working so hard"))

            }



        }
    }



}