package com.example.myfavoritewebsites.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfavoritewebsites.models.url.AddUrlModel
import com.example.myfavoritewebsites.models.url.AddUrlResponseModel
import com.example.myfavoritewebsites.models.url.AddUrlResult
import com.example.myfavoritewebsites.repository.AddUrlRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AddUrlViewModel @Inject constructor
(var addUrlRepository: AddUrlRepository) : ViewModel() {

    // ayuda a liberar los recursos cuando utilizamos programacion reactiva
    private val compositeDisposable = CompositeDisposable()

    val urlRegisterLD: MutableLiveData<AddUrlResponseModel> by lazy {
        MutableLiveData<AddUrlResponseModel>()
    }

    val addUrlListLD: MutableLiveData<AddUrlModel> by lazy {
        MutableLiveData<AddUrlModel>()
    }

    // add News
    fun addNewValidation(url: String) {

        compositeDisposable += addUrlRepository.addNewUrl(
            url = url
        )
            .subscribeOn(Schedulers.io())
            .subscribe({ UrlResultModel ->
                urlRegisterLD.postValue(UrlResultModel)
            }, {
                urlRegisterLD.postValue(
                    AddUrlResponseModel(
                        alias = url, links = AddUrlResult(self = url, short = url)
                    )
                )
            })
    }
}
