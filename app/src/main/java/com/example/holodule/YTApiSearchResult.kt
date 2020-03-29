package com.example.holodule

class YTApiSearchResult(
     var items: List<Item>
){
    class Item{
        lateinit var id:Id
        lateinit var snippet:Snippet
        lateinit var channelTitle:String

    }
    class Id{
        var videoId:String =""
    }
    class Snippet{
        lateinit var channelId:String
        /** 放送タイトル **/
        lateinit var title:String
        lateinit var thumbnails:Thumbnails
        lateinit var publishedAt:String
    }
    class Thumbnails{
        lateinit var default:ThumbnailsImgDef
        lateinit var medium:ThumbnailsImgMid
        lateinit var high:ThumbnailsImgHigh
    }

    class ThumbnailsImgDef {
        lateinit var url:String
        var width:Int ?= 120
        var height:Int ?= 90
    }
    class ThumbnailsImgMid {
        lateinit var url:String
        var width:Int ?= 320
        var height:Int ?= 180
    }
    class ThumbnailsImgHigh {
        lateinit var url:String
        var width:Int ?= 480
        var height:Int ?= 360
    }
}
