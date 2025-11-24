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
import com.example.botaniq.models.CactiPlant
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

class CactiPlantAdapter(
    private val onItemClick: (CactiPlant) -> Unit,
    private val onFavoriteClick: (CactiPlant) -> Unit,
    private val onAddToCartClick: (CactiPlant) -> Unit
) : ListAdapter<CactiPlant, CactiPlantAdapter.PlantViewHolder>(PlantDiffCallback()) {

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
        private val nameText: TextView = itemView.findViewById(R.id.plantNameTextView)
        private val descriptionText: TextView = itemView.findViewById(R.id.plantDescriptionTextView)
        private val typeChip: Chip = itemView.findViewById(R.id.typeChip)
        private val priceText: TextView = itemView.findViewById(R.id.priceTextView)
        private val lightRequirementText: TextView = itemView.findViewById(R.id.lightRequirementText)
        private val waterFrequencyText: TextView = itemView.findViewById(R.id.waterFrequencyText)
        private val growthRateText: TextView = itemView.findViewById(R.id.growthRateText)
        private val wishlistButton: ImageButton = itemView.findViewById(R.id.wishlistButton)
        private val addToCartButton: MaterialButton = itemView.findViewById(R.id.addToCartButton)

        // Light level indicators
        private val lightLevels = listOf(
            itemView.findViewById<View>(R.id.lightLevel1),
            itemView.findViewById<View>(R.id.lightLevel2),
            itemView.findViewById<View>(R.id.lightLevel3),
            itemView.findViewById<View>(R.id.lightLevel4)
        )

        // Water level indicators
        private val waterLevels = listOf(
            itemView.findViewById<View>(R.id.waterLevel1),
            itemView.findViewById<View>(R.id.waterLevel2),
            itemView.findViewById<View>(R.id.waterLevel3),
            itemView.findViewById<View>(R.id.waterLevel4)
        )

        fun bind(plant: CactiPlant) {
            imageView.setImageResource(plant.imageResource)
            nameText.text = plant.name
            descriptionText.text = plant.description
            typeChip.text = plant.type
            priceText.text = plant.price
            lightRequirementText.text = plant.lightRequirement
            waterFrequencyText.text = plant.waterFrequency
            growthRateText.text = "Growth Rate: ${plant.growthRate}"

            updateLightLevels(plant.lightLevel)
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

        private fun updateLightLevels(level: Int) {
            lightLevels.forEachIndexed { index, view ->
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
            .inflate(R.layout.item_cacti_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<CactiPlant>() {
        override fun areItemsTheSame(oldItem: CactiPlant, newItem: CactiPlant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CactiPlant, newItem: CactiPlant): Boolean {
            return oldItem == newItem
        }
    }
}