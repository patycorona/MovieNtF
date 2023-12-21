package com.example.movientf.ui.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movientf.R
import com.example.movientf.data.model.request.DeleteProfileRequest
import com.example.movientf.data.model.request.ProfileRequest
import com.example.movientf.data.model.request.UpdateProfileRequest
import com.example.movientf.data.repository.DataStoreRepository
import com.example.movientf.domain.model.ConstantGeneral
import com.example.movientf.domain.model.ProfileResult
import com.example.movientf.domain.model.ResultModel
import com.example.movientf.domain.usecase.ProfileUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(
    var profileUseCase: ProfileUseCase,
    var dataStoreRepository : DataStoreRepository
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val token = dataStoreRepository.getToken()
    val id_client = dataStoreRepository.getIdClient()

    val resultModel: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }

    val listProfileRM: MutableLiveData<ProfileResult> by lazy {
        MutableLiveData<ProfileResult>()
    }

    fun addProfile(user_name : String, image : String){
        var profileRequest = ProfileRequest(user_name = user_name, image = image)

        compositeDisposable += profileUseCase.addProfile(token.toString(), id_client.toString(), profileRequest)
            .subscribeOn(Schedulers.io())
            .subscribe({ RM ->
                resultModel.postValue(RM)
            }, {
                resultModel.postValue(
                    ResultModel(
                        code = ConstantGeneral.ERROR,
                        message = R.string.msg_token_error.toString()
                    )
                )
            })
    }

    fun getProfile(){
        compositeDisposable += profileUseCase.getProfile(token.toString(), id_client.toString())
            .subscribeOn(Schedulers.io())
            .subscribe({ list_profile ->
                listProfileRM.postValue(
                    ProfileResult(
                        sussess = true,
                        list_profile = list_profile
                    )
                )
            }, {
                listProfileRM.postValue(
                    ProfileResult(
                        sussess = false
                    )
                )
            })
    }


    fun deleteProfile(id_profile : String){
        val DeleteProfileRequest = DeleteProfileRequest(id_profile = id_profile)
        compositeDisposable += profileUseCase.deleteProfile(token.toString(), id_client.toString(), DeleteProfileRequest)
            .subscribeOn(Schedulers.io())
            .subscribe({ RM ->
                resultModel.postValue(RM)
            }, {
                resultModel.postValue(
                    ResultModel(
                        code = ConstantGeneral.ERROR,
                        message = R.string.msg_token_error.toString()
                    )
                )
            })
    }

    fun updateProfile(id: String, user_name : String, image : String, ){
       var updateProfileRequest = UpdateProfileRequest(id,user_name,image)
        compositeDisposable += profileUseCase.updateProfile(token.toString(), id_client.toString(), updateProfileRequest)
            .subscribeOn(Schedulers.io())
            .subscribe({ RM ->
                resultModel.postValue(RM)
            }, {
                resultModel.postValue(
                    ResultModel(
                        code = ConstantGeneral.ERROR,
                        message = R.string.msg_token_error.toString()
                    )
                )
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}