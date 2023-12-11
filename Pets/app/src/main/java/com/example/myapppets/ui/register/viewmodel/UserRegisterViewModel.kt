package com.example.myapppets.ui.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapppets.R
import com.example.myapppets.data.model.request.UserRequest
import com.example.myapppets.domian.model.ResultModel
import com.example.myapppets.domian.usecase.UserRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class UserRegisterViewModel @Inject constructor(
    var userRegisterUseCase: UserRegisterUseCase
    ): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val userResultModel: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }

    fun userRegister(name: String, lastName: String, email: String, password: String) {

        val userRequest = UserRequest(name = name,lastName = lastName, email = email, password = password)
        compositeDisposable += userRegisterUseCase.userRegister(userRequest)
            .subscribeOn(Schedulers.io())
            .subscribe({ userReg ->
                userResultModel.postValue(userReg)
            }, {
                userResultModel.postValue(
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