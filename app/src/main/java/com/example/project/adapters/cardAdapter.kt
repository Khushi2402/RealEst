package com.example.project.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.Property
import com.example.project.R
import com.example.project.models.property

class CardAdapter(private val propertyList: List<Property>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        Log.d("CardAdapter", "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_property_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        Log.d("CardAdapter", "onBindViewHolder: position=$position")
        val card = propertyList[position]
        holder.bind(card)
    }


    override fun getItemCount(): Int {
        Log.d("CardAdapter", "getItemCount: ${propertyList.size}")
        return propertyList.size
    }

//    item are not being populated with their assigned ids
    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.prop_image)
        private val nameTextView: TextView = itemView.findViewById(R.id.prop_name)
        private val typeTextView: TextView = itemView.findViewById(R.id.prop_type)
        private  val addTextView: TextView = itemView.findViewById(R.id.prop_address)
        private val sizeTextView: TextView = itemView.findViewById(R.id.prop_size)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.prop_description)


        fun bind(card: Property) {
            Glide.with(itemView)
                .load(card.image)
                .into(imageView)

            nameTextView.text = card.name
            typeTextView.text = card.type
            addTextView.text = card.address
            sizeTextView.text = card.size
            descriptionTextView.text = card.description
        }
    }
}
