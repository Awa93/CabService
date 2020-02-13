package com.example.cabservice.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cabservice.R
import com.example.cabservice.data.appdb.entity.User_Detail
import com.example.cabservice.databinding.ActivityLoginBinding
import com.example.cabservice.ui.home.MapsActivity
import com.example.vardaandriver.util.hide
import com.example.vardaandriver.util.show
import com.example.vardaandriver.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(),AuthListner,KodeinAware {

    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel=ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.loginModel=viewModel
        viewModel.authListner=this

        viewModel.getLoggedInuser().observe(this, Observer { user->
            if (user!=null){
                startActivity(Intent(this,MapsActivity::class.java).also {
                    it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(userDetail: User_Detail) {
        progress_bar.hide()
        login_root_layout.snackbar("${userDetail.FullName} is Logged in")
        Log.d("LoginActivity","onSuccess ${userDetail.FullName}")

    }

    override fun onFailure(msg: String) {
        progress_bar.hide()
//        toast(msg)
        login_root_layout.snackbar(msg)
        Log.e("onFailure",msg)
    }
}
