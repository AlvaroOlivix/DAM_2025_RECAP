package com.dam.dam_2025_recap.feature.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.dam.dam_2025_recap.R
import com.dam.dam_2025_recap.feature.movie.domain.Movie

class MovieAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffUtil()) {
    private lateinit var onClick: (String) -> Unit

    //Se usa para pasar una función al viewHolder
    // para que se ejecute cuando se pulse un elemento de la lista
    fun setOnClick(onClick: (String) -> Unit) {
        this.onClick = onClick
    }

    //Crea el viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        //Se introduce el viewHolder del item de la lista
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_list, parent, false)
        //Devuelve el viewHolder cargado con la vista
        return MovieViewHolder(view)
    }

    //Se llama al bind del viewHolder por cada elemento para pintarlo
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        //Se llama al bind del viewHolder
        holder.bind(getItem(position), onClick)
    }

    override fun getItemCount(): Int = currentList.size
}
