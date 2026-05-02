package com.example.teste3.salas

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.teste3.R
import com.example.teste3.home_aluno.HomeActivity
import com.example.teste3.login.ChatbotActivity
import com.example.teste3.mapa.MapaPrincipal
import com.example.teste3.perfil.PrincipalPerfil as PerfilActivity

class salahorarios : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_salahorarios)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nomeSala   = intent.getStringExtra("SALA_NOME")       ?: "Sala"
        val andar      = intent.getStringExtra("SALA_ANDAR")      ?: ""
        val capacidade = intent.getStringExtra("SALA_CAPACIDADE") ?: ""

        findViewById<TextView>(R.id.tvSalaTitulo).text = "$nomeSala, $andar"
        findViewById<TextView>(R.id.tvCapacidade).text = "Cap. $capacidade pessoas"

        setNavAtivo("reservas")

        findViewById<LinearLayout>(R.id.navMenu).setOnClickListener {
            startActivity(Intent(this, ChatbotActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.navSalas).setOnClickListener {
            startActivity(Intent(this, MapaPrincipal::class.java))
        }
        findViewById<LinearLayout>(R.id.navReservas).setOnClickListener {
            startActivity(Intent(this, Disponivel::class.java))
        }
        findViewById<LinearLayout>(R.id.navPerfil).setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }
    }

    private fun setNavAtivo(ativo: String) {
        data class NavItem(val layoutId: Int, val iconId: Int)

        val itens = mapOf(
            "chat"     to NavItem(R.id.navMenu,     R.id.iconChat),
            "home"     to NavItem(R.id.navHome,     R.id.iconHome),
            "salas"    to NavItem(R.id.navSalas,    R.id.iconSalas),
            "reservas" to NavItem(R.id.navReservas, R.id.iconReservas),
            "perfil"   to NavItem(R.id.navPerfil,   R.id.iconPerfil)
        )

        itens.forEach { (item, nav) ->
            val layout = findViewById<LinearLayout>(nav.layoutId) ?: return@forEach
            val icon   = findViewById<ImageView>(nav.iconId)      ?: return@forEach

            val selecionado = item == ativo
            layout.isSelected = selecionado
            icon.isSelected   = selecionado
        }
    }
}