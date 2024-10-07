package com.example.kotlin.examen.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen.data.model.PersonajeBase
import com.example.kotlin.examen.framework.viewholders.PersonajeViewHolder
import com.example.kotlin.examen.databinding.ItemPersonajeBinding


class PersonajeAdapter: RecyclerView.Adapter<PersonajeViewHolder>() {

    var data: ArrayList<PersonajeBase> = ArrayList()
    private lateinit var context: Context

    fun PersonajeAdapter(basicData: ArrayList<PersonajeBase>, context: Context){
        this.data = basicData
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val binding = ItemPersonajeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonajeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }



}