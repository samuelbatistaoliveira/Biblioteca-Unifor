package com.example.teste3.perfil

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.teste3.R


class multas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multas)

        // ── Marca o item "Perfil" como selecionado no menu ──
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = R.id.nav_profile

        // Botão voltar → retorna para a tela de Perfil
        val btnVoltar = findViewById<ImageButton>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}