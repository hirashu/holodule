package com.example.holodule

data class YTApiVideo(
    val items:List<Item>?
) {
    class Item{
        val id:String?=null
        val liveStreamingDetails :LiveStreamingDetails?=null
    }
    class LiveStreamingDetails{
        val scheduledStartTime :String? =null
    }
}