package com.cursosandroidant.storescoroutines.common.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/****
 * Project: Stores Coroutines
 * From: com.cursosandroidant.storescoroutines.common.entities
 * Created by Alain Nicolás Tello on 02/02/23 at 14:37
 * All rights reserved 2023.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
@Entity(tableName = "StoreEntity", indices = [Index(value = ["name"], unique = true)])
data class StoreEntity(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                       var name: String,
                       var phone: String,
                       var website: String = "",
                       var photoUrl: String,
                       var isFavorite: Boolean = false){

    constructor() : this(name = "", phone = "", photoUrl = "")
}
