package com.example.mytodolistkotlin.data.local

import androidx.room.*
import com.example.mytodolistkotlin.model.Tasks
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (task: Tasks)
    @Delete
    suspend fun delete(task: Tasks)
    @Update
    suspend fun update(task: Tasks)

    @Query("SELECT * FROM  task_table")
    suspend fun getAllList(task: Tasks) :Flow<List<Tasks>>

}