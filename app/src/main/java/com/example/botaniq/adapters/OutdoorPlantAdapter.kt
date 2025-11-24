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
import com.example.botaniq.models.OutdoorPlant
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

class OutdoorPlantAdapter(
    private val onItemClick: (OutdoorPlant) -> Unit,
    private val onFavoriteClick: (OutdoorPlant) -> Unit,
    private val onAddToCartClick: (OutdoorPlant) -> Unit
) : ListAdapter<OutdoorPlant, OutdoorPlantAdapter.PlantViewHolder>(PlantDiffCallback()) {

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
        private val nameText: TextView = itemView.findViewById(R.id.plantNameTextView)
        private val descriptionText: TextView = itemView.findViewById(R.id.plantDescriptionTextView)
        private val environmentChip: Chip = itemView.findViewById(R.id.environmentChip)
        private val priceText: TextView = itemView.findViewById(R.id.priceTextView)
        private val sunRequirementText: TextView = itemView.findViewById(R.id.sunRequirementText)
        private val waterRequirementText: TextView = itemView.findViewById(R.id.waterRequirementText)
        private val wishlistButton: ImageButton = itemView.findViewById(R.id.wishlistButton)
        private val addToCartButton: MaterialButton = itemView.findViewById(R.id.addToCartButton)

        // Sun level indicators
        private val sunLevels = listOf(
            itemView.findViewById<View>(R.id.sunLevel1),
            itemView.findViewById<View>(R.id.sunLevel2),
            itemView.findViewById<View>(R.id.sunLevel3),
            itemView.findViewById<View>(R.id.sunLevel4)
        )

        // Water level indicators
        private val waterLevels = listOf(
            itemView.findViewById<View>(R.id.waterLevel1),
            itemView.findViewById<View>(R.id.waterLevel2),
            itemView.findViewById<View>(R.id.waterLevel3),
            itemView.findViewById<View>(R.id.waterLevel4)
        )

        fun bind(plant: OutdoorPlant) {
            imageView.setImageResource(plant.imageResource)
            nameText.text = plant.name
            descriptionText.text = plant.description
            environmentChip.text = plant.environment
            priceText.text = plant.price
            sunRequirementText.text = plant.sunRequirement
            waterRequirementText.text = plant.waterRequirement

            updateSunLevels(plant.sunLevel)
            updateWaterLevels(plant.waterLevel)

            wishlistButton.setImageResource(
                if (plant.isFavorite) R.drawable.ic_heart
                else R.drawable.ic_favorite_border
            )

            // Set click listeners
            itemView.setOnClickListener { onItemClick(plant) }
            wishlistButton.setOnClickListener { onFavoriteClick(plant) }
            addToCartButton.setOnClickListener { onAddToCartClick(plant) }
        }

        private fun updateSunLevels(level: Int) {
            sunLevels.forEachIndexed { index, view ->
                view.setBackgroundResource(
                    if (index < level) R.drawable.level_indicator_active
                    else R.drawable.level_indicator_background
                )
            }
        }

        private fun updateWaterLevels(level: Int) {
            waterLevels.forEachIndexed { index, view ->
                view.setBackgroundResource(
                    if (index < level) R.drawable.level_indicator_active
                    else R.drawable.level_indicator_background
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_outdoor_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<OutdoorPlant>() {
        override fun areItemsTheSame(oldItem: OutdoorPlant, newItem: OutdoorPlant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OutdoorPlant, newItem: OutdoorPlant): Boolean {
            return oldItem == newItem
        }
    }
}