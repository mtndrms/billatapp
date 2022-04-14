package com.example.billatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.billatapp.adapters.RecyclerViewAdapter
import com.example.billatapp.api.ApiClient
import com.example.billatapp.api.services.TripService
import com.example.billatapp.models.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : AppCompatActivity() {

    private lateinit var tripService: TripService
    lateinit var tripList: MutableList<Trip>
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        tripService = ApiClient.getClient().create(TripService::class.java)

        val intent = intent
        val extras = intent.extras
        val from = extras?.getString("from")?.lowercase()
        val where = extras?.getString("where")?.lowercase()

        val trips = tripService.getAllTrips()
        trips.enqueue(object : Callback<List<Trip>> {
            override fun onResponse(call: Call<List<Trip>>, response: Response<List<Trip>>) {
                if (response.isSuccessful) {
                    tripList = (response.body() as MutableList<Trip>?)!!
                    initRecyclerView(tripList)
                }
            }

            override fun onFailure(call: Call<List<Trip>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecyclerView(tripList: MutableList<Trip>) {
        val rvResults: RecyclerView = findViewById(R.id.rvResults)
        rvResults.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter(tripList)
        rvResults.adapter = recyclerViewAdapter
    }
}