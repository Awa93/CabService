package com.example.cabservice

import android.app.Application
import com.example.cabservice.data.appdb.AppDatabase
import com.example.cabservice.data.network.MyApi
import com.example.cabservice.data.network.NetworkConectionIntercepter
import com.example.cabservice.data.repositories.UserRepository
import com.example.cabservice.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CabApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CabApplication))

        bind() from singleton { NetworkConectionIntercepter(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }

        bind() from provider { AuthViewModelFactory(instance()) }
    }




}