package com.bigosvaap.colaboradores.features.employees.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigosvaap.colaboradores.R
import com.bigosvaap.colaboradores.databinding.FragmentColaboradoresListBinding
import com.bigosvaap.colaboradores.features.employees.ColaboradoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColaboradoresListFragment : Fragment(R.layout.fragment_colaboradores_list) {

    private val viewModel: ColaboradoresViewModel by viewModels()
    private val colaboradoresAdapter = ColaboradoresAdapter(
        onItemClick = { empleado ->
            setFragmentResult("colaborador",
                bundleOf("latitude" to empleado.location.latitude, "longitude" to empleado.location.longitude))
            findNavController().navigate(R.id.action_colaboradoresListFragment_to_colaboradoresMapFragment)
        }
    )

    private lateinit var binding: FragmentColaboradoresListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentColaboradoresListBinding.bind(view)

        binding.apply {
            recyclerView.apply{
                adapter = colaboradoresAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.empleados.observe(viewLifecycleOwner){
            colaboradoresAdapter.submitList(it)
        }

    }
}