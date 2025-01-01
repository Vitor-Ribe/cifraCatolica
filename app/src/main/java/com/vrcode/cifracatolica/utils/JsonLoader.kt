package com.vrcode.cifracatolica.utils

import android.content.Context
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.vrcode.cifracatolica.database.AppDatabase
import com.vrcode.cifracatolica.model.Cifra
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.InputStreamReader

object JsonLoader {
    fun loadJsonIntoDatabase(context: Context, database: AppDatabase) {
        runBlocking {
            withContext(Dispatchers.IO) {
                try {
                    // Ler o arquivo JSON da pasta assets
                    val inputStream = context.assets.open("cifras.json")
                    val reader = InputStreamReader(inputStream)

                    // Usar Gson para converter o JSON em uma lista de objetos
                    val type = object : TypeToken<List<Cifra>>() {}.type
                    val cifras: List<Cifra> = Gson().fromJson(reader, type)

                    // Inserir as cifras no banco de dados
                    database.cifraDao().insertAll(cifras)

                    inputStream.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
