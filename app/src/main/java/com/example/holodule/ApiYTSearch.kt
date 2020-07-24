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
    private val testAPI = "{" +
            "  \"kind\": \"youtube#searchListResponse\"," +
            "  \"etag\": \"3PyItLFI7QD0jn2CPgSDJF7w3l0\"," +
            "  \"regionCode\": \"JP\"," +
            "  \"pageInfo\": {" +
            "    \"totalResults\": 4," +
            "    \"resultsPerPage\": 5" +
            "  }," +
            "  \"items\": [" +
            "    {" +
            "      \"kind\": \"youtube#searchResult\"," +
            "      \"etag\": \"Qu8Q4exRmpZ6-_hkvMJM3XgjLf0\"," +
            "      \"id\": {" +
            "        \"kind\": \"youtube#video\"," +
            "        \"videoId\": \"GvYht8MsqpE\"" +
            "      }," +
            "      \"snippet\": {" +
            "        \"publishedAt\": \"2020-07-23T14:07:33Z\"," +
            "        \"channelId\": \"UCeLzT-7b2PBcunJplmWtoDg\"," +
            "        \"title\": \"【2.0お披露目】パトラ進化！あの顔までできる！めちゃくちゃ動く！しっぽも足も動く！やばああああ！【周防パトラ / ハニスト】\"," +
            "        \"description\": \"みんなみてみてみてー！！！！！ 新曲 一目惚れパラドックス https://youtu.be/uC3ORIZfxdE 撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです ...\"," +
            "        \"thumbnails\": {" +
            "          \"default\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/GvYht8MsqpE/default.jpg\"," +
            "            \"width\": 120," +
            "            \"height\": 90" +
            "          }," +
            "          \"medium\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/GvYht8MsqpE/mqdefault.jpg\"," +
            "            \"width\": 320," +
            "            \"height\": 180" +
            "          }," +
            "          \"high\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/GvYht8MsqpE/hqdefault.jpg\"," +
            "            \"width\": 480," +
            "            \"height\": 360" +
            "          }" +
            "        }," +
            "        \"channelTitle\": \"Patra Channel / 周防パトラ 【ハニスト】\"," +
            "        \"liveBroadcastContent\": \"none\"," +
            "        \"publishTime\": \"2020-07-23T14:07:33Z\"" +
            "      }" +
            "    }," +
            "    {" +
            "      \"kind\": \"youtube#searchResult\"," +
            "      \"etag\": \"mg8C5q-7JztQN3w6y3OT1F6mWgo\"," +
            "      \"id\": {" +
            "        \"kind\": \"youtube#video\"," +
            "        \"videoId\": \"Pt1h52RnFnc\"" +
            "      }," +
            "      \"snippet\": {" +
            "        \"publishedAt\": \"2020-07-23T09:39:16Z\"," +
            "        \"channelId\": \"UCeLzT-7b2PBcunJplmWtoDg\"," +
            "        \"title\": \"【Minecraft】トライデントが欲しいから耐久しよう！774.incマイクラ！【周防パトラ / ハニスト】\"," +
            "        \"description\": \"メアリお姉さまとトライデントGETだぜ！ I'm working on it in a relaxed manner. I also understand English, so feel free to comment. リスナー様へのお願い 基本的には ...\"," +
            "        \"thumbnails\": {" +
            "          \"default\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/Pt1h52RnFnc/default.jpg\"," +
            "            \"width\": 120," +
            "            \"height\": 90" +
            "          }," +
            "          \"medium\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/Pt1h52RnFnc/mqdefault.jpg\"," +
            "            \"width\": 320," +
            "            \"height\": 180" +
            "          }," +
            "          \"high\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/Pt1h52RnFnc/hqdefault.jpg\"," +
            "            \"width\": 480," +
            "            \"height\": 360" +
            "          }" +
            "        }," +
            "        \"channelTitle\": \"Patra Channel / 周防パトラ 【ハニスト】\"," +
            "        \"liveBroadcastContent\": \"none\"," +
            "        \"publishTime\": \"2020-07-23T09:39:16Z\"" +
            "      }" +
            "    }," +
            "    {" +
            "      \"kind\": \"youtube#searchResult\"," +
            "      \"etag\": \"hKtQmwMmPzhaZpXyaxgAud6rkAo\"," +
            "      \"id\": {" +
            "        \"kind\": \"youtube#video\"," +
            "        \"videoId\": \"TNvHb412FsE\"" +
            "      }," +
            "      \"snippet\": {" +
            "        \"publishedAt\": \"2020-07-24T01:09:28Z\"," +
            "        \"channelId\": \"UCeLzT-7b2PBcunJplmWtoDg\"," +
            "        \"title\": \"【世界のアソビ大全51】竜胆尊VS周防パトラ！手加減なしバトルじゃ！！！【周防パトラ / ハニスト】\"," +
            "        \"description\": \"竜胆尊ちゃんと遊ぶよ～   竜胆尊ちゃん   https://www.youtube.com/channel/UCPvGypSgfDkVe7JG2KygK7A/featured Twitter @RindouMikoto ----- ※この映像は、 ...\"," +
            "        \"thumbnails\": {" +
            "          \"default\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/TNvHb412FsE/default_live.jpg\"," +
            "            \"width\": 120," +
            "            \"height\": 90" +
            "          }," +
            "          \"medium\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/TNvHb412FsE/mqdefault_live.jpg\"," +
            "            \"width\": 320," +
            "            \"height\": 180" +
            "          }," +
            "          \"high\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/TNvHb412FsE/hqdefault_live.jpg\"," +
            "            \"width\": 480," +
            "            \"height\": 360" +
            "          }" +
            "        }," +
            "        \"channelTitle\": \"Patra Channel / 周防パトラ 【ハニスト】\"," +
            "        \"liveBroadcastContent\": \"upcoming\"," +
            "        \"publishTime\": \"2020-07-24T01:09:28Z\"" +
            "      }" +
            "    }," +
            "    {" +
            "      \"kind\": \"youtube#searchResult\"," +
            "      \"etag\": \"nQCKh5r-DnpUqIqKewyq116bcao\"," +
            "      \"id\": {" +
            "        \"kind\": \"youtube#video\"," +
            "        \"videoId\": \"3JFiJ7lNdnM\"" +
            "      }," +
            "      \"snippet\": {" +
            "        \"publishedAt\": \"2020-07-24T00:33:54Z\"," +
            "        \"channelId\": \"UCeLzT-7b2PBcunJplmWtoDg\"," +
            "        \"title\": \"【ゴーストオブツシマ】初見プレイ！武士の道から外れ、邪道に落ちた兵冥人となれ#4　GhostofTsushima/PS4【周防パトラ / ハニスト】\"," +
            "        \"description\": \"話題のゴーストオブツシマを初見プレイ！ネタバレ禁止でお願いします！ I'm going to play an amazingly cool game of Ghosts of Tsushima! 公式からあらすじ 圧倒的な ...\"," +
            "        \"thumbnails\": {" +
            "          \"default\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/3JFiJ7lNdnM/default_live.jpg\"," +
            "            \"width\": 120," +
            "            \"height\": 90" +
            "          }," +
            "          \"medium\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/3JFiJ7lNdnM/mqdefault_live.jpg\"," +
            "            \"width\": 320," +
            "            \"height\": 180" +
            "          }," +
            "          \"high\": {" +
            "            \"url\": \"https://i.ytimg.com/vi/3JFiJ7lNdnM/hqdefault_live.jpg\"," +
            "            \"width\": 480," +
            "            \"height\": 360" +
            "          }" +
            "        }," +
            "        \"channelTitle\": \"Patra Channel / 周防パトラ 【ハニスト】\"," +
            "        \"liveBroadcastContent\": \"upcoming\"," +
            "        \"publishTime\": \"2020-07-24T00:33:54Z\"" +
            "      }" +
            "    }" +
            "  ]" +
            "}"
}