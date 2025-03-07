package org.jh.coach

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.dataSource.DATA_STORE_FILE_NAME

fun createAndroidDataStore(context: Context): DataStore<Preferences> {
    return data.dataSource.createDataStore {
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}
