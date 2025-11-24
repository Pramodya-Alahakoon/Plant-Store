package com.example.botaniq

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
import com.example.botaniq.models.AllPlant
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

class AllPlantAdapter(
    private val onItemClick: (AllPlant) -> Unit,
    private val onFavoriteClick: (AllPlant) -> Unit,
    private val onAddToCartClick: (AllPlant) -> Unit
) : ListAdapter<AllPlant, AllPlantAdapter.PlantViewHolder>(PlantDiffCallback()) {

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
        private val nameText: TextView = itemView.findViewById(R.id.plantNameTextView)
        private val descriptionText: TextView = itemView.findViewById(R.id.plantDescriptionTextView)
        private val priceText: TextView = itemView.findViewById(R.id.priceTextView)
        private val plantTypeChip: Chip = itemView.findViewById(R.id.plantTypeChip)
        private val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        private val ratingText: TextView = itemView.findViewById(R.id.ratingText)
        private val potTypeText: TextView = itemView.findViewById(R.id.potTypeText)
        private val wishlistButton: ImageButton = itemView.findViewById(R.id.wishlistButton)
        private val addToCartButton: MaterialButton = itemView.findViewById(R.id.addToCartButton)

        fun bind(plant: AllPlant) {
            imageView.setImageResource(plant.imageResource)
            nameText.text = plant.name
            descriptionText.text = plant.description
            priceText.text = plant.price
            plantTypeChip.text = plant.type
            ratingBar.rating = plant.rating.toFloat()
            ratingText.text = "(${plant.rating})"
            potTypeText.text = "Pot: ${plant.potType}"

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
            .inflate(R.layout.item_all_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {

        holder.bind(getItem(position))
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<AllPlant>() {
        override fun areItemsTheSame(oldItem: AllPlant, newItem: AllPlant): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: AllPlant, newItem: AllPlant): Boolean {
            return oldItem == newItem
        }
    }
}