package com.cursosandroidant.storescoroutines.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosandroidant.storescoroutines.common.entities.StoreEntity
import com.cursosandroidant.storescoroutines.common.utils.Constants
import com.cursosandroidant.storescoroutines.common.utils.StoresException
import com.cursosandroidant.storescoroutines.common.utils.TypeError
import com.cursosandroidant.storescoroutines.mainModule.model.MainInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/****
 * Project: Stores Coroutines
 * From: com.cursosandroidant.storescoroutines.mainModule.adapter
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
class MainViewModel: ViewModel() {
    private var interactor: MainInteractor = MainInteractor()


    private val typeError: MutableLiveData<TypeError> = MutableLiveData()

    private val showProgress: MutableLiveData<Boolean> = MutableLiveData()

    private val stores = interactor.stores

    fun getStores(): LiveData<MutableList<StoreEntity>>{
        return stores
    }

    fun getTypeError(): MutableLiveData<TypeError> = typeError

    fun isShowProgress(): LiveData<Boolean>{
        return showProgress
    }

    fun deleteStore(storeEntity: StoreEntity){
        executeAction { interactor.deleteStore(storeEntity) }
    }

    fun updateStore(storeEntity: StoreEntity){
        storeEntity.isFavorite = !storeEntity.isFavorite
        executeAction { interactor.updateStore(storeEntity) }
    }

    private fun executeAction(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            showProgress.value = Constants.SHOW

            try {
                block()
            } catch (e: StoresException){
                typeError.value = e.typeError
            } finally {
                showProgress.value = Constants.HIDE
            }
        }
    }
}