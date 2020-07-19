package com.example.holodule

import android.os.AsyncTask
import androidx.recyclerview.widget.RecyclerView
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.SearchListResponse
import com.google.gson.Gson
import java.lang.Exception

class ApiSearchVideoList() : AsyncTask<String, String, List<YTApiVideo.Item>>() {

    companion object{
        private const val apiKey:String = "AIzaSyCQKLwyW0ZgQd_itFgrVLj68xhCxFcOiO0"
    }

    override fun doInBackground(vararg params: String?): List<YTApiVideo.Item>? {

        val youtube = YouTube.Builder(NetHttpTransport(), JacksonFactory(), null)
            .setApplicationName("youtube-cmdline-search-sample").build()
        val videoSearch = youtube.Videos().list("id").setId("QA-UY6jX5P4")
        videoSearch.key = apiKey
        var gson = Gson()
        return try {
            /**TODO APIで実装を行う。*/
            val result = videoSearch.execute()
            val jsonTest = gson.toJson(result)
            val retJson = gson.fromJson(jsonTest, YTApiVideo::class.java)

            //テスト用コード
            //var ret =testAPI
            //val retJson = gson.fromJson(ret, YTApiSearchResult::class.java)
            retJson.items

        }catch (ex:Exception){
            null
        }
    }

    override fun onPostExecute(result: List<YTApiVideo.Item>?) {
        //ここで更新処理をおこなう。

    }

