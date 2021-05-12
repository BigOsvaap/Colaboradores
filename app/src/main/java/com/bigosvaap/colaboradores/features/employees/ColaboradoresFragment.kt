package com.bigosvaap.colaboradores.features.employees

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bigosvaap.colaboradores.R
import com.bigosvaap.colaboradores.databinding.FragmentColaboradoresBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColaboradoresFragment : Fragment(R.layout.fragment_colaboradores) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentColaboradoresBinding.bind(view)

        binding.apply {

            listaColaboradores.setOnClickListener {
                findNavController().navigate(R.id.action_colaboradoresFragment_to_colaboradoresListFragment)
            }

            mapaColaboradores.setOnClickListener {
                findNavController().navigate(R.id.action_colaboradoresFragment_to_colaboradoresMapFragment)
            }

        }

    }
}