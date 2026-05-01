package com.example.teste3.salas

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.teste3.R
import com.example.teste3.home_aluno.HomeActivity
import com.example.teste3.login.ChatbotActivity
import com.example.teste3.mapa.MapaBibliotecaActivity
import com.example.teste3.perfil.PrincipalPerfil as PerfilActivity
import androidx.core.widget.ImageViewCompat
import androidx.core.content.ContextCompat
import android.content.res.ColorStateList

class saladetalhe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_saladetalhe)

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
            insets
        }

        val nomeSala   = intent.getStringExtra("SALA_NOME")       ?: "Sala"
        val andar      = intent.getStringExtra("SALA_ANDAR")      ?: ""
        val capacidade = intent.getStringExtra("SALA_CAPACIDADE") ?: ""
        val status     = intent.getStringExtra("SALA_STATUS")     ?: "OCUPADA"

        findViewById<TextView>(R.id.tvNomeSala)?.text    = nomeSala
        findViewById<TextView>(R.id.tvAndar)?.text       = andar
        findViewById<TextView>(R.id.tvCapacidade)?.text  = "$capacidade pessoas"
        findViewById<TextView>(R.id.tvLocalizacao)?.text = andar
        findViewById<TextView>(R.id.tvBadge)?.text       = status

        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        tvStatus?.text = if (status == "OCUPADA") "Ocupada" else "Disponivel"
        val corStatus = if (status == "OCUPADA") 0xFFB00020.toInt() else 0xFF4CAF50.toInt()
        tvStatus?.setTextColor(corStatus)

        findViewById<ImageView>(R.id.btnVoltar)?.setOnClickListener {
            finish()
        }



        setNavAtivo("reservas")

        ImageViewCompat.setImageTintList(
            findViewById(R.id.iconReservas),
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gold))
        )

        findViewById<LinearLayout>(R.id.navMenu)?.setOnClickListener {
            startActivity(Intent(this, ChatbotActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.navHome)?.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.navSalas).setOnClickListener {
            startActivity(Intent(this, MapaBibliotecaActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.navReservas).setOnClickListener {
            startActivity(Intent(this, Disponivel::class.java))
        }
        findViewById<LinearLayout>(R.id.navPerfil)?.setOnClickListener {
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