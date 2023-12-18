package com.example.movientf.ui.profile.viewmodel

import androidx.compose.runtime.internal.illegalDecoyCallException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movientf.R
import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.data.model.request.SendEmailRequest
import com.example.movientf.data.repository.DataStoreRepository
import com.example.movientf.domain.model.ConstantGeneral
import com.example.movientf.domain.model.ResultModel
import com.example.movientf.domain.usecase.ProfileUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ProfileViewModel(
    var profileUseCase: ProfileUseCase,
    var dataStoreRepository : DataStoreRepository
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val resultModel: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }

    fun addProfile(user_name : String, image : String ){

        val profileRequest = ProfileRequest(user_name = user_name, image = image)

        viewModelScope.launch {
            dataStoreRepository.getIdClient()
                .flowOn(Dispatchers.IO)
                .collect { token ->
                    dataStoreRepository.getIdClient()
                        .flowOn(Dispatchers.IO)
                        .collect{ idClient ->
                            compositeDisposable += profileUseCase.addProfile(token, profileRequest, idClient )
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
                }
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}