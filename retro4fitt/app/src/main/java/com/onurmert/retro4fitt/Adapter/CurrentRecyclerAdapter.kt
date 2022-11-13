package com.onurmert.retro4fitt.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.onurmert.retro4fitt.Model.FilmsModel
import com.onurmert.retro4fitt.R
import com.onurmert.retro4fitt.View.CurrentFragmentDirections
import com.onurmert.retro4fitt.databinding.CurrentRecyclerRowBinding
import com.squareup.picasso.Picasso

class CurrentRecyclerAdapter(val filmList: ArrayList<FilmsModel>) :
    RecyclerView.Adapter<CurrentRecyclerAdapter.CurrentViewHolder>() {

    class CurrentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val binding = CurrentRecyclerRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.current_recycler_row,parent,false)

        return CurrentViewHolder(layout)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CurrentViewHolder, position: Int) {

        Picasso.with(holder.itemView.context)
            .load(filmList.get(position).large_cover_image)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.filmsImageView)

        holder.binding.titleText.setText("${position + 1})" + filmList.get(position).title!!.trim())
        holder.binding.ratingText.setText("Rating: " + filmList.get(position).rating!!.trim())
        holder.binding.yearText.setText("Year:" + filmList.get(position).year!!.trim())

        holder.binding.cardView.setOnClickListener {
            val directions = CurrentFragmentDirections
                .actionCurrentFragmentToFilmDetailFragment(filmList.get(position).id!!)

            Navigation.findNavController(it).navigate(directions)
        }
    }
    override fun getItemCount(): Int {
        return filmList.size
    }
}