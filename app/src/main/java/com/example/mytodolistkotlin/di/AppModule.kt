package com.example.mytodolistkotlin.di

import android.app.Application
import androidx.room.Room
import com.example.mytodolistkotlin.data.local.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDataBase (
            app :Application ,
             callback : TaskDatabase.TaskCallBack
    ) =
        Room.databaseBuilder(app,
            TaskDatabase::class.java,
            "task_table")
            .fallbackToDestructiveMigration()
                .addCallback(callback)
            .build()

    @Provides
    fun provieTaskDaO(db : TaskDatabase) =
        db.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun providecCoroutineScope () =  CoroutineScope(SupervisorJob())




}
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope