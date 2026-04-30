package com.example.teste3.mapa

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge
import com.example.teste3.R

class MapaBibliotecaActivity : AppCompatActivity() {

    private val secaoMap = mapOf(
        "tecnologia" to "Seção Tecnologia  •  Corredor 1",
        "finanças"   to "Seção Finanças  •  Corredor 2",
        "financas"   to "Seção Finanças  •  Corredor 2",
        "romance"    to "Seção Romance  •  Corredor 3",
        "medicina"   to "Seção Medicina  •  Corredor 4",
        "nutrição"   to "Seção Nutrição  •  Corredor 5",
        "nutricao"   to "Seção Nutrição  •  Corredor 5",
        "enfermagem" to "Seção Enfermagem  •  Corredor 6"
    )

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
        enableEdgeToEdge()
        setContentView(R.layout.activity_mapa)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupBusca()
        setupBottomNav()
    }

    private fun setupBusca() {
        val etBusca      = findViewById<EditText>(R.id.etBuscarLivro)
        val layoutResult = findViewById<LinearLayout>(R.id.layoutResultado)
        val tvResultado  = findViewById<TextView>(R.id.tvResultado)

        etBusca.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                layoutResult.visibility = android.view.View.GONE
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        etBusca.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = etBusca.text.toString().trim().lowercase()
                val secao = secaoMap.entries
                    .firstOrNull { (chave, _) -> query.contains(chave) }
                    ?.value

                tvResultado.text = if (secao != null) {
                    "Localizado em: $secao"
                } else {
                    "Livro não encontrado no acervo."
                }
                layoutResult.visibility = android.view.View.VISIBLE
                true
            } else false
        }
    }

    private fun setupBottomNav() {
        navItens.forEach { (item, nav) ->
            findViewById<LinearLayout>(nav.layoutId)?.setOnClickListener {
                setNavAtivo(item)
                when (item) {
                    "menu"     -> startActivity(Intent(this, com.example.teste3.login.ChatbotActivity::class.java))
                    "home"     -> startActivity(Intent(this, com.example.teste3.home_aluno.HomeActivity::class.java))
                    "reservas" -> startActivity(Intent(this, com.example.teste3.salas.Disponivel::class.java))
                    "perfil"   -> startActivity(Intent(this, com.example.teste3.perfil.MainActivity::class.java))
                    // "salas" — adicione quando tiver a tela correspondente
                }
            }
        }
    }

    private fun setNavAtivo(ativo: String) {
        navItens.forEach { (item, nav) ->
            val selecionado = item == ativo
            findViewById<LinearLayout>(nav.layoutId)?.isSelected = selecionado
            findViewById<ImageView>(nav.iconId)?.isSelected = selecionado
        }
    }
}