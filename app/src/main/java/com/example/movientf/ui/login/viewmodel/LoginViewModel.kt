package com.example.movientf.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movientf.R
import com.example.movientf.data.model.request.LoginRequest
import com.example.movientf.data.model.request.SendEmailRequest
import com.example.movientf.domain.model.ConstantGeneral.Companion.ERROR
import com.example.movientf.domain.model.LoginResultModel
import com.example.movientf.domain.model.ResultModel
import com.example.movientf.domain.model.UserModel
import com.example.movientf.domain.usecase.DataStoreUseCase
import com.example.movientf.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    var loginUseCase: LoginUseCase,
    var dataStoreUseCase: DataStoreUseCase
) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    val loginResultModel: MutableLiveData<LoginResultModel> by lazy {
        MutableLiveData<LoginResultModel>()
    }
    val resultModel: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }

    val getIdClient_token: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getPrefDataStore() {
        viewModelScope.launch {
            dataStoreUseCase.getIdClient()
                .zip(dataStoreUseCase.getToken()) { first, second ->
                    return@zip "$first - $second"
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    getIdClient_token.value = it
                }
        }
    }

    suspend fun setPrefDatStore(idClient: String, token: String) {
        dataStoreUseCase.setIdClient(idClient)
        dataStoreUseCase.setToken(token)
    }

    fun login(email: String, pwd: String) {
        val loginRequest = LoginRequest(email = email, password = pwd)

        compositeDisposable += loginUseCase.login(loginRequest)
            .subscribeOn(Schedulers.io())
            .subscribe({ loginRM ->
                getPrefDataStore()
                loginResultModel.postValue(loginRM)
            }, {
                loginResultModel.postValue(
                    LoginResultModel(
                        code = ERROR,
                        message = R.string.msg_error.toString(),
                        user = UserModel(),
                        token = R.string.msg_token_error.toString()
                    )
                )
            })
    }

    fun sendEmail(email: String){
        val sendEmailRequest = SendEmailRequest(email = email)
        compositeDisposable += loginUseCase.sendEmail(sendEmailRequest)
            .subscribeOn(Schedulers.io())
            .subscribe({ RM ->
                resultModel.postValue(RM)
            }, {
                resultModel.postValue(
                    ResultModel(
                        code = ERROR,
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