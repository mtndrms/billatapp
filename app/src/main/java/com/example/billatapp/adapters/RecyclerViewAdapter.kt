package com.example.billatapp.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.billatapp.BookingActivity
import com.example.billatapp.R
import com.example.billatapp.constants.BaseUrl
import com.example.billatapp.models.Trip
import com.example.billatapp.utils.convertTravelTime
import com.example.billatapp.utils.departureDateModification
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val results: MutableList<Trip>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ticket_search_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemViewHolder -> {
                holder.bind(results[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var path = BaseUrl.baseUrl
        private var tvDeparture: TextView = itemView.findViewById(R.id.tvDeparture)
        private val tvDestination: TextView = itemView.findViewById(R.id.tvDestination)
        private val tvTravelTime: TextView = itemView.findViewById(R.id.tvTravelTime)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val tvDepartureTime: TextView = itemView.findViewById(R.id.tvDepartureTime)
        private val ivCarrierLogo: ImageView = itemView.findViewById(R.id.ivCarrierLogo)
        private val cvCardView: MaterialCardView = itemView.findViewById(R.id.cvCard)

        fun bind(trip: Trip) = with(itemView) {
            tvDeparture.text = trip.departureLocation.city.replaceFirstChar { it.uppercase() }
            tvDestination.text = trip.destinationLocation.city.replaceFirstChar { it.uppercase() }
            tvTravelTime.text = convertTravelTime(trip.travelTime.toString())
            "₺${trip.price}".also { tvPrice.text = it }
            tvDepartureTime.text = departureDateModification(trip.departureDate)
            path = "${path}${trip.carrier.companyLogo.replace("${'\\'}", "/")}"
            Picasso.get()
                .load(path)
                .into(ivCarrierLogo)

            cvCardView.setOnClickListener {
                Intent(context, BookingActivity::class.java).apply {
                    val bundle = Bundle()
                    bundle.putString("carrierId", trip.carrier._id)
                    bundle.putString("tripId", trip._id)
                    this.putExtras(bundle)
                    startActivity(context, this, bundle)
                }
            }
        }
    }
}