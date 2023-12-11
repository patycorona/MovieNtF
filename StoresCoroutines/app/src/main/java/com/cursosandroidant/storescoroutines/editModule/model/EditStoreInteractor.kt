package com.cursosandroidant.storescoroutines.editModule.model

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import com.cursosandroidant.storescoroutines.StoreApplication
import com.cursosandroidant.storescoroutines.common.entities.StoreEntity
import com.cursosandroidant.storescoroutines.common.utils.StoresException
import com.cursosandroidant.storescoroutines.common.utils.TypeError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/****
 * Project: Stores Coroutines
 * From: com.cursosandroidant.storescoroutines.editModule.model
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
class EditStoreInteractor {

    fun getStoreById(id: Long): LiveData<StoreEntity>{
        return  StoreApplication.database.storeDao().getStoreById(id)
    }

    suspend fun saveStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        try {
            StoreApplication.database.storeDao().addStore(storeEntity)
        } catch (e: SQLiteConstraintException){
            throw StoresException(TypeError.INSERT)
        }
    }

    suspend fun updateStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        try {
            val result = StoreApplication.database.storeDao().updateStore(storeEntity)
            if (result == 0) throw StoresException(TypeError.UPDATE)
        } catch (e: SQLiteConstraintException){
            throw StoresException(TypeError.UPDATE)
        }
    }
}