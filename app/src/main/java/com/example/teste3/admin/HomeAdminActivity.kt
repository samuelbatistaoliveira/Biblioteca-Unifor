package com.example.teste3.admin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.teste3.BookRepository
import com.example.teste3.R
import com.example.teste3.databinding.ActivityHomeAdminBinding
import com.example.teste3.home_aluno.Book
import com.example.teste3.login.ChatbotActivity

class HomeAdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvUserName.text = "Olá, Admin!"

        binding.searchBar.setOnClickListener {
            Toast.makeText(this, "Pesquisar...", Toast.LENGTH_SHORT).show()
        }

        binding.fabAddBook.setOnClickListener {
            startActivity(Intent(this, CadastroLivroActivity::class.java))
        }

        setupBottomNav()
        loadBooks(BookRepository.getAll())
    }

    override fun onResume() {
        super.onResume()
        loadBooks(BookRepository.getAll())
    }

    private fun setupBottomNav() {
        binding.bottomNavigation.selectedItemId = R.id.nav_home

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_chat -> {
                    true
                }
                R.id.nav_home -> true
                R.id.nav_calendar -> {
                    startActivity(Intent(this, com.example.teste3.salas.AdmSalas::class.java))
                    true
                }
                R.id.nav_categories -> {
                    Toast.makeText(this, "Categorias", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, perfiladm::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun loadBooks(books: List<Book>) {
        val grid = binding.gridBooks
        grid.removeAllViews()

        val screenWidth = resources.displayMetrics.widthPixels
        val padding = (32 * resources.displayMetrics.density).toInt()
        val gap = (10 * resources.displayMetrics.density).toInt()
        val itemWidth = (screenWidth - padding - gap * 2) / 3

        books.forEach { book ->
            val itemView = LayoutInflater.from(this)
                .inflate(R.layout.item_book, grid, false)

            val params = android.widget.GridLayout.LayoutParams().apply {
                width  = itemWidth
                height = android.widget.GridLayout.LayoutParams.WRAP_CONTENT
                setMargins(gap / 2, 0, gap / 2, (14 * resources.displayMetrics.density).toInt())
            }
            itemView.layoutParams = params

            itemView.findViewById<ImageView>(R.id.imgCover).load(book.coverUrl)
            itemView.findViewById<TextView>(R.id.tvCoverTitle).text = book.title
            itemView.findViewById<TextView>(R.id.tvBookName).text = book.title

            itemView.setOnClickListener {
                val intent = Intent(this, DetalhesAdminActivity::class.java).apply {
                    putExtra("book_title",  book.title)
                    putExtra("book_author", book.author)
                    putExtra("book_cover",  book.coverUrl)
                    putExtra("book_year",   book.year)
                    putExtra("book_genre",  book.genre)
                    putExtra("book_status", book.status)
                }
                startActivity(intent)
            }

            grid.addView(itemView)
        }
    }
}