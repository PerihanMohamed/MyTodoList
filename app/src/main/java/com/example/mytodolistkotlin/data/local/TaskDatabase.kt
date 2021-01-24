package com.example.mytodolistkotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mytodolistkotlin.di.ApplicationScope
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
            @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

           val dao = database.get().taskDao()

            applicationScope.launch {
//                dao.insert(Tasks("Complete your code"))
//                dao.insert(Tasks("Dont Give Up"))
//                dao.insert(Tasks("i know you are working so hard"))
                dao.insert(Tasks("Wash the dishes"))
                dao.insert(Tasks("Do the laundry"))
                dao.insert(Tasks("Buy groceries", important = true))
                dao.insert(Tasks("Prepare food", completed = true))
                dao.insert(Tasks("Call mom"))
                dao.insert(Tasks("Visit grandma", completed = true))
                dao.insert(Tasks("Repair my bike"))
                dao.insert(Tasks("Call Elon Musk"))

            }



        }
    }



}