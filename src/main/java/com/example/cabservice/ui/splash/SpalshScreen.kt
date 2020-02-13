package com.example.cabservice.ui.splash

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cabservice.R
import com.example.cabservice.data.appdb.AppDatabase
import com.example.cabservice.data.network.MyApi
import com.example.cabservice.data.network.NetworkConectionIntercepter
import com.example.cabservice.data.repositories.UserRepository
import com.example.cabservice.ui.auth.AuthViewModel
import com.example.cabservice.ui.auth.AuthViewModelFactory
import com.example.cabservice.ui.auth.LoginActivity
import com.example.cabservice.ui.home.MapsActivity

class SpalshScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)
        val networkConectionIntercepter= NetworkConectionIntercepter(this)

        val api= MyApi(networkConectionIntercepter)
        val db= AppDatabase.invoke(this)
        val repository= UserRepository(api,db)

        val factory= AuthViewModelFactory(repository)



        val splashViewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)

        splashViewModel.getLoggedInuser().observe(this, Observer {user->

            if (user!=null){
                goToMainActivity()
            }
            else{
                gotoLoginActivity()
            }
        })


    }

    private fun gotoLoginActivity(){
        finish()
        startActivity(Intent(this, LoginActivity::class.java).also {
            it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }

    private fun goToMainActivity() {
        finish()
        startActivity(Intent(this, MapsActivity::class.java).also {
            it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }

}
