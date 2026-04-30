package com.example.teste3.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teste3.R
import com.example.teste3.databinding.ActivityChatbotBinding
import com.example.teste3.home_aluno.HomeActivity
import com.example.teste3.perfil.MainActivity as PerfilActivity

class ChatbotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatbotBinding
    private lateinit var adapter: MensagensAdapter
    private val mensagens = mutableListOf<Mensagem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MensagensAdapter(mensagens)
        binding.rvMensagens.layoutManager = LinearLayoutManager(this).also {
            it.stackFromEnd = true
        }
        binding.rvMensagens.adapter = adapter

        adicionarMensagemBot("Olá! Sou a bibliotecária virtual. Como posso ajudar? Sugiro que pergunte algo como \"Onde acho livros de Educação\"?")

        binding.btnEnviar.setOnClickListener {
            val texto = binding.etMensagem.text.toString().trim()
            if (texto.isNotEmpty()) {
                adicionarMensagemUsuario(texto)
                binding.etMensagem.setText("")
                responderBot(texto)
            }
        }

        binding.bottomNavigation.selectedItemId = R.id.nav_chat
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_chat -> true
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    })
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

    private fun adicionarMensagemBot(texto: String) {
        mensagens.add(Mensagem(texto, Mensagem.TIPO_BOT))
        adapter.notifyItemInserted(mensagens.size - 1)
        binding.rvMensagens.scrollToPosition(mensagens.size - 1)
    }

    private fun adicionarMensagemUsuario(texto: String) {
        mensagens.add(Mensagem(texto, Mensagem.TIPO_USUARIO))
        adapter.notifyItemInserted(mensagens.size - 1)
        binding.rvMensagens.scrollToPosition(mensagens.size - 1)
    }

    private fun responderBot(pergunta: String) {
        val resposta = when {
            pergunta.contains("nutrição", ignoreCase = true) ->
                "Clique no ícone de placas na barra inferior, ao abrir a tela, na barra de pesquisa procure por \"Nutrição\""
            pergunta.contains("livro", ignoreCase = true) ->
                "Você pode buscar livros pela aba de pesquisa. Qual tema você procura?"
            else ->
                "Não entendi sua pergunta. Pode reformular?"
        }
        adicionarMensagemBot(resposta)
    }
}

data class Mensagem(val texto: String, val tipo: Int) {
    companion object {
        const val TIPO_BOT     = 0
        const val TIPO_USUARIO = 1
    }
}

class MensagensAdapter(private val mensagens: List<Mensagem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class BotViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMensagem: TextView = view.findViewById(R.id.tvMensagemBot)
    }

    class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMensagem: TextView = view.findViewById(R.id.tvMensagemUsuario)
    }

    override fun getItemViewType(position: Int) = mensagens[position].tipo

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Mensagem.TIPO_BOT) {
            BotViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mensagem_bot, parent, false))
        } else {
            UsuarioViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mensagem_usuario, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mensagem = mensagens[position]
        when (holder) {
            is BotViewHolder     -> holder.tvMensagem.text = mensagem.texto
            is UsuarioViewHolder -> holder.tvMensagem.text = mensagem.texto
        }
    }

    override fun getItemCount() = mensagens.size
}