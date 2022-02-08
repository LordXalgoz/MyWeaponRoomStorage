package com.example.myweaponroomstorage

import androidx.room.*
import androidx.room.Room.databaseBuilder

@Database(entities = [Weapon::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val weaponDao: WeaponDao

    companion object {
        @Volatile
        var instance:AppDatabase?=null
            get(){
                var instance=field
                return instance?: synchronized(this) { databaseBuilder(
                    MainActivity.context,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                }.also { field=it }
            }
    }
}