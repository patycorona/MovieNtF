package com.example.movientf.ui.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movientf.R
import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.data.model.request.SendEmailRequest
import com.example.movientf.domain.model.ConstantGeneral
import com.example.movientf.domain.model.ResultModel
import com.example.movientf.domain.usecase.ProfileUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(
    var profileUseCase: ProfileUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val resultModel: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }

    fun addProfile(user_name : String, image : String ){
        val profileRequest = ProfileRequest(user_name = user_name, image = image)

        compositeDisposable += profileUseCase.addProfile( profileRequest )
            .subscribeOn(Schedulers.io())
            .subscribe({ RM ->
                resultModel.postValue(RM)
            }, {
                resultModel.postValue(
                    ResultModel(
                        code = ConstantGeneral.ERROR,
                        message = R.string.msg_error.toString(),
                    )
                )
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}