    //test用API返却値
    private val testAPI ="{\n" +
            "    \"etag\": \"E_1jsMS0LZKvdg9jbymC3DHGLKo\",\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"etag\": \"aGA2dpfKCOmIApu22-zosaINHmQ\",\n" +
            "            \"id\": {\n" +
            "                \"channelId\": \"UC1opHUrw8rvnsadT-iGp7Cg\",\n" +
            "                \"kind\": \"youtube#channel\"\n" +
            "            },\n" +
            "            \"kind\": \"youtube#searchResult\",\n" +
            "            \"snippet\": {\n" +
            "                \"channelId\": \"UC1opHUrw8rvnsadT-iGp7Cg\",\n" +
            "                \"channelTitle\": \"Aqua Ch. 湊あくあ\",\n" +
            "                \"description\": \"バーチャルメイド⚓️湊あくあ(みなとあくあ)です！ど、ドジとか言わないでください！ 放送で色んな変わったゲームや雑談をしています…！！...\",\n" +
            "                \"liveBroadcastContent\": \"none\",\n" +
            "                \"publishedAt\": {\n" +
            "                    \"dateOnly\": false,\n" +
            "                    \"tzShift\": 0,\n" +
            "                    \"value\": 1533105525000\n" +
            "                },\n" +
            "                \"thumbnails\": {\n" +
            "                    \"default\": {\n" +
            "                        \"url\": \"https://yt3.ggpht.com/-y2AiOii0yTM/AAAAAAAAAAI/AAAAAAAAAAA/fvrDtNkd7kI/s88-c-k-no-mo-rj-c0xffffff/photo.jpg\"\n" +
            "                    },\n" +
            "                    \"high\": {\n" +
            "                        \"url\": \"https://yt3.ggpht.com/-y2AiOii0yTM/AAAAAAAAAAI/AAAAAAAAAAA/fvrDtNkd7kI/s800-c-k-no-mo-rj-c0xffffff/photo.jpg\"\n" +
            "                    },\n" +
            "                    \"medium\": {\n" +
            "                        \"url\": \"https://yt3.ggpht.com/-y2AiOii0yTM/AAAAAAAAAAI/AAAAAAAAAAA/fvrDtNkd7kI/s240-c-k-no-mo-rj-c0xffffff/photo.jpg\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"title\": \"Aqua Ch. 湊あくあ\",\n" +
            "                \"publishTime\": \"2018-08-01T06:38:45Z\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"etag\": \"tJAEFlwdhbhQ7rDZseqj2AtU168\",\n" +
            "            \"id\": {\n" +
            "                \"kind\": \"youtube#video\",\n" +
            "                \"videoId\": \"v8vVb3G1afo\"\n" +
            "            },\n" +
            "            \"kind\": \"youtube#searchResult\",\n" +
            "            \"snippet\": {\n" +
            "                \"channelId\": \"UCVzlYSqUhQwKxw6qFaP0NJw\",\n" +
            "                \"channelTitle\": \"Rezado-subs\",\n" +
            "                \"description\": \"Collection of Aqua Jumpscare screams and adventure in the Horror Game classic Clock Tower. ❀Source❀ https://www.youtube.com/watch?v=Vs6ZaW2KHI8 Be ...\",\n" +
            "                \"liveBroadcastContent\": \"none\",\n" +
            "                \"publishedAt\": {\n" +
            "                    \"dateOnly\": false,\n" +
            "                    \"tzShift\": 0,\n" +
            "                    \"value\": 1594618078000\n" +
            "                },\n" +
            "                \"thumbnails\": {\n" +
            "                    \"default\": {\n" +
            "                        \"height\": 90,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/v8vVb3G1afo/default.jpg\",\n" +
            "                        \"width\": 120\n" +
            "                    },\n" +
            "                    \"high\": {\n" +
            "                        \"height\": 360,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/v8vVb3G1afo/hqdefault.jpg\",\n" +
            "                        \"width\": 480\n" +
            "                    },\n" +
            "                    \"medium\": {\n" +
            "                        \"height\": 180,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/v8vVb3G1afo/mqdefault.jpg\",\n" +
            "                        \"width\": 320\n" +
            "                    }\n" +
            "                },\n" +
            "                \"title\": \"Minato Aqua in The Clock Tower.\",\n" +
            "                \"publishTime\": \"2020-07-13T05:27:58Z\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"etag\": \"I7fpv76XBxvjBXc5mI_EGDgEKFo\",\n" +
            "            \"id\": {\n" +
            "                \"kind\": \"youtube#video\",\n" +
            "                \"videoId\": \"ZTZLepOK3S4\"\n" +
            "            },\n" +
            "            \"kind\": \"youtube#searchResult\",\n" +
            "            \"snippet\": {\n" +
            "                \"channelId\": \"UC8zpmQeA0Z_4R-EM7kIRcWw\",\n" +
            "                \"channelTitle\": \"Vtuber clips\",\n" +
            "                \"description\": \"original video https://www.youtube.com/watch?v=o41aBPpm8nQ Minato Aqua's channel https://www.youtube.com/channel/UC1opHUrw8rvnsadT-iGp7Cg My ...\",\n" +
            "                \"liveBroadcastContent\": \"none\",\n" +
            "                \"publishedAt\": {\n" +
            "                    \"dateOnly\": false,\n" +
            "                    \"tzShift\": 0,\n" +
            "                    \"value\": 1591913897000\n" +
            "                },\n" +
            "                \"thumbnails\": {\n" +
            "                    \"default\": {\n" +
            "                        \"height\": 90,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/ZTZLepOK3S4/default.jpg\",\n" +
            "                        \"width\": 120\n" +
            "                    },\n" +
            "                    \"high\": {\n" +
            "                        \"height\": 360,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/ZTZLepOK3S4/hqdefault.jpg\",\n" +
            "                        \"width\": 480\n" +
            "                    },\n" +
            "                    \"medium\": {\n" +
            "                        \"height\": 180,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/ZTZLepOK3S4/mqdefault.jpg\",\n" +
            "                        \"width\": 320\n" +
            "                    }\n" +
            "                },\n" +
            "                \"title\": \"【ENGsub】Embarrassing Episode of Minato Aqua\",\n" +
            "                \"publishTime\": \"2020-06-11T22:18:17Z\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"etag\": \"j6gvl_tvis0S41CbZuYMkFhVg98\",\n" +
            "            \"id\": {\n" +
            "                \"kind\": \"youtube#video\",\n" +
            "                \"videoId\": \"xtKVzs2LRAI\"\n" +
            "            },\n" +
            "            \"kind\": \"youtube#searchResult\",\n" +
            "            \"snippet\": {\n" +
            "                \"channelId\": \"UC5hYWH4J8gYxQmyuyWWC8gw\",\n" +
            "                \"channelTitle\": \"Angle\",\n" +
            "                \"description\": \"It was only Aqua at first, but now it's spread.. Everyone's Channels! Mio (Black in White): https://www.youtube.com/channel/UCp-5t9SrOQwXMU7iIjQfARg Aqua ...\",\n" +
            "                \"liveBroadcastContent\": \"none\",\n" +
            "                \"publishedAt\": {\n" +
            "                    \"dateOnly\": false,\n" +
            "                    \"tzShift\": 0,\n" +
            "                    \"value\": 1588712645000\n" +
            "                },\n" +
            "                \"thumbnails\": {\n" +
            "                    \"default\": {\n" +
            "                        \"height\": 90,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/xtKVzs2LRAI/default.jpg\",\n" +
            "                        \"width\": 120\n" +
            "                    },\n" +
            "                    \"high\": {\n" +
            "                        \"height\": 360,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/xtKVzs2LRAI/hqdefault.jpg\",\n" +
            "                        \"width\": 480\n" +
            "                    },\n" +
            "                    \"medium\": {\n" +
            "                        \"height\": 180,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/xtKVzs2LRAI/mqdefault.jpg\",\n" +
            "                        \"width\": 320\n" +
            "                    }\n" +
            "                },\n" +
            "                \"title\": \"Pikapandemic【UNO】\",\n" +
            "                \"publishTime\": \"2020-05-05T21:04:05Z\"\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            \"etag\": \"b_rG5Xz7uTQA6WHnfhJfVzppol0\",\n" +
            "            \"id\": {\n" +
            "                \"kind\": \"youtube#video\",\n" +
            "                \"videoId\": \"rBxuPbeoiXM\"\n" +
            "            },\n" +
            "            \"kind\": \"youtube#searchResult\",\n" +
            "            \"snippet\": {\n" +
            "                \"channelId\": \"UCm-n2HnhndQylpmCYhE3mng\",\n" +
            "                \"channelTitle\": \"Vtuber翻譯軍\",\n" +
            "                \"description\": \"嘗試剪了一下，渣剪請見諒~ 有什麼建議可以在底下留言，希望可以跟大家交流交流@@ ---------------------------------------------------------------------------------...\",\n" +
            "                \"liveBroadcastContent\": \"none\",\n" +
            "                \"publishedAt\": {\n" +
            "                    \"dateOnly\": false,\n" +
            "                    \"tzShift\": 0,\n" +
            "                    \"value\": 1593001750000\n" +
            "                },\n" +
            "                \"thumbnails\": {\n" +
            "                    \"default\": {\n" +
            "                        \"height\": 90,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/rBxuPbeoiXM/default.jpg\",\n" +
            "                        \"width\": 120\n" +
            "                    },\n" +
            "                    \"high\": {\n" +
            "                        \"height\": 360,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/rBxuPbeoiXM/hqdefault.jpg\",\n" +
            "                        \"width\": 480\n" +
            "                    },\n" +
            "                    \"medium\": {\n" +
            "                        \"height\": 180,\n" +
            "                        \"url\": \"https://i.ytimg.com/vi/rBxuPbeoiXM/mqdefault.jpg\",\n" +
            "                        \"width\": 320\n" +
            "                    }\n" +
            "                },\n" +
            "                \"title\": \"【湊阿庫婭、星街彗星、角卷綿芽、天音彼方】4人一起合唱紅蓮華【紅蓮華/LiSA】\",\n" +
            "                \"publishTime\": \"2020-06-24T12:29:10Z\"\n" +
            "            }\n" +
            "        }\n" +
            "    ],\n" +
            "    \"kind\": \"youtube#searchListResponse\",\n" +
            "    \"nextPageToken\": \"CAUQAA\",\n" +
            "    \"pageInfo\": {\n" +
            "        \"resultsPerPage\": 5,\n" +
            "        \"totalResults\": 3528\n" +
            "    },\n" +
            "    \"regionCode\": \"JP\"\n" +
            "}"
}