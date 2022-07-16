package com.moryzaky.chalkboard.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@Entity(tableName = "personRemoteKey")
data class PersonRemoteKey(
    @PrimaryKey val id: String,
    val prevKey: Int?,
    val nextKey: Int?
)