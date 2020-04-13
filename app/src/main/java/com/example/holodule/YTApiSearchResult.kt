package com.example.holodule

data class YTApiSearchResult(
    val items: List<Item>?
){
    class Item{
        val id:Id? = null
        val snippet: Snippet? = null
        val channelTitle:String? = null

    }
    class Id{
        val videoId:String? = ""
    }
    class Snippet{
        val channelId:String? = null

        /** 放送タイトル **/
        val title:String? = null
        val thumbnails:Thumbnails? = null
        val publishedAt:String? = null
    }
    class Thumbnails{
        val default:ThumbnailsImgDef? = null
        val medium:ThumbnailsImgMid? = null
        val high:ThumbnailsImgHigh? = null
    }

    class ThumbnailsImgDef {
        val url:String? = null
        val width:Int ?= 120
        val height:Int ?= 90
    }
    class ThumbnailsImgMid {
        val url:String? = null
        val width:Int ?= 320
        val height:Int ?= 180
    }
    class ThumbnailsImgHigh {
        val url:String? = null
        val width:Int ?= 480
        val height:Int ?= 360
    }
}
