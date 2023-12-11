package com.example.myapppets.ui.petregister.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapppets.R
import com.example.myapppets.data.model.request.PetRequest
import com.example.myapppets.domian.model.ResultModel
import com.example.myapppets.domian.usecase.PetRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PetRegisterViewModel @Inject constructor (
    var petRegisterUseCase: PetRegisterUseCase
        ) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val petResult: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }

    fun petRegister(name: String, type: String, raza: String, obs: String, image: String) {
        val petParam = PetRequest(name = name, type = type, raza = raza, obs = obs, image = image)
        compositeDisposable += petRegisterUseCase.petRegister(petParam)
            .subscribeOn(Schedulers.io())
            .subscribe({ petReg ->
                petResult.postValue(petReg)
            }, {
                petResult.postValue(
                    ResultModel(
                        code = CODE,
                        message = R.string.msg_error_register.toString()
                    )
                )
            })
    }

    companion object{
        const val CODE = "-1"

    }
}
