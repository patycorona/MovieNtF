package com.cursosandroidant.storesmvvm.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cursosandroidant.storesmvvm.common.entities.StoreEntity

@Database(entities = arrayOf(StoreEntity::class), version = 2)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}