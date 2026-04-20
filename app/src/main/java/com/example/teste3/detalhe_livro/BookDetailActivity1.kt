package com.example.teste3.detalhe_livro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.teste3.BotaoNav.BottomNavHelper
import com.example.teste3.R
import com.example.teste3.aluguel_livros.AluguelActivity
import com.example.teste3.databinding.ActivityBookDetailBinding
import com.example.teste3.home_aluno.HomeActivity

class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
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
        binding.imgBookCover.load(coverUrl)

        binding.tvStatus.setTextColor(
            if (status == "Disponível")
                getColor(android.R.color.holo_green_dark)
            else
                getColor(android.R.color.holo_red_dark)
        )

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnRent.setOnClickListener {
            if (status != "Disponível") {
                Toast.makeText(this, "Livro indisponível para aluguel", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, AluguelActivity::class.java).apply {
                putExtra("book_title",  title)
                putExtra("book_author", author)
                putExtra("book_cover",  coverUrl)
                putExtra("book_year",   year)
                putExtra("book_genre",  genre)
                putExtra("book_status", "Alugado")
            }
            startActivity(intent)
        }

        BottomNavHelper.setup(
            context         = this,
            navChat         = binding.bottomNav.navChat,
            navChatBg       = binding.bottomNav.navChatBg,
            navHome         = binding.bottomNav.navHome,
            navHomeBg       = binding.bottomNav.navHomeBg,
            navCalendar     = binding.bottomNav.navCalendar,
            navCalendarBg   = binding.bottomNav.navCalendarBg,
            navCategories   = binding.bottomNav.navCategories,
            navCategoriesBg = binding.bottomNav.navCategoriesBg,
            navProfile      = binding.bottomNav.navProfile,
            navProfileBg    = binding.bottomNav.navProfileBg,
            activeItem      = BottomNavHelper.NavItem.HOME
        )
    }
}