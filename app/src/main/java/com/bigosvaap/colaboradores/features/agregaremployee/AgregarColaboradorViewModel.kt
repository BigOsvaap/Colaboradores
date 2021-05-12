package com.bigosvaap.colaboradores.features.agregaremployee

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigosvaap.colaboradores.model.Empleado
import com.bigosvaap.colaboradores.persistance.EmpleadoDao
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AgregarColaboradorViewModel @Inject constructor(
    private val empleadoDao: EmpleadoDao,
    private val firebaseFirestore: FirebaseFirestore
) : ViewModel() {

    val TAG = AgregarColaboradorViewModel::class.java.simpleName

    fun addColaborador(empleado: Empleado) = viewModelScope.launch {
        empleadoDao.insert(empleado)
        firebaseFirestore.collection("empleados")
            .add(empleado.toHashMapOf())
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

}