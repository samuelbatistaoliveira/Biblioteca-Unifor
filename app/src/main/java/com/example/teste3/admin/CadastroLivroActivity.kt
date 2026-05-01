package com.example.teste3.admin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.teste3.BookRepository
import com.example.teste3.BotaoNav.BottomNavHelper
import com.example.teste3.databinding.ActivityCadastroLivroBinding
import com.example.teste3.home_aluno.Book
import com.example.teste3.admin.HomeAdminActivity

class CadastroLivroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroLivroBinding
    private var capaUri: Uri? = null

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
        binding = ActivityCadastroLivroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.imgCapa.setOnClickListener { abrirGaleria() }
        binding.fabEditCapa.setOnClickListener { abrirGaleria() }

        binding.btnAdicionar.setOnClickListener {
            val nome   = binding.etNome.text.toString().trim()
            val autor  = binding.etAutor.text.toString().trim()
            val ano    = binding.etAno.text.toString().trim()
            val codigo = binding.etCodigo.text.toString().trim()

            if (nome.isEmpty() || autor.isEmpty() || ano.isEmpty() || codigo.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val novoLivro = Book(
                id       = BookRepository.getAll().size + 1,
                title    = nome,
                author   = autor,
                coverUrl = capaUri?.toString() ?: "",
                year     = ano,
                genre    = codigo
            )
            BookRepository.add(novoLivro)

            Toast.makeText(this, "Livro \"$nome\" cadastrado com sucesso!", Toast.LENGTH_LONG).show()

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