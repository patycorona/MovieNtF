package com.cursosandroidant.storescoroutines.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cursosandroidant.storescoroutines.common.entities.StoreEntity

/****
 * Project: Stores Coroutines
 * From: com.cursosandroidant.storescoroutines.common.database
 * Created by Alain Nicolás Tello on 02/02/23 at 14:37
 * All rights reserved 2023.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
@Database(entities = [StoreEntity::class], version = 3)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}