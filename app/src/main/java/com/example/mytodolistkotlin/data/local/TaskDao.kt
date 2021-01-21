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


    fun getTasks(query: String, sortOrder: SortOrder, hideCompleted: Boolean): Flow<List<Tasks>> =
            when(sortOrder) {
                SortOrder.BY_DATE -> getTasksSortedByDateCreated(query, hideCompleted)
                SortOrder.BY_NAME -> getTasksSortedByName(query, hideCompleted)
            }

    @Query("SELECT * FROM task_table WHERE (completed != :hideCompleted OR completed = 0) AND name LIKE '%' || :searchQuery || '%' ORDER BY important DESC, name")
    fun getTasksSortedByName(searchQuery: String, hideCompleted: Boolean): Flow<List<Tasks>>

    @Query("SELECT * FROM task_table WHERE (completed != :hideCompleted OR completed = 0) AND name LIKE '%' || :searchQuery || '%' ORDER BY important DESC, created")
    fun getTasksSortedByDateCreated(searchQuery: String, hideCompleted: Boolean): Flow<List<Tasks>>

    @Query("DELETE FROM TASK_TABLE WHERE ")
    fun DeleteCompletedDialogFra()
}