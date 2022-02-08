package com.example.myweaponroomstorage

import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeaponManager {
    private val weapons by lazy {
        val deferred = MainActivity.scope.async { weaponDao.getAll() }
        while (deferred.isActive);
        if (deferred.isCancelled) return@lazy arrayListOf<Weapon>()
        arrayListOf(*deferred.getCompleted().toTypedArray())
    }
    val scope by lazy { MainActivity.scope }
    val weaponDao = AppDatabase.instance!!.weaponDao
    infix fun addWeapon(weapon: Weapon): Boolean {
        val deferred = MainActivity.scope.async { weaponDao.insert(weapon) }
        while (deferred.isActive);
        if (!deferred.isCancelled) weapon.id = deferred.getCompleted().toInt()
        return weapons.add(weapon)
    }

    infix fun deleteWeapon(index: Int): Weapon {
        val id = weapons[index].id
        MainActivity.scope.launch {
            weaponDao.deleteById(id)
        }
        return weapons.removeAt(index)
    }

    infix fun getWeapon(index: Int): Weapon = weapons.get(index)


    val count: Int get() = weapons.size
    override fun toString(): String = "[${weapons.joinToString { it.toString() }}]"
}