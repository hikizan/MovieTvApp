package com.hikizan.movietvapp.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hikizan.movietvapp.R
import com.hikizan.movietvapp.databinding.ItemMovietvListBinding
import com.hikizan.movietvapp.core.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.core.utils.constants.AppConstants

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<MovieItem>()
    var onItemClick: ((MovieItem) -> Unit)? = null

    fun setData(newListData: List<MovieItem>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movietv_list, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovietvListBinding.bind(itemView)

        fun bind(data: MovieItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(AppConstants.POSTER_PATH + data.posterPath)
                    .into(imgPoster)
                tvItemTitle.text = data.title
                tvItemReleaseDate.text = data.releaseDate
                tvItemOverview.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}