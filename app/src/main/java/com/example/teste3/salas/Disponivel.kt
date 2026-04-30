package com.example.teste3.salas

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.teste3.R
import com.example.teste3.home_aluno.HomeActivity
import com.example.teste3.login.ChatbotActivity
import com.example.teste3.perfil.MainActivity as PerfilActivity

class Disponivel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_disponivel)

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
            insets
        }

        // Salas LIVRES — vão para tela de horários
        findViewById<LinearLayout>(R.id.cardSalaA).setOnClickListener {
            abrirHorarios("Sala A", "Andar 1", "6")
        }
        findViewById<LinearLayout>(R.id.cardSalaB).setOnClickListener {
            abrirHorarios("Sala B", "Andar 1", "8")
        }
        findViewById<LinearLayout>(R.id.cardSalaE).setOnClickListener {
            abrirHorarios("Sala E", "Andar 1", "4")
        }

        // Salas OCUPADAS — vão para tela de detalhe
        findViewById<LinearLayout>(R.id.cardSalaC).setOnClickListener {
            abrirDetalhe("Sala C", "Andar 2", "4", "OCUPADA")
        }
        findViewById<LinearLayout>(R.id.cardSalaD).setOnClickListener {
            abrirDetalhe("Sala D", "Andar 2", "10", "OCUPADA")
        }

        // Menu inferior
        setNavAtivo("salas")

        findViewById<LinearLayout>(R.id.navMenu).setOnClickListener {
            startActivity(Intent(this, ChatbotActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.navSalas).setOnClickListener {
            // ja estamos na tela de salas
        }
        findViewById<LinearLayout>(R.id.navReservas).setOnClickListener {
            Toast.makeText(this, "Em breve", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.navPerfil).setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }
    }

    private fun abrirHorarios(nomeSala: String, andar: String, capacidade: String) {
        val intent = Intent(this, salahorarios::class.java)
        intent.putExtra("SALA_NOME", nomeSala)
        intent.putExtra("SALA_ANDAR", andar)
        intent.putExtra("SALA_CAPACIDADE", capacidade)
        startActivity(intent)
    }

    private fun abrirDetalhe(nomeSala: String, andar: String, capacidade: String, status: String) {
        val intent = Intent(this, saladetalhe::class.java)
        intent.putExtra("SALA_NOME", nomeSala)
        intent.putExtra("SALA_ANDAR", andar)
        intent.putExtra("SALA_CAPACIDADE", capacidade)
        intent.putExtra("SALA_STATUS", status)
        startActivity(intent)
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