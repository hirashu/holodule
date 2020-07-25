package com.example.holodule

data class YTApiVideo(
    val items:List<Item>?
) {
    class Item{
        val id:String?=null
        val snippet: Snippet?=null
        val liveStreamingDetails :LiveStreamingDetail?=null
    }

    class Snippet{
        val title :String? =null
        val description:String?=null
        val thumbnails:YTApiSearchResult.Thumbnails?=null
        /**TODO ここはenumクラス化するか考える*/
        val liveBroadcastContent:String?=null
        val channelId:String?=null

    }

    /**動画の配信時間のクラス*/
    class LiveStreamingDetail{
        val scheduledStartTime :TimeValue? =null
    }

    class TimeValue{
        val value :Long?=null
    }
}