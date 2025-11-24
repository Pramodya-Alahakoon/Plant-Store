package com.example.botaniq.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.botaniq.R
import com.example.botaniq.models.HerbsPlant
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

class HerbsPlantAdapter(
    private val onItemClick: (HerbsPlant) -> Unit,
    private val onFavoriteClick: (HerbsPlant) -> Unit,
    private val onAddToCartClick: (HerbsPlant) -> Unit
) : ListAdapter<HerbsPlant, HerbsPlantAdapter.PlantViewHolder>(PlantDiffCallback()) {

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
        private val nameText: TextView = itemView.findViewById(R.id.plantNameTextView)
        private val descriptionText: TextView = itemView.findViewById(R.id.plantDescriptionTextView)
        private val categoryChip: Chip = itemView.findViewById(R.id.categoryChip)
        private val priceText: TextView = itemView.findViewById(R.id.priceTextView)
        private val growthTimeText: TextView = itemView.findViewById(R.id.growthTimeTextView)
        private val harvestSeasonText: TextView = itemView.findViewById(R.id.harvestSeasonTextView)
        private val wishlistButton: ImageButton = itemView.findViewById(R.id.wishlistButton)
        private val addToCartButton: MaterialButton = itemView.findViewById(R.id.addToCartButton)

        fun bind(plant: HerbsPlant) {
            imageView.setImageResource(plant.imageResource)
            nameText.text = plant.name
            descriptionText.text = plant.description
            categoryChip.text = plant.category
            priceText.text = plant.price
            growthTimeText.text = plant.growthTime
            harvestSeasonText.text = plant.harvestSeason

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
            .inflate(R.layout.item_herbs_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<HerbsPlant>() {
        override fun areItemsTheSame(oldItem: HerbsPlant, newItem: HerbsPlant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HerbsPlant, newItem: HerbsPlant): Boolean {
            return oldItem == newItem
        }
    }
}