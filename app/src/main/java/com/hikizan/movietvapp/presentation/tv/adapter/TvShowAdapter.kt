package com.hikizan.movietvapp.presentation.tv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hikizan.movietvapp.R
import com.hikizan.movietvapp.databinding.ItemMovietvListBinding
import com.hikizan.movietvapp.domain.movietv.model.response.TvShowItem
import com.hikizan.movietvapp.utils.constants.AppConstants

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ListViewHolder>() {

    private var listData = ArrayList<TvShowItem>()
    var onItemClick: ((TvShowItem) -> Unit)? = null

    fun setData(newListData: List<TvShowItem>?) {
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

        fun bind(data: TvShowItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(AppConstants.POSTER_PATH + data.posterPath)
                    .into(imgPoster)
                tvItemTitle.text = data.name
                tvItemReleaseDate.text = data.firstAirDate
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