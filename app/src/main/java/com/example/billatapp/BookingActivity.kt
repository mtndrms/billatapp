package com.example.billatapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.billatapp.adapters.BookingGridViewAdapter
import com.example.billatapp.api.ApiClient
import com.example.billatapp.api.services.TripService
import com.example.billatapp.constants.BaseUrl
import com.example.billatapp.models.Trip
import com.example.billatapp.utils.convertTravelTime
import com.example.billatapp.utils.departureDateModification
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingActivity : AppCompatActivity() {

    private lateinit var tripService: TripService
    private lateinit var trip: Trip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        tripService = ApiClient.getClient().create(TripService::class.java)

        var path = BaseUrl.baseUrl
        val intent = intent
        val extras = intent.extras
        val seats: ArrayList<ImageView> = ArrayList()
        val tripId: String? = extras?.getString("tripId")
        val tvDeparture: TextView = findViewById(R.id.tvDeparture)
        val tvDestination: TextView = findViewById(R.id.tvDestination)
        val tvDepartureTime: TextView = findViewById(R.id.tvDepartureTime)
        val tvTravelTime: TextView = findViewById(R.id.tvTravelTime)
        val tvPrice: TextView = findViewById(R.id.tvPrice)
        val ivCarrierLogo: ImageView = findViewById(R.id.ivCarrierLogo)
        val gvSeatPickContainer: GridView = findViewById(R.id.gvSeatPickContainer)

        val tripCall = tripId?.let { tripService.getTripById(it) }
        tripCall?.enqueue(object : Callback<Trip> {
            override fun onResponse(call: Call<Trip>, response: Response<Trip>) {
                trip = (response.body() as Trip)
                tvDeparture.text = trip.departureLocation.city.replaceFirstChar { it.uppercase() }
                tvDestination.text =
                    trip.destinationLocation.city.replaceFirstChar { it.uppercase() }
                tvDepartureTime.text = departureDateModification(trip.departureDate)
                tvTravelTime.text = convertTravelTime(trip.travelTime.toString())
                tvPrice.text = getString(R.string.price_turkish_lira, trip.price.toString())
                for (i in 1..trip.vehicle.capacity) {
                    val seat = ImageView(applicationContext)
                    seats.add(seat)
                }
                path = "${path}${trip.carrier.companyLogo.replace("${'\\'}", "/")}"
                Picasso.get()
                    .load(path)
                    .into(ivCarrierLogo)
            }

            override fun onFailure(call: Call<Trip>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })

        val bookingGridViewAdapter = BookingGridViewAdapter(this, seats)
        gvSeatPickContainer.adapter = bookingGridViewAdapter
    }
}
