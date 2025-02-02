package com.dam.dam_2025_recap.feature.movie.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dam.dam_2025_recap.databinding.ItemMovieListBinding
import com.dam.dam_2025_recap.feature.movie.domain.Movie

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var binding: ItemMovieListBinding

    fun bind(movie: Movie, onClick: (String) -> Unit) {
        binding = ItemMovieListBinding.bind(view)
        binding.apply {
            text.text = movie.title
            text2.text = movie.description
            image.setImageResource(movie.imageUrl.toInt())
            view.setOnClickListener {
                onClick(movie.id)
            }
        }
    }
}