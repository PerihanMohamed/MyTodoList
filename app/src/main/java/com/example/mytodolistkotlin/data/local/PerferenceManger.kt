package com.example.mytodolistkotlin.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val  TAG = "PerferenceManger "
enum class SortOrder {BY_DATE ,BY_NAME}

data class FilterPreferences (val sortOrder: SortOrder ,val  hideComplete :Boolean)

@Singleton
class PerferenceManger @Inject constructor(@ApplicationContext context:Context) {


    val dataStore: DataStore<Preferences> = context.createDataStore("user_settings")

    val PreferenceFlow  = dataStore.data
            .catch { exception ->
                if(exception is IOException){
                     Log.e(TAG , " ERROW READING PERFERENCES" , exception)
                    emit(emptyPreferences())
                }else
                    throw exception

            }
            .map { Perferences ->
                   val sortOrder= SortOrder.valueOf(Perferences[PreferenceKeys.sortOrder]?:SortOrder.BY_DATE.name )
                  val hideComplete =Perferences[PreferenceKeys.hideComplete] ?:false
                FilterPreferences(sortOrder ,hideComplete)
            }

    suspend fun sortOrder (sortOrder: SortOrder) {
        dataStore.edit { Preferences ->
            Preferences[PreferenceKeys.sortOrder] = sortOrder.name

        }
    }
        suspend fun hideCompletes (hideComplete: Boolean){
            dataStore.edit { Preferences ->
                Preferences[PreferenceKeys.hideComplete] = hideComplete

            }

    }

    private object PreferenceKeys {
        val sortOrder = preferencesKey<String>(SortOrder.BY_DATE.name)
        val hideComplete = preferencesKey<Boolean>("false")

    }

}