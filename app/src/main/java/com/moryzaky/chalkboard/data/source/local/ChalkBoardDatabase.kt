package com.moryzaky.chalkboard.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moryzaky.chalkboard.BuildConfig
import com.moryzaky.chalkboard.data.model.PersonDataModel
import com.moryzaky.chalkboard.data.source.local.dao.PersonDao

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@Database(
    entities = [
        PersonDataModel::class
    ],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
@TypeConverters(DataTypeConverter::class)
abstract class ChalkBoardDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "chalkboard.db"
    }

    abstract val personDao: PersonDao
}