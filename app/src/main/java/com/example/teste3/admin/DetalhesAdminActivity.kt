package com.example.teste3.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.teste3.BotaoNav.BottomNavHelper
import com.example.teste3.databinding.ActivityDetalhesAdminBinding

class DetalhesAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        val title  = intent.getStringExtra("book_title")  ?: ""
        val author = intent.getStringExtra("book_author") ?: ""
        val cover  = intent.getStringExtra("book_cover")  ?: ""
        val year   = intent.getStringExtra("book_year")   ?: ""
        val genre  = intent.getStringExtra("book_genre")  ?: ""
        val status = intent.getStringExtra("book_status") ?: ""

        binding.tvBookTitle.text  = title
        binding.tvBookAuthor.text = author
        binding.tvBookYear.text   = year
        binding.tvBookGenre.text  = genre
        binding.tvBookStatus.text = status
        binding.imgBookCover.load(cover)

        binding.tvBookStatus.setTextColor(
            if (status == "Disponível")
                getColor(android.R.color.holo_green_dark)
            else
                getColor(android.R.color.holo_red_dark)
        )

        binding.btnEditar.setOnClickListener {
            val intent = Intent(this, EditarLivroActivity::class.java).apply {
                putExtra("edit_mode",    true)
                putExtra("book_title",  title)
                putExtra("book_author", author)
                putExtra("book_cover",  cover)
                putExtra("book_year",   year)
                putExtra("book_genre",  genre)
                putExtra("book_status", status)
            }
            startActivity(intent)
        }

        binding.btnDeletar.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Deletar livro")
                .setMessage("Tem certeza que deseja deletar \"$title\"?")
                .setPositiveButton("Deletar") { _, _ ->
                    com.example.teste3.BookRepository.remove(title)
                    finish()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        setupBottomNav()
    }

    private fun setupBottomNav() {
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
        binding.bottomNav.navChat.setOnClickListener { }
        binding.bottomNav.navHome.setOnClickListener {
            val intent = Intent(this, HomeAdminActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
        }
    }
}