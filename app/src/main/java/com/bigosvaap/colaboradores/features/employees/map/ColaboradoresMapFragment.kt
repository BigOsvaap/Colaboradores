package com.bigosvaap.colaboradores.features.employees.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.bigosvaap.colaboradores.R
import com.bigosvaap.colaboradores.features.employees.ColaboradoresViewModel
import com.bigosvaap.colaboradores.model.Empleado
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColaboradoresMapFragment : Fragment(R.layout.fragment_colaboradores_map), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    private val viewModel: ColaboradoresViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap

        val latitude = 19.3052213
        val longitude = -99.2032828
        val zoomLevel = 20f

        val homeLatLng = LatLng(latitude, longitude)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))


        addMarkers(map)

        setFragmentResultListener("colaborador"){_, bundle ->
            val empleadoLatLng = LatLng(bundle.getDouble("latitude") , bundle.getDouble("longitude"))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(empleadoLatLng, zoomLevel))
        }

    }

    private fun addMarkers(googleMap: GoogleMap){
        viewModel.empleados.observe(viewLifecycleOwner) { empleados ->
            empleados.forEach { empleado ->
                addColaboratorMarker(empleado, googleMap)
            }
        }
    }

    private fun addColaboratorMarker(empleado: Empleado, googleMap: GoogleMap){
        val position = LatLng(empleado.location.latitude, empleado.location.longitude)
        val snippet =  "ID: ${empleado.id}, Email: ${empleado.email}"
        googleMap.addMarker(MarkerOptions().position(position).title(empleado.name).snippet(snippet))
    }


}