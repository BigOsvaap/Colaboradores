package com.bigosvaap.colaboradores.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bigosvaap.colaboradores.model.Empleado

@Database(entities = [Empleado::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun empleadosDao(): EmpleadoDao

}