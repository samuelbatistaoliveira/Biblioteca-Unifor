package com.example.teste3.admin

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.teste3.R


class perfiladm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_adm)

        // Botão lápis → vai para Editar Perfil
        val btnEditar = findViewById<FrameLayout>(R.id.btnEditarPerfil)
        btnEditar.setOnClickListener {
            startActivity(Intent(this, editarperfiladm::class.java))
        }

        // Botão engrenagem → vai para Configurações
        val btnConfig = findViewById<FrameLayout>(R.id.btnConfiguracoes)
        btnConfig.setOnClickListener {
            startActivity(Intent(this, configadm::class.java))
        }

        val navMenu = findViewById<LinearLayout>(R.id.navMenu)
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navCalendar = findViewById<LinearLayout>(R.id.navCalendar)
        val navSignpost = findViewById<LinearLayout>(R.id.navSignpost)
        val navPerfil = findViewById<LinearLayout>(R.id.navPerfil)

        val allNavItems = listOf(navMenu, navHome, navCalendar, navSignpost, navPerfil)
        navPerfil.isSelected = true

        allNavItems.forEach { item ->
            item.setOnClickListener {
                allNavItems.forEach { it.isSelected = false }
                item.isSelected = true
            }
        }
    }
}