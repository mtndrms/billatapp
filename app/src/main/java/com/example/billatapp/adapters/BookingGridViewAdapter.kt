package com.example.billatapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.billatapp.R

class BookingGridViewAdapter(private val context: Context, private val seats: ArrayList<ImageView>) : BaseAdapter() {
    override fun getCount(): Int {
        return seats.size
    }

    override fun getItem(p0: Int): Any {
        return seats[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.booking_grid_view_item, null)
        val ivSeat: ImageView = view.findViewById(R.id.seat)
        ivSeat.setOnClickListener {

        }
        ivSeat.setImageResource(R.drawable.passenger_empty_seat)
        return view
    }
}