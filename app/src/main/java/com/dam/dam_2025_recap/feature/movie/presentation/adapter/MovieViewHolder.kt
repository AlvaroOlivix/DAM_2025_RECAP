package com.dam.dam_2025_recap.feature.movie.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dam.dam_2025_recap.core.extensions.loadUrl
import com.dam.dam_2025_recap.databinding.ItemMovieListBinding
import com.dam.dam_2025_recap.feature.movie.domain.Movie

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var binding: ItemMovieListBinding

    //Es la función que se llama en el onBindViewHolder
    // que se usa para pintar los elementos en la vista
    fun bind(movie: Movie, onClick: (String) -> Unit) {
        binding = ItemMovieListBinding.bind(view)
        binding.apply {
            text.text = movie.title
            text2.text = movie.description
            image.loadUrl(movie.imageUrl)

            //Cuando se pulse el elemento se ejecuta la función onClick
            view.setOnClickListener {
                onClick(movie.id)
            }
        }
    }
}