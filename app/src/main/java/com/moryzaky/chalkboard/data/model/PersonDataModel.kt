package com.moryzaky.chalkboard.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.moryzaky.chalkboard.domain.model.PersonDomainModel

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@Entity(tableName = "persons")
data class PersonDataModel(
    @SerializedName("name") val nameDetails: NameDataModel,
    @SerializedName("dob") val birthdayInfo: BirthdayDataModel
) {
    @PrimaryKey
    @SerializedName("id")
    var id: String = ""
        get() {
            if (field.isNullOrBlank())
                return extractId()
            return field

        }

    private fun extractId(): String {
        return nameDetails.title + nameDetails.firstName + nameDetails.lastName + birthdayInfo.date
    }
}

internal fun PersonDataModel.toDomainModel(): PersonDomainModel {
    return PersonDomainModel(
        id = this.id,
        title = this.nameDetails.title,
        firstName = this.nameDetails.firstName,
        lastName = this.nameDetails.lastName,
        birthday = this.birthdayInfo.date,
        age = this.birthdayInfo.age
    )
}