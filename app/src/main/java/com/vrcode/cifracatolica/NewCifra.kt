@file:OptIn(DelicateCoroutinesApi::class)

package com.vrcode.cifracatolica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vrcode.cifracatolica.R.layout.activity_new_cifra
import com.vrcode.cifracatolica.database.DatabaseBuilder
import com.vrcode.cifracatolica.model.Cifra
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewCifra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_new_cifra)

        // Referenciar os campos
        val edtTitulo = findViewById<EditText>(R.id.edTituloNewCifra)
        val edtCantor = findViewById<EditText>(R.id.edCantorNewCifra)
        val edtLetra = findViewById<EditText>(R.id.edTextoCifraNewCifra)
        val btnSalvar = findViewById<Button>(R.id.btSalvarNewCifra)
        val edtCompositor = findViewById<EditText>(R.id.edCompositorNewCifra)
        val edtCategoria = findViewById<EditText>(R.id.edCategoriaNewCifra)

        // Ação ao clicar no botão Salvar
        btnSalvar.setOnClickListener {
            val titulo = edtTitulo.text.toString().trim()
            val cantor = edtCantor.text.toString().trim()
            val letra = edtLetra.text.toString().trim()
            val compositor = edtCompositor.text.toString().trim()
            val categoria = edtCategoria.text.toString().trim()
            val image = "default_image"

            if (titulo.isEmpty() || cantor.isEmpty() || letra.isEmpty()) {
                Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show()
            } else {
                salvarCifra(titulo, cantor, letra, compositor, categoria, image)
                Toast.makeText(this, "Cifra salva com sucesso!", Toast.LENGTH_SHORT).show()
                finish() // Volta para a tela anterior
            }
        }
    }

    private fun salvarCifra(titulo: String, cantor: String, letra: String, compositor: String, categoria: String, image: String) {
        val novaCifra = Cifra(titulo = titulo, cantor = cantor, letra = letra, compositor = compositor, categoria = categoria, image = image)

        GlobalScope.launch {
            val db = DatabaseBuilder.getDatabase(applicationContext)
            db.cifraDao().inserir(novaCifra)
        }
    }
}
