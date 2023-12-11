package com.example.myapppets.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapppets.R
import com.example.myapppets.data.model.request.UserAccessRequest
import com.example.myapppets.domian.model.ResultModel
import com.example.myapppets.domian.usecase.UserAccessUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserAccessViewModel @Inject constructor(
    var userAccessUseCase: UserAccessUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val userResultModel: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }

    fun userValidation(email: String, pwd: String) {
        val userRequest = UserAccessRequest(email = email, password = pwd)

        compositeDisposable += userAccessUseCase.userAccess(userRequest)
            .subscribeOn(Schedulers.io())
            .subscribe({ accesResulM ->
                userResultModel.postValue(accesResulM)
            }, {
                userResultModel.postValue(
                    ResultModel(
                        code = CODE,
                        message = R.string.msg_error_user.toString()
                    )
                )
            })
    }

    companion object{
        const val CODE = "-1"
    }
}

