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
import com.example.botaniq.models.IndoorPlant
import com.google.android.material.button.MaterialButton

class IndoorPlantAdapter(
    private val onItemClick: (IndoorPlant) -> Unit,
    private val onFavoriteClick: (IndoorPlant) -> Unit,
    private val onAddToCartClick: (IndoorPlant) -> Unit
) : ListAdapter<IndoorPlant, IndoorPlantAdapter.PlantViewHolder>(PlantDiffCallback()) {

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
        private val nameText: TextView = itemView.findViewById(R.id.plantNameTextView)
        private val descriptionText: TextView = itemView.findViewById(R.id.plantDescriptionTextView)
        private val priceText: TextView = itemView.findViewById(R.id.priceTextView)
        private val wishlistButton: ImageButton = itemView.findViewById(R.id.wishlistButton)
        private val addToCartButton: MaterialButton = itemView.findViewById(R.id.addToCartButton)

        fun bind(plant: IndoorPlant) {
            imageView.setImageResource(plant.imageResource)
            nameText.text = plant.name
            descriptionText.text = plant.description
            priceText.text = plant.price

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
            .inflate(R.layout.item_indoor_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class PlantDiffCallback : DiffUtil.ItemCallback<IndoorPlant>() {
        override fun areItemsTheSame(oldItem: IndoorPlant, newItem: IndoorPlant): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: IndoorPlant, newItem: IndoorPlant): Boolean {
            return oldItem == newItem
        }
    }
}