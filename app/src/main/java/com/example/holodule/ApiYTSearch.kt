package com.example.holodule

import android.os.AsyncTask
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.youtube.YouTube
import com.google.gson.Gson
import java.lang.Exception
import java.time.format.DateTimeFormatter

class ApiYTSearch() : AsyncTask<String, String, List<YTApiSearchResult.Item>>() {

        companion object {
            private const val apiKey: String = "AIzaSyCQKLwyW0ZgQd_itFgrVLj68xhCxFcOiO0"
        }

        override fun doInBackground(vararg params: String?): List<YTApiSearchResult.Item>? {

            var gson = Gson()
            val youtube = YouTube.Builder(NetHttpTransport(), JacksonFactory(), null)
                .setApplicationName("youtube-cmdline-search-sample").build()
            val search = youtube.Search().list("snippet")
            search.key = apiKey
            //取得開始時間の設定
            search.publishedAfter = DateTime(DateUnit().yesterday())

            val channelIdList = if(BuildMode.MODE.testMode) {
                //テストデータモード
                //listOf(Member774inc.SUO_PATRA.chId)
                var ret =testAPI
                return try {
                    val retJson = gson.fromJson(ret, YTApiSearchResult::class.java)
                    retJson.items
                }catch (ex:Exception){
                    null
                }
            }else{
                //APIモード（正規ルート）
                //listOf(Member774inc.SUO_PATRA.chId)//デバッグモード
                groupChannelIdList(Group774Inc.ALL)
            }
            val broadcastList = mutableListOf<YTApiSearchResult.Item>()

            channelIdList.forEach {
                search.channelId = it
                try {
                    val result = search.execute()
                    /*TODO ↓↓本来ならMapperを用いて実装する*/
                    val jsonTest = gson.toJson(result)
                    val retJson = gson.fromJson(jsonTest, YTApiSearchResult::class.java)

                    if (retJson.items != null) {
                        retJson.items.forEach {
                            broadcastList.add(it)
                        }
                    }
                } catch (ex: Exception) {
                    return broadcastList
                }
            }

            return broadcastList
        }

        override fun onPostExecute(result: List<YTApiSearchResult.Item>?) {
            //ここで更新処理をおこなう。

        }

