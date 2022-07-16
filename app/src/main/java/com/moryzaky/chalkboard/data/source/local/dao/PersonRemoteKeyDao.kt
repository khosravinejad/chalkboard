package com.moryzaky.chalkboard.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moryzaky.chalkboard.data.model.PersonRemoteKey

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@Dao
interface PersonRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<PersonRemoteKey>)

    @Query("SELECT * FROM personRemoteKey WHERE id = :key")
    suspend fun personRemoteKeysByPokeId(key: String): PersonRemoteKey?

    @Query("DELETE FROM personRemoteKey")
    suspend fun deleteAll()
}