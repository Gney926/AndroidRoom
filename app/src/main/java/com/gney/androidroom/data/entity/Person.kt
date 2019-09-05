package com.gney.androidroom.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gney.androidroom.data.converter.DateConverter
import java.io.Serializable
import java.util.*

@Entity(tableName = "person")
@TypeConverters(DateConverter::class)
data class Person(
    @PrimaryKey
    @ColumnInfo(name = "socialNumber")
    val socialNumber: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "job")
    val job: String,

    @ColumnInfo(name = "created")
    val created: Date
): Serializable