package com.example.myweaponroomstorage

import android.provider.ContactsContract
import android.util.Log
import androidx.databinding.ObservableField
import kotlinx.coroutines.*

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainVM {
    private val weaponManager by lazy {WeaponManager()}
    private val df = SimpleDateFormat(MainActivity.context.getString(R.string.dateFormat))
    val fieldName = ObservableField("")
    val fieldMaterial = ObservableField("")
    val fieldDate = ObservableField(df.format(Date()))
    val adapter = ObservableField(RvAdapterWeapon(weaponManager))

    var name get() =fieldName.get().toString(); set(value) =fieldName.set(value)
    var material get() = fieldMaterial.get().toString() ;set(value)=fieldMaterial.set(value)
    var date
        get() = try{df.parse(fieldDate.get().toString())?:Date()}catch (_:Exception){Date()}
        set(value) =fieldDate.set(df.format(value))

    fun addWeapon() {
        val weapon = Weapon().apply {
            name = this@MainVM.name.ifEmpty { "Оружие" }
            this@MainVM.name=""
            material = this@MainVM.material.ifEmpty { "Материал" }
            this@MainVM.material=""
            date = this@MainVM.date
        }
        weaponManager addWeapon weapon
        adapter.set( RvAdapterWeapon(weaponManager))
    }

}