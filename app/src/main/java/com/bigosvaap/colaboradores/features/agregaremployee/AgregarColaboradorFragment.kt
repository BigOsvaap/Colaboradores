package com.bigosvaap.colaboradores.features.agregaremployee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bigosvaap.colaboradores.R
import com.bigosvaap.colaboradores.databinding.FragmentAgregarColaboradorBinding
import com.bigosvaap.colaboradores.model.Empleado
import com.bigosvaap.colaboradores.model.Location
import com.github.javafaker.Faker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class AgregarColaboradorFragment : Fragment(R.layout.fragment_agregar_colaborador) {

    @Inject
    lateinit var faker: Faker

    private lateinit var binding: FragmentAgregarColaboradorBinding
    private val viewModel: AgregarColaboradorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAgregarColaboradorBinding.bind(view)

        populateText()

        binding.save.setOnClickListener {

            with(binding){
                val empleado = Empleado(id.editText?.text.toString().toInt(),
                name.editText?.getText().toString(),
                Location(latitude.editText?.text.toString().toDouble(), longitude.editText?.text.toString().toDouble()),
                email.editText?.text.toString())

                viewModel.addColaborador(empleado)
            }

            findNavController().navigate(R.id.action_agregarColaboradorFragment_to_mainMenuFragment)

        }

    }

    private fun populateText(){
        binding.apply {

            id.editText?.setText(Random.nextInt(1, 66).toString())
            name.editText?.setText(faker.name().fullName())
            latitude.editText?.setText(Random.nextDouble(-100.0, 100.0).toString())
            longitude.editText?.setText(Random.nextDouble(-100.0, 100.0).toString())
            email.editText?.setText(faker.internet().emailAddress())

        }
    }
}