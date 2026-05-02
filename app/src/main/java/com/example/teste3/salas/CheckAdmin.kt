package com.example.teste3.salas

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.teste3.R
import com.example.teste3.admin.AluguelAdmin
import com.example.teste3.admin.HomeAdminActivity
import com.example.teste3.admin.perfiladm
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.switchmaterial.SwitchMaterial

class CheckAdmin : AppCompatActivity() {

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
        setContentView(R.layout.activity_check_salas)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        // Switches
        val switches = listOf(
            Pair(findViewById<SwitchMaterial>(R.id.switchTurno1), findViewById<ImageView>(R.id.iconTurno1)),
            Pair(findViewById<SwitchMaterial>(R.id.switchTurno2), findViewById<ImageView>(R.id.iconTurno2)),
            Pair(findViewById<SwitchMaterial>(R.id.switchTurno3), findViewById<ImageView>(R.id.iconTurno3)),
            Pair(findViewById<SwitchMaterial>(R.id.switchTurno4), findViewById<ImageView>(R.id.iconTurno4)),
            Pair(findViewById<SwitchMaterial>(R.id.switchTurno5), findViewById<ImageView>(R.id.iconTurno5))
        )

        switches.forEach { (switch, icon) ->
            atualizarIcone(switch.isChecked, icon)
            switch.setOnCheckedChangeListener { _, isChecked ->
                atualizarIcone(isChecked, icon)
            }
        }

        findViewById<MaterialButton>(R.id.btnSalvar).setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Sucesso")
                .setMessage("Salas reservadas com sucesso!")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }

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
            startActivity(Intent(this, AdmSalas::class.java))
        }
        findViewById<LinearLayout>(R.id.navSalas)?.setOnClickListener {
            startActivity(Intent(this, AdmSalas::class.java))
        }
        findViewById<LinearLayout>(R.id.navPerfil)?.setOnClickListener {
            startActivity(Intent(this, perfiladm::class.java))
        }
    }

    private fun setNavAtivo(ativo: String) {
        navItens.forEach { (item, nav) ->
            val selecionado = item == ativo
            findViewById<LinearLayout>(nav.layoutId)?.isSelected = selecionado
            findViewById<ImageView>(nav.iconId)?.isSelected = selecionado
        }
    }

    private fun atualizarIcone(ativo: Boolean, icon: ImageView) {
        val cor = if (ativo) {
            ContextCompat.getColor(this, R.color.gold)
        } else {
            ContextCompat.getColor(this, R.color.bottom_nav_icon_color)
        }
        icon.setColorFilter(cor)
    }
}