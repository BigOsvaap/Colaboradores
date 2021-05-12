package com.bigosvaap.colaboradores.features.employees.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bigosvaap.colaboradores.databinding.EmpleadoItemBinding
import com.bigosvaap.colaboradores.model.Empleado

class ColaboradoresAdapter(private val onItemClick: (Empleado) -> Unit
    ): ListAdapter<Empleado, ColaboradoresAdapter.ColaboradorViewHolder>(DiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColaboradorViewHolder {
        return ColaboradorViewHolder.from(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: ColaboradorViewHolder, position: Int) {
        val empleadoItem = getItem(position) as Empleado
        holder.bind(empleadoItem)
    }


    class ColaboradorViewHolder private constructor(
        private val binding: EmpleadoItemBinding,
        private val onItemClick: (Empleado) -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Empleado){
            binding.empleado = item
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }

        companion object{
            fun from(parent: ViewGroup, onItemClick: (Empleado) -> Unit): ColaboradorViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EmpleadoItemBinding.inflate(layoutInflater, parent, false)
                return ColaboradorViewHolder(binding, onItemClick)
            }
        }

    }

    class DiffCallback: DiffUtil.ItemCallback<Empleado>(){

        override fun areItemsTheSame(oldItem: Empleado, newItem: Empleado): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Empleado, newItem: Empleado): Boolean {
            return oldItem == newItem
        }

    }

}