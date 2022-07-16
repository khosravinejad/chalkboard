package com.moryzaky.chalkboard.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moryzaky.chalkboard.data.model.BirthdayDataModel
import com.moryzaky.chalkboard.data.model.NameDataModel
import com.moryzaky.chalkboard.data.model.PersonDataModel

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

class DataTypeConverter {
    companion object {
        private val gson = Gson()
    }

    @TypeConverter
    fun personsToJson(persons: List<PersonDataModel>): String? {
        return gson.toJson(persons)
    }

    @TypeConverter
    fun jsonToPersons(json: String?): List<PersonDataModel>? {
        val type = object : TypeToken<List<PersonDataModel>?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun nameInfoToJson(nameDataModel: NameDataModel): String? {
        return gson.toJson(nameDataModel)
    }

    @TypeConverter
    fun jsonToNameInfo(json: String?): NameDataModel? {
        val type = object : TypeToken<NameDataModel?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun birthdayToJson(birthdayDataModel: BirthdayDataModel): String? {
        return gson.toJson(birthdayDataModel)
    }

    @TypeConverter
    fun jsonToBirthday(json: String?): BirthdayDataModel? {
        val type = object : TypeToken<BirthdayDataModel?>() {}.type
        return gson.fromJson(json, type)
    }
}