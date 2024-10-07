package com.example.kotlin.examen.framework.viewholders

import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.examen.data.model.PersonajeBase
import com.example.kotlin.examen.domain.PersonajeInfoRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.kotlin.examen.databinding.ItemPersonajeBinding

class PersonajeViewHolder(private val binding: ItemPersonajeBinding) : RecyclerView.ViewHolder(binding.root) {



    fun bind(item: PersonajeBase, context:Context){
        binding.TVName.text = item.name
        binding.TVDesc.text = item.description
        binding.TVRaza.text = item.race
        getPersonajeImage(item.image,binding.IVPhoto,context)

    }

    private fun getPersonajeImage(url: String, imageView: ImageView, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val personajeInfoRequirement = PersonajeInfoRequirement()
            val result: PersonajeBase? = personajeInfoRequirement(1)
            CoroutineScope(Dispatchers.Main).launch {
                val urlImage = url // Usar el parámetro url en lugar de result?.image?.toString()

                val requestOptions = RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .priority(Priority.HIGH)

                Glide.with(context).load(urlImage)
                    .apply(requestOptions)
                    .into(imageView)
            }
        }
    }

}