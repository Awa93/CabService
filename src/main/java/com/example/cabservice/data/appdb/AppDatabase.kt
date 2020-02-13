package com.example.cabservice.data.appdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cabservice.data.appdb.dao.UserDao
import com.example.cabservice.data.appdb.entity.User_Detail

@Database(entities = [User_Detail::class], version = 1,exportSchema = false)
abstract class AppDatabase :RoomDatabase() {
    abstract fun getUserDao():UserDao

    companion object{

        @Volatile
        private var instance:AppDatabase?=null
        private val LOCK=Any()
        operator fun invoke(context: Context)= instance?: synchronized(LOCK){
            instance?:buildDatabase(context).also{
                instance=it
            }
        }

        private fun buildDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,AppDatabase::class.java,
            "MyDatabase.db"
        ).build()
    }
}