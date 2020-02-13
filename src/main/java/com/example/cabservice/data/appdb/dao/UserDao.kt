package com.example.cabservice.data.appdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cabservice.data.appdb.entity.CURRENT_USER_ID
import com.example.cabservice.data.appdb.entity.User_Detail

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDetail: User_Detail):Long

    @Query("SELECT * FROM user_detail WHERE uid=$CURRENT_USER_ID")
    fun getUser():LiveData<User_Detail>
}