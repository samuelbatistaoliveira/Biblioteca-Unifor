package com.example.teste3

import com.example.teste3.home_aluno.Book

object BookRepository {

    private val books = mutableListOf(
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

    fun getAll(): List<Book> = books.toList()

    fun add(book: Book) {
        books.add(book)
    }

    fun remove(title: String) {
        books.removeAll { it.title == title }
    }

    fun update(oldTitle: String, updatedBook: Book) {
        val index = books.indexOfFirst { it.title == oldTitle }
        if (index != -1) books[index] = updatedBook
    }
}