package com.moryzaky.chalkboard.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moryzaky.chalkboard.data.model.PersonDataModel

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: PersonDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<PersonDataModel>)

    @Query("SELECT * FROM persons")
    fun pagingSource(): PagingSource<Int, PersonDataModel>

    @Query("SELECT * FROM persons WHERE id = :id")
    suspend fun getPersonDetails(id: String): PersonDataModel?

    @Query("DELETE FROM persons")
    suspend fun deleteAll()
}