package com.example.myweaponroomstorage

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

@Entity
@TypeConverters(DateConverter::class)
class Weapon (
    var name: String = "",
    var material: String = "",
    var date: Date = Date()
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}