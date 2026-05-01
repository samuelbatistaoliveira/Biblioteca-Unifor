package com.example.teste3.perfil

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.teste3.R
import com.example.teste3.home_aluno.HomeActivity
import com.example.teste3.login.ChatbotActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PrincipalPerfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = R.id.nav_profile

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    })
                    true
                }
                R.id.nav_chat -> {
                    startActivity(Intent(this, ChatbotActivity::class.java))
                    true
                }
                R.id.nav_calendar -> {
                    startActivity(Intent(this, com.example.teste3.salas.Disponivel::class.java))
                    true
                }
                R.id.nav_categories -> {
                    startActivity(Intent(this, com.example.teste3.mapa.MapaBibliotecaActivity::class.java))
                    true
                }
                R.id.nav_profile -> true
                else -> false
            }
        }

        findViewById<FrameLayout>(R.id.btnEditarPerfil).setOnClickListener {
            startActivity(Intent(this, EditarPerfil::class.java))
        }

        findViewById<FrameLayout>(R.id.btnConfiguracoes).setOnClickListener {
            startActivity(Intent(this, configuracoes::class.java))
        }

        findViewById<FrameLayout>(R.id.btnMultas).setOnClickListener {
            startActivity(Intent(this, multas::class.java))
        }

        findViewById<ImageView>(R.id.ivArrowBook1).setOnClickListener {
            startActivity(Intent(this, multas::class.java))
        }
    }
}