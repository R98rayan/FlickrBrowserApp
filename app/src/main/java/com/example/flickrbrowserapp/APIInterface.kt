package com.example.flickrbrowserapp

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET(" ")
    fun getPhotos(): Call<Flickr>
}