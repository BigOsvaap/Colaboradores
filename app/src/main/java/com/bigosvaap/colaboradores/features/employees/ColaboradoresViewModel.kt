package com.bigosvaap.colaboradores.features.employees

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bigosvaap.colaboradores.persistance.EmpleadoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ColaboradoresViewModel @Inject constructor(
    private val empleadoDao: EmpleadoDao
): ViewModel() {


    val empleados = getAllColaboradores().asLiveData()

    private fun getAllColaboradores() = empleadoDao.getAll()


}