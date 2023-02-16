package com.android.example.beerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BeerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val beerName: TextView
    val beerImage: ImageView
    val beerTagLine: TextView
    val beerAbv: TextView

    init {
        beerName = view.findViewById(R.id.beer_name)
        beerImage = view.findViewById(R.id.beer_Image)
        beerTagLine = view.findViewById(R.id.tagline_text)
        beerAbv =view.findViewById(R.id.Abv)
    }
}

class BeerListAdapter(
    private val beerList: List<PunkBeers>
) : RecyclerView.Adapter<BeerListViewHolder>() {
    var onItemClick: ((PunkBeers) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        val beerView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_itemview, parent, false)
        return BeerListViewHolder(beerView)
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val beer = beerList[position]
        holder.beerName.text = beer.name
        holder.beerTagLine.text = beer.tagline
        holder.beerAbv.text = beer.abv.toString()
        Glide.with(holder.beerImage).load(beerList[position].imageURL).into(holder.beerImage)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(beer)
        }
    }


    override fun getItemCount(): Int {
        return beerList.size
    }
}