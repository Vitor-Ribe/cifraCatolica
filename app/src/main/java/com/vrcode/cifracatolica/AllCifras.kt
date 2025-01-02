package com.vrcode.cifracatolica

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrcode.cifracatolica.database.AppDatabase
import com.vrcode.cifracatolica.model.Cifra
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AllCifras : AppCompatActivity() {
    private lateinit var adapter: CifrasAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_cifras)

        database = AppDatabase.getInstance(this)

        // Configurar RecyclerView e Adapter
        adapter = CifrasAdapter(emptyList(), { cifra ->
            // Clique curto - Abre a cifra
            val intent = Intent(this, VisualizaCifra::class.java)
            intent.putExtra("CIFRA_ID", cifra.id) // Passar o ID da cifra
            startActivity(intent)
        }, { cifra ->
            // Clique longo - Exibe a confirmação de exclusão
            showDeleteConfirmation(cifra)
        })

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCifras)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        loadCifras()
    }

    // Carregar as cifras utilizando Coroutine
    private fun loadCifras() {
        lifecycleScope.launch {
            // Usar Dispatchers.IO para operação de leitura do banco de dados
            val cifras = withContext(Dispatchers.IO) {
                database.cifraDao().getAllCifras()
            }
            // Atualizar a lista na UI thread
            adapter.updateData(cifras)
        }
    }

    // Mostrar a confirmação de exclusão
    private fun showDeleteConfirmation(cifra: Cifra) {
        AlertDialog.Builder(this)
            .setTitle("Excluir Cifra")
            .setMessage("Tem certeza que deseja excluir a cifra '${cifra.titulo}'?")
            .setPositiveButton("Sim") { _, _ ->
                deleteCifra(cifra)
            }
            .setNegativeButton("Não", null)
            .show()
    }

    // Excluir a cifra
    private fun deleteCifra(cifra: Cifra) {
        lifecycleScope.launch {
            // Usar Dispatchers.IO para operação de exclusão no banco de dados
            withContext(Dispatchers.IO) {
                database.cifraDao().delete(cifra)
            }
            // Atualizar a UI após a exclusão
            loadCifras()
            Toast.makeText(this@AllCifras, "Cifra excluída", Toast.LENGTH_SHORT).show()
        }
    }
}
