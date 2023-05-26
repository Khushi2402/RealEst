package com.example.project

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.adapters.CardAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter
    private val propertyList: MutableList<Property> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CardAdapter(propertyList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadDataFromInternet()
    }

    private fun loadDataFromInternet() {
        Log.d("LoadData", "Start loading data")
        // Start shimmer effect


        // Simulated data loading delay
        Handler(Looper.getMainLooper()).postDelayed({
            // Call loadPropertyData with a callback
            loadPropertyData { propertyData, error ->
                if (error != null) {
                    // Handle error case
                    // Show an error message or retry the API call'
                    Log.e("LoadData", "Error loading data: ${error.message}")
                } else {
                    // Assume you have loaded the property data successfully
                    Log.d("LoadData", "Data loaded successfully")
                    // Stop shimmer effect and display RecyclerView

                    // Update RecyclerView with property data
                    propertyList.clear()
                    if (propertyData != null) {
                        propertyList.addAll(propertyData)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }, 2000) // Simulate 5 seconds delay, replace with your actual data loading code
    }

    private fun loadPropertyData(callback: (List<Property>?, Throwable?) -> Unit) {
        val client = OkHttpClient()

        val request = Request.Builder()
                // add your ip address
            .url("http://192.168.43.176:5050/property")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle network failure
                // Show an error message or retry the API call
                callback(null, e)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()

                activity?.runOnUiThread {
                    if (response.isSuccessful && responseData != null) {
                        // Parse the response data into a list of Property objects
                        val propertyData = parsePropertyData(responseData)

                        // Update RecyclerView with property data
                        propertyList.clear()
                        propertyList.addAll(propertyData)
                        adapter.notifyDataSetChanged()
                        Log.d("API Response", "Data loaded successfully")
                    } else {
                        // Handle API error
                        // Show an error message or retry the API call
                        callback(null, Exception("API Error"))
                        Log.e("API Response", "Error loading data")
                    }
                }
            }

        })
    }

    private fun parsePropertyData(responseData: String): List<Property> {
        val propertyList = mutableListOf<Property>()

        try {

            val jsonArray = JSONArray(responseData)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)

                val image = jsonObject.getString("image")
                val name = jsonObject.getString("name")
                val type = jsonObject.getString("type")
                val address = jsonObject.getString("address")
                val size = jsonObject.getString("size")
                val description = jsonObject.getString("description")


                val property = Property( image, name, type, address, size, description)
                propertyList.add(property)

                // Log the values
                Log.d("Property", "Image: $image")
                Log.d("Property", "Name: $name")
                Log.d("Property", "Type: $type")
                Log.d("Property", "Address: $address")
                Log.d("Property", "Size: $size")
                Log.d("Property", "Description: $description")

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return propertyList
    }

}
