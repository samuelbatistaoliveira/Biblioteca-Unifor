package com.example.teste3.admin

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teste3.R

class AluguelAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aluguel_admin)

        // Cards de solicitação
        findViewById<LinearLayout>(R.id.cardSolicitacao1)?.setOnClickListener {
            startActivity(Intent(this, solicitacao::class.java))
        }
        findViewById<LinearLayout>(R.id.cardSolicitacao2)?.setOnClickListener {
            startActivity(Intent(this, solicitacao::class.java))
        }
        findViewById<LinearLayout>(R.id.cardSolicitacao3)?.setOnClickListener {
            startActivity(Intent(this, solicitacao::class.java))
        }
        findViewById<LinearLayout>(R.id.cardDevolucao1)?.setOnClickListener {
            startActivity(Intent(this, Devolucao_admin::class.java))
        }
        findViewById<LinearLayout>(R.id.cardDevolucao2)?.setOnClickListener {
            startActivity(Intent(this, Devolucao_admin::class.java))
        }

        // Bottom nav
        findViewById<LinearLayout>(R.id.navChat)?.setOnClickListener { }
        findViewById<LinearLayout>(R.id.navHome)?.setOnClickListener {
            startActivity(Intent(this, HomeAdminActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            })
        }
        findViewById<LinearLayout>(R.id.navCalendar)?.setOnClickListener {
            startActivity(Intent(this, com.example.teste3.salas.AdmSalas::class.java))
        }
        findViewById<LinearLayout>(R.id.navCategories)?.setOnClickListener {
            Toast.makeText(this, "Categorias", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.navProfile)?.setOnClickListener {
            startActivity(Intent(this, perfiladm::class.java))
        }

        setNavAtivo("chat")
    }

    private fun setNavAtivo(ativo: String) {
        val itens = mapOf(
            "chat"       to Pair(R.id.navChat,       R.id.iconChat),
            "home"       to Pair(R.id.navHome,       R.id.iconHome),
            "calendar"   to Pair(R.id.navCalendar,   R.id.iconCalendar),
            "categories" to Pair(R.id.navCategories, R.id.iconCategories),
            "profile"    to Pair(R.id.navProfile,    R.id.iconProfile)
        )

        itens.forEach { (item, pair) ->
            val layout = findViewById<LinearLayout>(pair.first) ?: return@forEach
            val icon   = findViewById<ImageView>(pair.second)   ?: return@forEach
            val selecionado = item == ativo
            layout.isSelected = selecionado
            icon.isSelected   = selecionado
        }
    }
}