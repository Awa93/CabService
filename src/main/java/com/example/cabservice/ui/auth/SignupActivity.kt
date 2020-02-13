package com.example.vardaandriver.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cabservice.R
import com.example.cabservice.data.appdb.AppDatabase
import com.example.cabservice.databinding.ActivitySignupBinding
import com.example.cabservice.ui.auth.*
import com.example.vardaandriver.util.hide
import com.example.vardaandriver.util.show
import com.example.vardaandriver.util.snackbar
import com.example.vardaandriver.util.toast
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(),SignAuthListner,KodeinAware {

    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        val binding: ActivitySignupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.signupModel = viewModel
        viewModel.signAuthListner = this

    }


    override fun onStarted() {
        toast("Sign up started")
        progress_bar1.show()
    }


    override fun onSuccess(signupResponse: LiveData<String>) {
//        signup_root_layout.snackbar("Succes")
        progress_bar1.hide()
        signupResponse.observe(this, Observer {
            toast(it)
            signup_root_layout.snackbar(it)

        })
    }


    override fun onFailure(msg: String) {
        signup_root_layout.snackbar(msg)
        progress_bar1.hide()
        Log.e("onFailure",msg)
    }



}
