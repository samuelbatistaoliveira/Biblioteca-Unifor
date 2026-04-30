package com.example.teste3.home_aluno

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.teste3.R
import com.example.teste3.databinding.ActivityHomeBinding
import com.example.teste3.detalhe_livro.BookDetailActivity
import com.example.teste3.login.ChatbotActivity
import com.example.teste3.perfil.MainActivity as PerfilActivity
import com.example.teste3.salas.Disponivel
import com.example.teste3.mapa.MapaBibliotecaActivity

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val coverUrl: String,
    val year: String = "-",
    val genre: String = "-",
    val status: String = "Disponível",
)

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvUserName.text = "Olá, Narak!"

        binding.searchBar.setOnClickListener {
            Toast.makeText(this, "Pesquisar...", Toast.LENGTH_SHORT).show()
        }

        setupBottomNav()
        loadBooks(sampleBooks())
    }

    private fun setupBottomNav() {
        binding.bottomNavigation.selectedItemId = R.id.nav_home

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_chat -> {
                    startActivity(Intent(this, ChatbotActivity::class.java))
                    true
                }
                R.id.nav_home -> true
                R.id.nav_calendar -> {
                    startActivity(Intent(this, Disponivel::class.java))
                    true
                }
                R.id.nav_categories -> {
                    startActivity(Intent(this, MapaBibliotecaActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
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
                val intent = Intent(this, BookDetailActivity::class.java).apply {
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

    private fun sampleBooks(): List<Book> = listOf(
        Book(1, "Entendendo Algoritmos", "Aditya Y. Bhargava",
            "android.resource://com.example.teste3/drawable/capa_entendendo_algoritmos",
            year = "2017", genre = "Tecnologia"),
        Book(2, "Pai Rico Pai Pobre", "Robert T. Kiyosaki",
            "android.resource://com.example.teste3/drawable/capa_pai_rico_pai_pobre",
            year = "2000", genre = "Finanças"),
        Book(3, "É Assim Que Acaba", "Colleen Hoover",
            "android.resource://com.example.teste3/drawable/capa_assim_que_acaba",
            year = "2016", genre = "Romance"),
        Book(4, "Netter Atlas de Anatomia", "Frank H. Netter",
            "android.resource://com.example.teste3/drawable/capa_anatomia_humana",
            year = "2015", genre = "Medicina"),
        Book(5, "Introdução à Nutrição", "Vários Autores",
            "android.resource://com.example.teste3/drawable/capa_introducao_a_nutricao",
            year = "2018", genre = "Nutrição"),
        Book(6, "Fisiologia Humana", "Dee Unglaub Silverthorn",
            "android.resource://com.example.teste3/drawable/capa_fisiologia_humana",
            year = "2017", genre = "Medicina"),
        Book(7, "Fundamentos de Enfermagem", "Patricia A. Potter",
            "android.resource://com.example.teste3/drawable/capa_fundamentos_de_enfermagem",
            year = "2018", genre = "Enfermagem"),
        Book(8, "Código Limpo", "Robert C. Martin",
            "https://covers.openlibrary.org/b/isbn/9788576082675-L.jpg",
            year = "2009", genre = "Tecnologia"),
        Book(9, "Django Essencial", "Vários Autores",
            "android.resource://com.example.teste3/drawable/capa_django_essencial",
            year = "2020", genre = "Tecnologia"),
    )
}