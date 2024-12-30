package com.vrcode.cifracatolica

import CifrasAdapter
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
        adapter = CifrasAdapter(emptyList()) { cifra ->
            showDeleteConfirmation(cifra)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCifras)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        loadCifras()
    }

    private fun loadCifras() {
        val cifras = database.cifraDao().getAllCifras()
        adapter.updateData(cifras)
    }

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

    private fun deleteCifra(cifra: Cifra) {
        // Remover do banco de dados
        Thread {
            database.cifraDao().delete(cifra)
            runOnUiThread {
                loadCifras() // Atualizar a lista
                Toast.makeText(this, "Cifra excluída", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}

