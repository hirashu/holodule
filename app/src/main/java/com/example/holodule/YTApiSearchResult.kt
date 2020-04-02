package com.example.holodule

class YTApiSearchResult(
     var items: List<Item>?
){
    class Item{
        var id:Id? = null
        var snippet: Snippet? = null
        var channelTitle:String? = null

    }
    class Id{
        var videoId:String? = ""
    }
    class Snippet{
        var channelId:String? = null

        /** 放送タイトル **/
        var title:String? = null
        var thumbnails:Thumbnails? = null
        var publishedAt:String? = null
    }
    class Thumbnails{
        var default:ThumbnailsImgDef? = null
        var medium:ThumbnailsImgMid? = null
        var high:ThumbnailsImgHigh? = null
    }

    class ThumbnailsImgDef {
        var url:String? = null
        var width:Int ?= 120
        var height:Int ?= 90
    }
    class ThumbnailsImgMid {
        var url:String? = null
        var width:Int ?= 320
        var height:Int ?= 180
    }
    class ThumbnailsImgHigh {
        var url:String? = null
        var width:Int ?= 480
        var height:Int ?= 360
    }
}
