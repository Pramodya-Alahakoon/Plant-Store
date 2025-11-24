package com.example.botaniq.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.botaniq.R
import com.example.botaniq.models.BestSellerPlant
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

class BestSellerPlantAdapter(
    private val onItemClick: (BestSellerPlant) -> Unit,
    private val onFavoriteClick: (BestSellerPlant) -> Unit,
    private val onAddToCartClick: (BestSellerPlant) -> Unit
) : ListAdapter<BestSellerPlant, BestSellerPlantAdapter.PlantViewHolder>(PlantDiffCallback()) {

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
        private val nameText: TextView = itemView.findViewById(R.id.plantNameTextView)
        private val descriptionText: TextView = itemView.findViewById(R.id.plantDescriptionTextView)
        private val priceText: TextView = itemView.findViewById(R.id.priceTextView)
        private val bestSellerChip: Chip = itemView.findViewById(R.id.bestSellerChip)
        private val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        private val ratingText: TextView = itemView.findViewById(R.id.ratingText)
        private val typeText: TextView = itemView.findViewById(R.id.typeText)
        private val wishlistButton: ImageButton = itemView.findViewById(R.id.wishlistButton)
        private val addToCartButton: MaterialButton = itemView.findViewById(R.id.addToCartButton)

        fun bind(plant: BestSellerPlant) {
            imageView.setImageResource(plant.imageResource)
            nameText.text = plant.name
            descriptionText.text = plant.description
            priceText.text = plant.price
            ratingBar.rating = plant.rating.toFloat()
            ratingText.text = "(${plant.rating})"
            typeText.text = plant.type
            bestSellerChip.text = if (plant.isPopular) "Popular Pick" else "Best Seller"

            wishlistButton.setImageResource(
                if (plant.isFavorite) R.drawable.ic_heart
                else R.drawable.ic_favorite_border
            )

            // Set click listeners
            itemView.setOnClickListener { onItemClick(plant) }
            wishlistButton.setOnClickListener { onFavoriteClick(plant) }
            addToCartButton.setOnClickListener { onAddToCartClick(plant) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bestsellers_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<BestSellerPlant>() {
        override fun areItemsTheSame(oldItem: BestSellerPlant, newItem: BestSellerPlant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BestSellerPlant, newItem: BestSellerPlant): Boolean {
            return oldItem == newItem
        }
    }
}