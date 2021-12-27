package com.example.flickrbrowserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        OurClass.mainActivity = this

        lateinit var button: Button
        lateinit var editText: EditText

        lateinit var rvMain: RecyclerView
        lateinit var rvAdapter: RVAdapter

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = arrayListOf<Photo>()

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(list)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        editText = findViewById(R.id.editTextTextPersonName)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            val apiInterface = APIClient().getClient(FlickrAPI.getURL_PhotosByTag(editText.text.toString()))?.create(APIInterface::class.java)

            list.clear()

            apiInterface?.getPhotos()?.enqueue(object: Callback<Flickr> {
                override fun onResponse(call: Call<Flickr>, response: Response<Flickr>) {
                    // we use a try block to make sure that our app doesn't crash if the data is incomplete
                    try{
                        // now we have access to all cars from the JSON file, we will only use the first car in this demo (index value 0)
                        list.addAll(response.body()!!.photos.photo)
                        rvAdapter.notifyDataSetChanged()
                    }catch(e: Exception){
                        Log.d("MAIN", "ISSUE: $e")
                    }
                }

                override fun onFailure(call: Call<Flickr>, t: Throwable) {
                    Log.d("MAIN", "Unable to get data")
                }

            })
        }
    }

    fun updateImageByURL(url: String, imageView: ImageView){
        Glide.with(this).load(url).into(imageView)
    }

}