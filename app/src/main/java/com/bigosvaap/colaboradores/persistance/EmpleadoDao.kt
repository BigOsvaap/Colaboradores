package com.bigosvaap.colaboradores.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bigosvaap.colaboradores.model.Empleado
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpleadoDao {

    @Query("SELECT * FROM empleados")
    fun getAll(): Flow<List<Empleado>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(empleado: Empleado)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(empleados: List<Empleado>)

}