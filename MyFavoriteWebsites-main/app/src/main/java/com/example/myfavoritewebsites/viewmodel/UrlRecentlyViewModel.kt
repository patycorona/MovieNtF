package com.example.myfavoritewebsites.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfavoritewebsites.models.url.AddUrlModel
import com.example.myfavoritewebsites.repository.UrlRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UrlRecentlyViewModel @Inject constructor(var urlRepository: UrlRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val urlRecentlyLD: MutableLiveData<AddUrlModel> by lazy {
        MutableLiveData<AddUrlModel>()
    }

    fun getUrlRecently(urlOrigin: String) {

        compositeDisposable += urlRepository.getRecentlyUrls()
            .subscribeOn(Schedulers.io())
            .subscribe({ urlResently ->
                urlRecentlyLD.postValue(urlResently)
            }, {
                urlRecentlyLD.postValue(
                    AddUrlModel(
                        url = urlOrigin
                    )
                )
            })
    }
}
