package com.moryzaky.chalkboard.domain.model

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

data class PersonDomainModel(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String?,
    val birthday: Date,
    val age: Int
) {

    fun getFormattedBirthday(): String {
        return SimpleDateFormat("dd/mm/yyyy").format(birthday.time)
    }

    fun getFullName(): String {
        return "$firstName $lastName"
    }

    fun getInitialLetters(): String? {
        var letter: String? = ""
        if (firstName.isNotEmpty()) {
            letter = letter.plus(firstName.first().toString())
        }
        if (!lastName.isNullOrEmpty()) {
            letter = letter.plus(lastName.first().toString())
        }
        return if (letter.isNullOrEmpty()) {
            null
        } else {
            letter
        }
    }
}