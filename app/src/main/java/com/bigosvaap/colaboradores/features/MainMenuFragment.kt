package com.bigosvaap.colaboradores.features

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bigosvaap.colaboradores.R
import com.bigosvaap.colaboradores.databinding.FragmentMainMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainMenuBinding.bind(view)

        binding.apply {
            colaboradores.setOnClickListener {
                findNavController().navigate(R.id.action_mainMenuFragment_to_colaboradoresFragment)
            }
            agregarColaborador.setOnClickListener {
                findNavController().navigate(R.id.action_mainMenuFragment_to_agregarColaboradorFragment)
            }
        }

    }
}