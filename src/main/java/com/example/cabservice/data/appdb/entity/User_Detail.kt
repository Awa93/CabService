package com.example.cabservice.data.appdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID=0

@Entity
data class User_Detail(
     var User_Id: Int? = null,
     var FullName: String? = null,
     var Email_Id: String? = null,
     var Phone: String? = null,
     var Password: String? = null,
     var Address: String? = null,
     var State: String? = null,
     var City: String? = null,
     var Pincode: String? = null,
     var Mebr_Amount: Double? = null
){
    @PrimaryKey(autoGenerate = false)
    var uid:Int= CURRENT_USER_ID
}


