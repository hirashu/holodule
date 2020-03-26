package com.example.holodule

import android.os.AsyncTask
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.SearchListResponse
import com.google.gson.Gson

class ApiYTSearch() : AsyncTask<String, String, YTApiSearchReslt>() {

    override fun doInBackground(vararg params: String?): YTApiSearchReslt? {

        val youtube = YouTube.Builder(NetHttpTransport(), JacksonFactory(), null)
            .setApplicationName("youtube-cmdline-search-sample").build()
        //println("YOUTUBE ::: $youtube")

        val search = youtube.Search().list("id,snippet").setQ("UC1opHUrw8rvnsadT-iGp7Cg")
        val ret = search.execute().toString()
        var gson = Gson()
        return  gson.fromJson(ret, YTApiSearchReslt::class.java)
    }

}