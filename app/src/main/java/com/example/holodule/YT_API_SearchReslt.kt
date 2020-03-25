package com.example.holodule

class YT_API_SearchReslt {
    lateinit var items: List<Item>
    class Item{
        lateinit var id:Id
        lateinit var snippet:Snippet
        lateinit var channelTitle:String
    }
    class Id{
        //放送URL
        lateinit var videoId:String
        lateinit var channelId:String
    }
    class Snippet{
        //放送タイトル
        lateinit var title:String
        lateinit var thumbnails:Thumbnails
    }
    class Thumbnails{
        lateinit var default:ThumbnailsImg
        lateinit var medium:ThumbnailsImg
        lateinit var high:ThumbnailsImg
    }

    class ThumbnailsImg {
        lateinit var url:String
        var width:Int = 0
        var height:Int = 0
    }
}
