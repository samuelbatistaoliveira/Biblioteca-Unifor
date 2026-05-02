package com.example.teste3.salas

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.teste3.R
import com.example.teste3.admin.AluguelAdmin
import com.example.teste3.admin.HomeAdminActivity
import com.example.teste3.admin.perfiladm

class AdmSalas : AppCompatActivity() {

    private data class NavItem(val layoutId: Int, val iconId: Int)

    private val navItens = mapOf(
        "menu"     to NavItem(R.id.navChat,     R.id.iconChat),
        "home"     to NavItem(R.id.navHome,     R.id.iconHome),
        "reservas" to NavItem(R.id.navReservas, R.id.iconReservas),
        "salas"    to NavItem(R.id.navSalas,    R.id.iconSalas),
        "perfil"   to NavItem(R.id.navPerfil,   R.id.iconPerfil)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admsalas)

        setNavAtivo("reservas")

        findViewById<LinearLayout>(R.id.navChat)?.setOnClickListener {
            startActivity(Intent(this, AluguelAdmin::class.java))
        }
        findViewById<LinearLayout>(R.id.navHome)?.setOnClickListener {
            startActivity(Intent(this, HomeAdminActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            })
        }
        findViewById<LinearLayout>(R.id.navReservas)?.setOnClickListener {
            // já está nesta tela
        }
        findViewById<LinearLayout>(R.id.navSalas)?.setOnClickListener {
            // já está nesta tela
        }
        findViewById<LinearLayout>(R.id.navPerfil)?.setOnClickListener {
            startActivity(Intent(this, perfiladm::class.java))
        }

        val irParaCheck = { startActivity(Intent(this, CheckAdmin::class.java)) }

        findViewById<LinearLayout>(R.id.cardSalaA)?.setOnClickListener { irParaCheck() }
        findViewById<LinearLayout>(R.id.cardSalaB)?.setOnClickListener { irParaCheck() }
        findViewById<LinearLayout>(R.id.cardSalaC)?.setOnClickListener { irParaCheck() }
        findViewById<LinearLayout>(R.id.cardSalaD)?.setOnClickListener { irParaCheck() }
        findViewById<LinearLayout>(R.id.cardSalaE)?.setOnClickListener { irParaCheck() }
    }

    private fun setNavAtivo(ativo: String) {
        navItens.forEach { (item, nav) ->
            val selecionado = item == ativo
            findViewById<LinearLayout>(nav.layoutId)?.isSelected = selecionado
            findViewById<ImageView>(nav.iconId)?.isSelected = selecionado
        }
    }
}