package com.example.myapppets.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapppets.domian.model.PetResult
import com.example.myapppets.domian.usecase.AllPetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    var allPetUseCase: AllPetUseCase
    ): ViewModel()  {
    private val compositeDisposable = CompositeDisposable()

    val listPetsRs: MutableLiveData<PetResult> by lazy {
        MutableLiveData<PetResult>()
    }

    fun getPets() {
        compositeDisposable += allPetUseCase.getAllPet()
            .subscribeOn(Schedulers.io())
            .subscribe({ listPetsR ->
                listPetsRs.postValue(
                    PetResult(
                        sussess = true,
                        list = listPetsR
                    )
                )
            }, {
                listPetsRs.postValue(
                    PetResult(
                        sussess = false
                    )
                )
            })
    }

}