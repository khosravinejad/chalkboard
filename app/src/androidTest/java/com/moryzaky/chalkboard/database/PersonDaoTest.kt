package com.moryzaky.chalkboard.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.moryzaky.chalkboard.data.model.BirthdayDataModel
import com.moryzaky.chalkboard.data.model.NameDataModel
import com.moryzaky.chalkboard.data.model.PersonDataModel
import com.moryzaky.chalkboard.data.source.local.ChalkBoardDatabase
import io.mockk.MockKAnnotations
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

class PersonDaoTest {

    private val db = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        ChalkBoardDatabase::class.java
    ).build()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun insert_persons_and_get_all_persons() = runBlocking {
        val person1 = makeFakePersonDataModel("morteza")
        val person2 = makeFakePersonDataModel("jhon")
        val person3 = makeFakePersonDataModel("sarah")

        val personDao = db.personDao

        personDao.insertAll(listOf(person1, person2, person3))

        val result1 = personDao.getPersonDetails("Mrmortezalast mortezaFri Nov 09 00:00:00 GMT+03:00 1990")
        Assert.assertEquals(makeFakePersonDataModel("morteza"), result1)

        val result2 = personDao.getPersonDetails("Mrjhonlast jhonFri Nov 09 00:00:00 GMT+03:00 1990")
        Assert.assertEquals(makeFakePersonDataModel("jhon"), result2)

        val result3 = personDao.getPersonDetails("Mrsarahlast sarahFri Nov 09 00:00:00 GMT+03:00 1990")
        Assert.assertEquals(makeFakePersonDataModel("sarah"), result3)

        // invalid fetching
        val result4 = personDao.getPersonDetails("invalidId")
        Assert.assertEquals(null, result4)

    }

    private fun makeFakePersonDataModel(name: String) = PersonDataModel(
        nameDetails = NameDataModel(
            title = "Mr",
            firstName = name,
            lastName = "last $name"
        ),
        birthdayInfo = BirthdayDataModel(
            date = Date("11/09/1990"),
            age = 32
        )
    )
}