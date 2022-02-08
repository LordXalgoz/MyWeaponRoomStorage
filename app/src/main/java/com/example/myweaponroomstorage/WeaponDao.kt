package com.example.myweaponroomstorage

import android.database.Cursor
import androidx.room.*

@Dao
interface WeaponDao {
    @Query("SELECT * from Weapon")
    suspend fun getAll():List<Weapon>

    @Query("SELECT * from Weapon WHERE id = :id")
    suspend fun getById(id: Int): Weapon?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Weapon):Long

    @Update
    suspend fun update(note: Weapon)

    @Query("DELETE FROM Weapon WHERE id = :id")
    suspend fun deleteById(id: Int)
}