package com.cursosandroidant.storesmvvm.common.database

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

//basicamente lo que nos establece el patron  singleton es que una sola clase
//debe tener una sola instancia y de esta manera todos los procesos que necesiten
//acceder a esa clase lo harán desde la misma instancia.
class StoreAPI constructor(context: Context) {
    companion object{
        @Volatile
        private var INSTANCE:StoreAPI? = null

        fun getInstance(context: Context) = INSTANCE?: synchronized(this){
            INSTANCE ?: StoreAPI(context).also{ INSTANCE = it}
        }
    }

    //Request Queve es un tipo especializado en administrar operaciones de
    // redde forma asíncrina además de poder leer o escribir en la cache

    val requestQueve: RequestQueue by lazy{
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueve(req : Request<T>){
        requestQueve.add(req)}
}