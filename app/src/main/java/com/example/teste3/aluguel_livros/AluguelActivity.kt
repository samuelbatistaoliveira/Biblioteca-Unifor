package com.example.teste3.aluguel_livros

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.teste3.R
import com.example.teste3.databinding.ActivityAluguelBinding
import com.example.teste3.home_aluno.HomeActivity
import com.example.teste3.login.ChatbotActivity
import com.example.teste3.perfil.MainActivity as PerfilActivity

class AluguelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAluguelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAluguelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title    = intent.getStringExtra("book_title")  ?: "Sem título"
        val author   = intent.getStringExtra("book_author") ?: "Autor desconhecido"
        val coverUrl = intent.getStringExtra("book_cover")  ?: ""
        val year     = intent.getStringExtra("book_year")   ?: "-"
        val genre    = intent.getStringExtra("book_genre")  ?: "-"
        val status   = intent.getStringExtra("book_status") ?: "Disponível"

        binding.tvBookTitle.text  = title
        binding.tvBookAuthor.text = author
        binding.tvYear.text       = year
        binding.tvGenre.text      = genre
        binding.tvStatus.text     = status
        binding.ivBookCover.load(coverUrl)

        binding.tvStatus.setTextColor(
            if (status == "Disponível")
                getColor(android.R.color.holo_green_dark)
            else
                getColor(android.R.color.holo_red_dark)
        )

        binding.btnBack.setOnClickListener { finish() }

        binding.btnConfirm.setOnClickListener {
            Toast.makeText(this, "Retirada confirmada! Retire na Biblioteca da Unifor.", Toast.LENGTH_LONG).show()
            finish()
        }

        binding.btnCancel.setOnClickListener {
            Toast.makeText(this, "Aluguel cancelado.", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.bottomNavigation.selectedItemId = R.id.nav_home
        binding.bottomNavigation.setOnItemSelectedListener { item ->
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
                R.id.nav_profile -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> {
                    Toast.makeText(this, item.title ?: "Nav", Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }
}