    //test用API返却値
    private val testAPI = "{\"etag\":\"2iHOyLMNvBtlgk7nz16q26bS9gk\",\"items\":[{\"etag\":\"ACUnHSR-sa4np6Vrbi8f2BHRrpE\",\"id\":{\"kind\":\"youtube#video\",\"videoId\":\"TNvHb412FsE\"},\"kind\":\"youtube#searchResult\",\"snippet\":{\"channelId\":\"UCeLzT-7b2PBcunJplmWtoDg\",\"channelTitle\":\"Patra Channel / 周防パトラ 【ハニスト】\",\"description\":\"竜胆尊ちゃんと遊ぶよ～   竜胆尊ちゃん   https://www.youtube.com/channel/UCPvGypSgfDkVe7JG2KygK7A/featured Twitter @RindouMikoto ----- ※この映像は、 ...\",\"liveBroadcastContent\":\"none\",\"publishedAt\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595600932000},\"thumbnails\":{\"default\":{\"height\":90,\"url\":\"https://i.ytimg.com/vi/TNvHb412FsE/default.jpg\",\"width\":120},\"high\":{\"height\":360,\"url\":\"https://i.ytimg.com/vi/TNvHb412FsE/hqdefault.jpg\",\"width\":480},\"medium\":{\"height\":180,\"url\":\"https://i.ytimg.com/vi/TNvHb412FsE/mqdefault.jpg\",\"width\":320}},\"title\":\"【世界のアソビ大全51】竜胆尊VS周防パトラ！手加減なしバトルじゃ！！！【周防パトラ / ハニスト】\",\"publishTime\":\"2020-07-24T14:28:52Z\"}},{\"etag\":\"4ClAqdurT8_fJ5OyWQsx-ge9MLE\",\"id\":{\"kind\":\"youtube#video\",\"videoId\":\"3JFiJ7lNdnM\"},\"kind\":\"youtube#searchResult\",\"snippet\":{\"channelId\":\"UCeLzT-7b2PBcunJplmWtoDg\",\"channelTitle\":\"Patra Channel / 周防パトラ 【ハニスト】\",\"description\":\"話題のゴーストオブツシマを初見プレイ！ネタバレ禁止でお願いします！ I\\u0027m going to play an amazingly cool game of Ghosts of Tsushima! 公式からあらすじ 圧倒的な ...\",\"liveBroadcastContent\":\"none\",\"publishedAt\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595582258000},\"thumbnails\":{\"default\":{\"height\":90,\"url\":\"https://i.ytimg.com/vi/3JFiJ7lNdnM/default.jpg\",\"width\":120},\"high\":{\"height\":360,\"url\":\"https://i.ytimg.com/vi/3JFiJ7lNdnM/hqdefault.jpg\",\"width\":480},\"medium\":{\"height\":180,\"url\":\"https://i.ytimg.com/vi/3JFiJ7lNdnM/mqdefault.jpg\",\"width\":320}},\"title\":\"【ゴーストオブツシマ】初見プレイ！武士の道から外れ、邪道に落ちた兵冥人となれ#4　GhostofTsushima/PS4【周防パトラ / ハニスト】\",\"publishTime\":\"2020-07-24T09:17:38Z\"}},{\"etag\":\"rk9n9349eHTqUdj7EPcdsjxcaHU\",\"id\":{\"kind\":\"youtube#video\",\"videoId\":\"8W8uUtAo_Rg\"},\"kind\":\"youtube#searchResult\",\"snippet\":{\"channelId\":\"UCeLzT-7b2PBcunJplmWtoDg\",\"channelTitle\":\"Patra Channel / 周防パトラ 【ハニスト】\",\"description\":\"朝マイクラは作業するぞ！ I\\u0027m working on it in a relaxed manner. I also understand English, so feel free to comment. リスナー様へのお願い 基本的にはメンバーが ...\",\"liveBroadcastContent\":\"none\",\"publishedAt\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595643775000},\"thumbnails\":{\"default\":{\"height\":90,\"url\":\"https://i.ytimg.com/vi/8W8uUtAo_Rg/default.jpg\",\"width\":120},\"high\":{\"height\":360,\"url\":\"https://i.ytimg.com/vi/8W8uUtAo_Rg/hqdefault.jpg\",\"width\":480},\"medium\":{\"height\":180,\"url\":\"https://i.ytimg.com/vi/8W8uUtAo_Rg/mqdefault.jpg\",\"width\":320}},\"title\":\"【Minecraft】朝は色々な作業をまったりしよう！774.incマイクラ！I\\u0026#39;m going to work in peace.【周防パトラ / ハニスト】\",\"publishTime\":\"2020-07-25T02:22:55Z\"}},{\"etag\":\"rdABspjLBQlHqZcfW09c0fdp7Ak\",\"id\":{\"kind\":\"youtube#video\",\"videoId\":\"gHEe1iFsogs\"},\"kind\":\"youtube#searchResult\",\"snippet\":{\"channelId\":\"UCeLzT-7b2PBcunJplmWtoDg\",\"channelTitle\":\"Patra Channel / 周防パトラ 【ハニスト】\",\"description\":\"PS4バイオハザード5初見プレイ！ネタバレは聞いたら教えてほしいです！ へっぽこメアパト I understand English, so please feel free to comment! バイオハザード ...\",\"liveBroadcastContent\":\"none\",\"publishedAt\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595664012000},\"thumbnails\":{\"default\":{\"height\":90,\"url\":\"https://i.ytimg.com/vi/gHEe1iFsogs/default.jpg\",\"width\":120},\"high\":{\"height\":360,\"url\":\"https://i.ytimg.com/vi/gHEe1iFsogs/hqdefault.jpg\",\"width\":480},\"medium\":{\"height\":180,\"url\":\"https://i.ytimg.com/vi/gHEe1iFsogs/mqdefault.jpg\",\"width\":320}},\"title\":\"【バイオハザード5】今度こそ生き残れ！愛と絆？！初見プレイでがんばるぞ！#4【周防パトラ / ハニスト】\",\"publishTime\":\"2020-07-25T08:00:12Z\"}},{\"etag\":\"CD6jOT0ZxKHY-hAbiJOcmFM28g8\",\"id\":{\"kind\":\"youtube#video\",\"videoId\":\"_W-4P1jrHfI\"},\"kind\":\"youtube#searchResult\",\"snippet\":{\"channelId\":\"UCeLzT-7b2PBcunJplmWtoDg\",\"channelTitle\":\"Patra Channel / 周防パトラ 【ハニスト】\",\"description\":\"1stアルバムスペシャルMVの１つぶいちゅっばの歌です。 ぶいちゅっばを好きな人すべてにきいてほしいです。 ◇周防パトラ1stアルバム「あいあむなんばーわん！」 2020年8 ...\",\"liveBroadcastContent\":\"none\",\"publishedAt\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595674813000},\"thumbnails\":{\"default\":{\"height\":90,\"url\":\"https://i.ytimg.com/vi/_W-4P1jrHfI/default.jpg\",\"width\":120},\"high\":{\"height\":360,\"url\":\"https://i.ytimg.com/vi/_W-4P1jrHfI/hqdefault.jpg\",\"width\":480},\"medium\":{\"height\":180,\"url\":\"https://i.ytimg.com/vi/_W-4P1jrHfI/mqdefault.jpg\",\"width\":320}},\"title\":\"【オリジナル曲】ぶいちゅっばの歌 / 1stアルバムスペシャルMV【周防パトラ / ハニスト】\",\"publishTime\":\"2020-07-25T11:00:13Z\"}}],\"kind\":\"youtube#searchListResponse\",\"pageInfo\":{\"resultsPerPage\":5,\"totalResults\":5},\"regionCode\":\"JP\"}"
}