package com.cursosandroidant.storescoroutines.common.database

import androidx.lifecycle.LiveData
import androidx.room.*
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
@Dao
interface StoreDao {
    @Query("SELECT * FROM StoreEntity")
    fun getAllStores() : LiveData<MutableList<StoreEntity>>

    @Query("SELECT * FROM StoreEntity where id = :id")
    fun getStoreById(id: Long): LiveData<StoreEntity>

    @Insert
    suspend fun addStore(storeEntity: StoreEntity): Long

    @Update
    suspend fun updateStore(storeEntity: StoreEntity): Int

    @Delete
    suspend fun deleteStore(storeEntity: StoreEntity): Int
}