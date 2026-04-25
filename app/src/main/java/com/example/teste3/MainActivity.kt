package com.example.teste3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.teste3.admin.HomeAdminActivity
import com.example.teste3.login.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, HomeAdminActivity::class.java))
        finish()
    }
}