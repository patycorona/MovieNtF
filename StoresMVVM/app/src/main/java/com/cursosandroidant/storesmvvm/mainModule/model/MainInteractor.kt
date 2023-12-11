package com.cursosandroidant.storesmvvm.mainModule.model

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.cursosandroidant.storesmvvm.StoreApplication
import com.cursosandroidant.storesmvvm.common.entities.StoreEntity
import com.cursosandroidant.storesmvvm.common.utils.Constans.END_POINT_GET_ALL
import com.cursosandroidant.storesmvvm.common.utils.Constans.ERROR
import com.cursosandroidant.storesmvvm.common.utils.Constans.STATUS_PROPERTY
import com.cursosandroidant.storesmvvm.common.utils.Constans.STORES_PROPERTY
import com.cursosandroidant.storesmvvm.common.utils.Constans.STORE_URL
import com.cursosandroidant.storesmvvm.common.utils.Constans.SUCCESS
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainInteractor {

    fun getStoresAPI(callback: (MutableList<StoreEntity>) -> Unit){
        val url = STORE_URL + END_POINT_GET_ALL

        var storeList = mutableListOf<StoreEntity>()

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,{ response ->
            Log.i("response",response.toString())

            var status = response.optInt(STATUS_PROPERTY,ERROR)
            Log.i("status",status.toString())

            if (status == SUCCESS){

                //extraemos nuestro array atravez de TypeToken
                val jsonList = response.optJSONArray(STORES_PROPERTY)?.toString()

                if(jsonList != null){
                    //config. el objeto hay que definisle un tipo ya que es de tipo genenrico
                    val mutableListType = object : TypeToken<MutableList<StoreEntity>>(){}.type
                    // le pasamos la fuente(jsonList) y el resultado(mutableListType)
                    storeList = Gson().fromJson(jsonList, mutableListType)

                    callback(storeList)
                    return@JsonObjectRequest
                }
            }else{
                //val status = response.optInt(STATUS_PROPERTY,ERROR)

                val jsonList = response.optJSONArray(STORES_PROPERTY)?.toString()
                if (jsonList != null){
                    val mutableListType = object : TypeToken<MutableList<StoreEntity>>(){}.type
                    storeList = Gson().fromJson(jsonList, mutableListType)

                    callback(storeList)
                }
            }
            callback(storeList)
        },{
            it.printStackTrace()
            callback(storeList)
        })
        StoreApplication.storeAPI.addToRequestQueve(jsonObjectRequest)
    }

   /* fun getStoresRooo(callback: (MutableList<StoreEntity>) -> Unit){
        val queue = LinkedBlockingQueue<MutableList<StoreEntity>>()
        val storeList = StoreApplication.database.storeDao().getAllStores()
        Thread{
            val json = Gson().toJson(storeList)
            Log.i("Gson",json)
            //queue.add(storeList)
            callback(storeList)
        }.start()
        callback(queue.take())
    }*/

    fun deleteStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        //val queue = LinkedBlockingQueue<StoreEntity>()
        Thread{
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            callback(storeEntity)
            //queue.add(storeEntity)
        }.start()

        //callback(queue.take())
    }

    fun updateStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        //val queue = LinkedBlockingQueue<StoreEntity>()
        Thread{
            StoreApplication.database.storeDao().updateStore(storeEntity)
            callback(storeEntity)
            //queue.add(storeEntity)
        }.start()

        //callback(queue.take())
    }
}