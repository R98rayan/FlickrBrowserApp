package com.example.flickrbrowserapp

class FlickrAPI {
    companion object {
        var url = "https://api.flickr.com/services/rest"
        var apiKey = "d5edeaeadebc44d7c948faf9457d4d0c"

        fun getURL_PhotosByTag(tag: String): String {
            return "$url/?method=flickr.photos.search&api_key=$apiKey&tags=$tag&format=json&nojsoncallback=1"
        }

        fun getPhotoURL(photo: Photo, size: String): String {
            return "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_${size}.jpg"
        }
    }
}