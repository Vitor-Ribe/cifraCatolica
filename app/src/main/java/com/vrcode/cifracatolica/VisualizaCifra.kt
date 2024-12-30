package com.vrcode.cifracatolica

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.vrcode.cifracatolica.database.AppDatabase
import com.vrcode.cifracatolica.model.Cifra
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VisualizaCifra : AppCompatActivity() {
    private lateinit var tvTitulo: TextView
    private lateinit var tvCantor: TextView
    private lateinit var tvLetra: TextView
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualiza_cifra)

        // Inicializar os elementos do layout
        tvTitulo = findViewById(R.id.tvTitulo)
        tvCantor = findViewById(R.id.tvCantor)
        tvLetra = findViewById(R.id.tvLetra)

        // Inicializar o banco de dados
        database = AppDatabase.getInstance(this)

        // Obter o ID da cifra passado pela Intent
        val cifraId = intent.getIntExtra("CIFRA_ID", -1)

        if (cifraId != -1) {
            // Carregar os dados da cifra
            loadCifra(cifraId)
        } else {
            Toast.makeText(this, "Erro ao carregar a cifra", Toast.LENGTH_SHORT).show()
            finish() // Encerrar a Activity se o ID for inválido
        }
    }

    private fun loadCifra(cifraId: Int) {
        lifecycleScope.launch {
            val cifra: Cifra? = withContext(Dispatchers.IO) {
                database.cifraDao().getCifraById(cifraId)
            }

            if (cifra != null) {
                // Atualizar os textos do layout com os dados da cifra
                tvTitulo.text = cifra.titulo
                tvCantor.text = cifra.cantor
                tvLetra.text = cifra.letra
            } else {
                Toast.makeText(this@VisualizaCifra, "Cifra não encontrada", Toast.LENGTH_SHORT).show()
                finish() // Encerrar a Activity se a cifra não for encontrada
            }
        }
    }
}
