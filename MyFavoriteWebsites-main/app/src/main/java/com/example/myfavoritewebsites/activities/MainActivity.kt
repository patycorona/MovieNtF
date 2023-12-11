package com.example.myfavoritewebsites.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfavoritewebsites.databinding.ActivityMainBinding
import com.example.myfavoritewebsites.models.url.AddUrlModel
import com.example.myfavoritewebsites.models.url.AddUrlResponseModel
import com.example.myfavoritewebsites.viewmodel.AddUrlViewModel
import com.example.myfavoritewebsites.viewmodel.UrlRecentlyViewModel
import com.example.myfavoritewebsites.views.UrlAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    var aliasParam: String = ""
    var urlParam: String = ""
    val listurlsResently = mutableListOf<String>()
    private val viewModelUrl: AddUrlViewModel by viewModels()
    private val viewModelUrlRecently: UrlRecentlyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnGuardar?.setOnClickListener {
            addUrl(binding?.edUrl?.text.toString())
        }
        initAddUrlObserver()
        initObserver()
    }

    private fun setAdapter() {

        val adapter = UrlAdapter(
            listurlsResently
        )
        binding?.recyclerview?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    // Callback
    private val urlAddResult = Observer<AddUrlResponseModel> { accessResponseModel ->
        if (accessResponseModel.alias != "") {
            aliasParam = accessResponseModel.alias
            urlParam = accessResponseModel.links.short
            AddListUrl(urlParam)
            viewModelUrlRecently.getUrlRecently(accessResponseModel.links.short)
        } else {
            Toast.makeText(this, "an exception occurred, please try again", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private val urlRecentlyObserver = Observer<AddUrlModel> { addUrlModel ->
        if (addUrlModel.url == "") {
            Toast.makeText(this, "an exception occurred, please try again", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun initAddUrlObserver() {
        viewModelUrl.urlRegisterLD.observe(this, urlAddResult)
    }

    private fun initObserver() {
        viewModelUrlRecently.urlRecentlyLD.observe(this, urlRecentlyObserver)
    }

    private fun AddListUrl(url: String) {
        println("add Url: ${listurlsResently.add(url)}")
        println("list: $listurlsResently")
        initRecycler()
        setAdapter()

        Toast.makeText(this, "OK $listurlsResently", Toast.LENGTH_LONG)
            .show()
    }

    private fun addUrl(url: String) {
        viewModelUrl.addNewValidation(url)
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding?.recyclerview?.apply {
            layoutManager = linearLayoutManager
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }
}
