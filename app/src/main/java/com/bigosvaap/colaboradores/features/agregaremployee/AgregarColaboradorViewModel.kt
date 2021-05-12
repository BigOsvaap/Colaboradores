package com.bigosvaap.colaboradores.features.agregaremployee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigosvaap.colaboradores.model.Empleado
import com.bigosvaap.colaboradores.persistance.EmpleadoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AgregarColaboradorViewModel @Inject constructor(
    private val empleadoDao: EmpleadoDao
) : ViewModel() {

    fun addColaborador(empleado: Empleado) = viewModelScope.launch {
        empleadoDao.insert(empleado)
    }

}