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
import com.example.botaniq.models.AirPurifyingPlant
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

class AirPurifyingPlantAdapter(
    private val onItemClick: (AirPurifyingPlant) -> Unit,
    private val onFavoriteClick: (AirPurifyingPlant) -> Unit,
    private val onAddToCartClick: (AirPurifyingPlant) -> Unit
) : ListAdapter<AirPurifyingPlant, AirPurifyingPlantAdapter.PlantViewHolder>(PlantDiffCallback()) {

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
        private val nameText: TextView = itemView.findViewById(R.id.plantNameTextView)
        private val descriptionText: TextView = itemView.findViewById(R.id.plantDescriptionTextView)
        private val priceText: TextView = itemView.findViewById(R.id.priceTextView)
        private val purificationChip: Chip = itemView.findViewById(R.id.purificationLevelChip)
        private val purificationText: TextView = itemView.findViewById(R.id.purificationEfficiencyText)
        private val maintenanceText: TextView = itemView.findViewById(R.id.maintenanceTextView)
        private val wishlistButton: ImageButton = itemView.findViewById(R.id.wishlistButton)
        private val addToCartButton: MaterialButton = itemView.findViewById(R.id.addToCartButton)

        // Purification level indicators
        private val purificationLevels = listOf(
            itemView.findViewById<View>(R.id.purificationLevel1),
            itemView.findViewById<View>(R.id.purificationLevel2),
            itemView.findViewById<View>(R.id.purificationLevel3),
            itemView.findViewById<View>(R.id.purificationLevel4),
            itemView.findViewById<View>(R.id.purificationLevel5)
        )

        fun bind(plant: AirPurifyingPlant) {
            imageView.setImageResource(plant.imageResource)
            nameText.text = plant.name
            descriptionText.text = plant.description
            priceText.text = plant.price
            purificationChip.text = "Purification: ${plant.getPurificationText()}"
            purificationText.text = plant.getPurificationText()
            maintenanceText.text = plant.maintenanceLevel

            // Update purification level indicators
            updatePurificationLevels(plant.purificationLevel)

            wishlistButton.setImageResource(
                if (plant.isFavorite) R.drawable.ic_heart
                else R.drawable.ic_favorite_border
            )

            // Click listeners
            itemView.setOnClickListener { onItemClick(plant) }
            wishlistButton.setOnClickListener { onFavoriteClick(plant) }
            addToCartButton.setOnClickListener { onAddToCartClick(plant) }
        }

        private fun updatePurificationLevels(level: Int) {
            purificationLevels.forEachIndexed { index, view ->
                view.setBackgroundResource(
                    if (index < level) R.drawable.level_indicator_active
                    else R.drawable.level_indicator_background
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_air_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<AirPurifyingPlant>() {
        override fun areItemsTheSame(oldItem: AirPurifyingPlant, newItem: AirPurifyingPlant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AirPurifyingPlant, newItem: AirPurifyingPlant): Boolean {
            return oldItem == newItem
        }
    }
}