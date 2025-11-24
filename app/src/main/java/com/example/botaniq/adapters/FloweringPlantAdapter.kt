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
import com.example.botaniq.FloweringPlant
import com.example.botaniq.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

class FloweringPlantAdapter(
    private val onItemClick: (FloweringPlant) -> Unit,
    private val onFavoriteClick: (FloweringPlant) -> Unit,
    private val onAddToCartClick: (FloweringPlant) -> Unit
) : ListAdapter<FloweringPlant, FloweringPlantAdapter.PlantViewHolder>(PlantDiffCallback()) {

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
        private val nameText: TextView = itemView.findViewById(R.id.plantNameTextView)
        private val descriptionText: TextView = itemView.findViewById(R.id.plantDescriptionTextView)
        private val priceText: TextView = itemView.findViewById(R.id.priceTextView)
        private val seasonChip: Chip = itemView.findViewById(R.id.seasonChip)
        private val sunlightText: TextView = itemView.findViewById(R.id.sunlightTextView)
        private val wateringText: TextView = itemView.findViewById(R.id.wateringTextView)
        private val wishlistButton: ImageButton = itemView.findViewById(R.id.wishlistButton)
        private val addToCartButton: MaterialButton = itemView.findViewById(R.id.addToCartButton)

        fun bind(plant: FloweringPlant) {
            imageView.setImageResource(plant.imageResource)
            nameText.text = plant.name
            descriptionText.text = plant.description
            priceText.text = plant.price
            seasonChip.text = plant.season
            sunlightText.text = plant.sunlight
            wateringText.text = plant.watering

            wishlistButton.setImageResource(
                if (plant.isFavorite) R.drawable.ic_heart
                else R.drawable.ic_favorite_border
            )

            itemView.setOnClickListener { onItemClick(plant) }
            wishlistButton.setOnClickListener { onFavoriteClick(plant) }
            addToCartButton.setOnClickListener { onAddToCartClick(plant) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_flowering_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<FloweringPlant>() {
        override fun areItemsTheSame(oldItem: FloweringPlant, newItem: FloweringPlant): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: FloweringPlant, newItem: FloweringPlant): Boolean {
            return oldItem == newItem
        }
    }
}