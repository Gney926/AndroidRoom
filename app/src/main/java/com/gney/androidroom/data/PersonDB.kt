package com.gney.androidroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gney.androidroom.data.PersonDB.Companion.DB_VERSION
import com.gney.androidroom.data.dao.PersonDao
import com.gney.androidroom.data.entity.Person

@Database(entities = [Person::class], version = DB_VERSION, exportSchema = false)
abstract class PersonDB: RoomDatabase() {

    abstract fun personDao(): PersonDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "person.db"

        private var INSTANCE: PersonDB? = null

        fun getInstance(context: Context): PersonDB? {
            if (INSTANCE == null) {
                synchronized(PersonDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDB::class.java,
                        DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }


        fun destroyInstance() {
            INSTANCE = null
        }
    }
}