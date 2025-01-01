package com.vrcode.cifracatolica

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.vrcode.cifracatolica.R.id.bt_cifras
import com.vrcode.cifracatolica.R.id.bt_listas
import com.vrcode.cifracatolica.database.AppDatabase
import com.vrcode.cifracatolica.utils.JsonLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val botaoListas: ImageButton = findViewById(bt_listas)

        botaoListas.setOnClickListener {
            val intent = Intent(this, Listas::class.java)
            startActivity(intent)
        }

        val botaoCifra: ImageButton = findViewById(bt_cifras)

        botaoCifra.setOnClickListener {
            lifecycleScope.launch {
                // Verificar e carregar JSON no banco de dados
                val database = AppDatabase.getInstance(this@Home)
                val cifrasExistentes = withContext(Dispatchers.IO) {
                    database.cifraDao().getAllCifras()
                }

                if (cifrasExistentes.isEmpty()) {
                    JsonLoader.loadJsonIntoDatabase(this@Home, database)
                }

                // Navegar para a página de cifras
                val intent = Intent(this@Home, CifraPage::class.java)
                startActivity(intent)
            }
        }




    }


}