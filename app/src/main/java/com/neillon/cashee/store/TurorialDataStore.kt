package com.neillon.cashee.store

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.neillon.cashee.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TutorialDataStore(private val context: Context) {

    private var dataStore: DataStore<Preferences> =
        context.createDataStore(name = Constants.Tutorial.STORE_TUTORIAL_NAME)
    private val tutorialKey by lazy { preferencesKey<Int>(Constants.Tutorial.NEED_TUTORIAL) }

    fun read(): Flow<Boolean> = dataStore.data
        .map { currentPreferences ->
            if (currentPreferences[tutorialKey] == null) true else currentPreferences[tutorialKey] == 1
        }

    suspend fun edit(newValue: Boolean) {
        dataStore.edit { settings ->
            settings[tutorialKey] = if (newValue) 1 else 0
        }
    }
}