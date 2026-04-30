package com.example.teste3.admin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.teste3.BotaoNav.BottomNavHelper
import com.example.teste3.BookRepository
import com.example.teste3.databinding.ActivityEditarLivroBinding
import com.example.teste3.home_aluno.Book

class EditarLivroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarLivroBinding
    private var capaUri: Uri? = null
    private var capaOriginal: String = ""

    private val galeriaLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            capaUri = result.data?.data
            capaUri?.let { binding.imgCapa.setImageURI(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarLivroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Recebe os dados do livro para pré-preencher
        val oldTitle = intent.getStringExtra("book_title")  ?: ""
        val author   = intent.getStringExtra("book_author") ?: ""
        val cover    = intent.getStringExtra("book_cover")  ?: ""
        val year     = intent.getStringExtra("book_year")   ?: ""
        val genre    = intent.getStringExtra("book_genre")  ?: ""
        val status   = intent.getStringExtra("book_status") ?: ""

        capaOriginal = cover

        binding.etNome.setText(oldTitle)
        binding.etAutor.setText(author)
        binding.etAno.setText(year)
        binding.etGenero.setText(genre)
        binding.etEstado.setText(status)
        binding.imgCapa.load(cover)

        // Editar capa
        binding.imgCapa.setOnClickListener { abrirGaleria() }
        binding.fabEditCapa.setOnClickListener { abrirGaleria() }

        // Salvar alterações
        binding.btnSalvar.setOnClickListener {
            val novoNome   = binding.etNome.text.toString().trim()
            val novoAutor  = binding.etAutor.text.toString().trim()
            val novoAno    = binding.etAno.text.toString().trim()
            val novoGenero = binding.etGenero.text.toString().trim()
            val novoEstado = binding.etEstado.text.toString().trim()

            if (novoNome.isEmpty() || novoAutor.isEmpty() || novoAno.isEmpty() || novoGenero.isEmpty() || novoEstado.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val novaCapa = capaUri?.toString() ?: capaOriginal

            val livroAtualizado = Book(
                id = 0,
                title = novoNome,
                author = novoAutor,
                coverUrl = novaCapa,
                year = novoAno,
                genre = novoGenero,
                status = novoEstado
            )

            BookRepository.update(oldTitle, livroAtualizado)
            Toast.makeText(this, "Livro atualizado com sucesso!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, HomeAdminActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }

        setupBottomNav()
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galeriaLauncher.launch(intent)
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