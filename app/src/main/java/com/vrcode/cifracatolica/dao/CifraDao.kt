package com.vrcode.cifracatolica.dao

import androidx.room.*

import com.vrcode.cifracatolica.model.Cifra

@Dao
interface CifraDao {
    @Insert
    suspend fun inserir(cifra: Cifra)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cifras: List<Cifra>)

    @Delete
    suspend fun deletar(cifra: Cifra)

    @Query("SELECT * FROM cifras")
    suspend fun getAllCifras(): List<Cifra>

    @Delete
    fun delete(cifra: Cifra)

    @Query("SELECT * FROM cifras WHERE id = :id")
    fun getCifraById(id: Int): Cifra?
}
