package com.gney.androidroom.data.dao

import androidx.paging.DataSource
import androidx.room.*
import com.gney.androidroom.data.entity.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM person ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, Person>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)

    @Query("SELECT * FROM person WHERE socialNumber = :socialNumber")
    fun isPerson(socialNumber: String): Boolean

    @Query("UPDATE person SET name = :name, job = :job")
    fun updateInfo(name: String, job: String)
}