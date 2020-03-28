package com.example.holodule

import android.os.AsyncTask
import androidx.recyclerview.widget.RecyclerView
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.SearchListResponse
import com.google.gson.Gson
import java.lang.Exception

class ApiYTSearch() : AsyncTask<String, String, List<YTApiSearchResult.Item>>() {

    override fun doInBackground(vararg params: String?): List<YTApiSearchResult.Item>? {

        val youtube = YouTube.Builder(NetHttpTransport(), JacksonFactory(), null)
            .setApplicationName("youtube-cmdline-search-sample").build()
        val search = youtube.Search().list("id,snippet").setQ("UC1opHUrw8rvnsadT-iGp7Cg")
        var gson = Gson()
        try {
            /**TODO APIで実装を行う。*/
            //val ret = search.execute()
            var ret =testString
            val retJson = gson.fromJson(ret, YTApiSearchResult::class.java)
            return retJson.items

        }catch (ex:Exception){
            return null
        }
    }

    override fun onPostExecute(result: List<YTApiSearchResult.Item>?) {
        //ここで更新処理をおこなう。

    }

    //test用API返却値
    val testString="{\n" +
            " \"kind\": \"youtube#searchListResponse\",\n" +
            " \"etag\": \"\\\"ksCrgYQhtFrXgbHAhi9Fo5t0C2I/ZQDC-OVWT2lGrPLObszO2wpjZb8\\\"\",\n" +
            " \"nextPageToken\": \"CAUQAA\",\n" +
            " \"regionCode\": \"JP\",\n" +
            " \"pageInfo\": {\n" +
            "  \"totalResults\": 6732,\n" +
            "  \"resultsPerPage\": 5\n" +
            " },\n" +
            " \"items\": [\n" +
            "  {\n" +
            "   \"kind\": \"youtube#searchResult\",\n" +
            "   \"etag\": \"\\\"ksCrgYQhtFrXgbHAhi9Fo5t0C2I/cyHjRV6Cjw04GuJ9u2wMn40trkQ\\\"\",\n" +
            "   \"id\": {\n" +
            "    \"kind\": \"youtube#channel\",\n" +
            "    \"channelId\": \"UC1opHUrw8rvnsadT-iGp7Cg\"\n" +
            "   },\n" +
            "   \"snippet\": {\n" +
            "    \"publishedAt\": \"2018-08-01T06:38:45.000Z\",\n" +
            "    \"channelId\": \"UC1opHUrw8rvnsadT-iGp7Cg\",\n" +
            "    \"title\": \"Aqua Ch. 湊あくあ\",\n" +
            "    \"description\": \"バーチャルメイド⚓️湊あくあ(みなとあくあ)です！ど、ドジとか言わないでください！ 放送で色んな変わったゲームや雑談をしています…！！...\",\n" +
            "    \"thumbnails\": {\n" +
            "     \"default\": {\n" +
            "      \"url\": \"https://yt3.ggpht.com/-y2AiOii0yTM/AAAAAAAAAAI/AAAAAAAAAAA/fvrDtNkd7kI/s88-c-k-no-mo-rj-c0xffffff/photo.jpg\"\n" +
            "     },\n" +
            "     \"medium\": {\n" +
            "      \"url\": \"https://yt3.ggpht.com/-y2AiOii0yTM/AAAAAAAAAAI/AAAAAAAAAAA/fvrDtNkd7kI/s240-c-k-no-mo-rj-c0xffffff/photo.jpg\"\n" +
            "     },\n" +
            "     \"high\": {\n" +
            "      \"url\": \"https://yt3.ggpht.com/-y2AiOii0yTM/AAAAAAAAAAI/AAAAAAAAAAA/fvrDtNkd7kI/s800-c-k-no-mo-rj-c0xffffff/photo.jpg\"\n" +
            "     }\n" +
            "    },\n" +
            "    \"channelTitle\": \"Aqua Ch. 湊あくあ\",\n" +
            "    \"liveBroadcastContent\": \"upcoming\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"youtube#searchResult\",\n" +
            "   \"etag\": \"\\\"ksCrgYQhtFrXgbHAhi9Fo5t0C2I/_Zi-0_BgrY04gX4yR7YIxgFbQe0\\\"\",\n" +
            "   \"id\": {\n" +
            "    \"kind\": \"youtube#video\",\n" +
            "    \"videoId\": \"VI8o8aJ5rK0\"\n" +
            "   },\n" +
            "   \"snippet\": {\n" +
            "    \"publishedAt\": \"2020-03-28T00:09:25.000Z\",\n" +
            "    \"channelId\": \"UC8zpmQeA0Z_4R-EM7kIRcWw\",\n" +
            "    \"title\": \"【CHAOS】All weird sounds Aqua made in 1 hour\",\n" +
            "    \"description\": \"original video https://www.youtube.com/watch?v=M4MEtEnD5EM Minato Aqua's channel https://www.youtube.com/channel/UC1opHUrw8rvnsadT-iGp7Cg #湊 ...\",\n" +
            "    \"thumbnails\": {\n" +
            "     \"default\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/VI8o8aJ5rK0/default.jpg\",\n" +
            "      \"width\": 120,\n" +
            "      \"height\": 90\n" +
            "     },\n" +
            "     \"medium\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/VI8o8aJ5rK0/mqdefault.jpg\",\n" +
            "      \"width\": 320,\n" +
            "      \"height\": 180\n" +
            "     },\n" +
            "     \"high\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/VI8o8aJ5rK0/hqdefault.jpg\",\n" +
            "      \"width\": 480,\n" +
            "      \"height\": 360\n" +
            "     }\n" +
            "    },\n" +
            "    \"channelTitle\": \"Vtuber clips\",\n" +
            "    \"liveBroadcastContent\": \"none\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"youtube#searchResult\",\n" +
            "   \"etag\": \"\\\"ksCrgYQhtFrXgbHAhi9Fo5t0C2I/sZT9_fSxDGDGBIZ0Yrrz8KzMYf0\\\"\",\n" +
            "   \"id\": {\n" +
            "    \"kind\": \"youtube#video\",\n" +
            "    \"videoId\": \"cB5Wh4K5v6g\"\n" +
            "   },\n" +
            "   \"snippet\": {\n" +
            "    \"publishedAt\": \"2020-01-07T13:44:47.000Z\",\n" +
            "    \"channelId\": \"UC8zpmQeA0Z_4R-EM7kIRcWw\",\n" +
            "    \"title\": \"【3D Minato Aqua】Cute moment with Noeru\",\n" +
            "    \"description\": \"First 3D collab of Aqua and Noeru! ↓Original video↓ https://www.youtube.com/watch?v=hOleS05Au9c ↓Minato Aqua's channel↓ ...\",\n" +
            "    \"thumbnails\": {\n" +
            "     \"default\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/cB5Wh4K5v6g/default.jpg\",\n" +
            "      \"width\": 120,\n" +
            "      \"height\": 90\n" +
            "     },\n" +
            "     \"medium\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/cB5Wh4K5v6g/mqdefault.jpg\",\n" +
            "      \"width\": 320,\n" +
            "      \"height\": 180\n" +
            "     },\n" +
            "     \"high\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/cB5Wh4K5v6g/hqdefault.jpg\",\n" +
            "      \"width\": 480,\n" +
            "      \"height\": 360\n" +
            "     }\n" +
            "    },\n" +
            "    \"channelTitle\": \"Vtuber clips\",\n" +
            "    \"liveBroadcastContent\": \"none\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"youtube#searchResult\",\n" +
            "   \"etag\": \"\\\"ksCrgYQhtFrXgbHAhi9Fo5t0C2I/8M8qVeSfMkoxsJr3uvabk4q4JP4\\\"\",\n" +
            "   \"id\": {\n" +
            "    \"kind\": \"youtube#video\",\n" +
            "    \"videoId\": \"4rYx6pyfVBk\"\n" +
            "   },\n" +
            "   \"snippet\": {\n" +
            "    \"publishedAt\": \"2019-12-08T01:11:41.000Z\",\n" +
            "    \"channelId\": \"UCrYMKte9Ocjh0wSOMI67-zw\",\n" +
            "    \"title\": \"Aqua&#39;s Minecraft Neighbor\",\n" +
            "    \"description\": \"Minato Aqua has a spat with her neighbor in the Hololive Minecraft server source: https://www.youtube.com/watch?v=N99L2ALq2LI sub to aqua: ...\",\n" +
            "    \"thumbnails\": {\n" +
            "     \"default\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/4rYx6pyfVBk/default.jpg\",\n" +
            "      \"width\": 120,\n" +
            "      \"height\": 90\n" +
            "     },\n" +
            "     \"medium\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/4rYx6pyfVBk/mqdefault.jpg\",\n" +
            "      \"width\": 320,\n" +
            "      \"height\": 180\n" +
            "     },\n" +
            "     \"high\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/4rYx6pyfVBk/hqdefault.jpg\",\n" +
            "      \"width\": 480,\n" +
            "      \"height\": 360\n" +
            "     }\n" +
            "    },\n" +
            "    \"channelTitle\": \"helpless\",\n" +
            "    \"liveBroadcastContent\": \"none\"\n" +
            "   }\n" +
            "  },\n" +
            "  {\n" +
            "   \"kind\": \"youtube#searchResult\",\n" +
            "   \"etag\": \"\\\"ksCrgYQhtFrXgbHAhi9Fo5t0C2I/AGW5V1rUZPGR5uxhUkdUGjfSY6U\\\"\",\n" +
            "   \"id\": {\n" +
            "    \"kind\": \"youtube#video\",\n" +
            "    \"videoId\": \"6wLNR3fZFaI\"\n" +
            "   },\n" +
            "   \"snippet\": {\n" +
            "    \"publishedAt\": \"2019-10-01T14:59:23.000Z\",\n" +
            "    \"channelId\": \"UC1opHUrw8rvnsadT-iGp7Cg\",\n" +
            "    \"title\": \"【LOL】プラチナメイドcarryします！！！【湊あくあ】\",\n" +
            "    \"description\": \"【コラボ相手】 日ノ隈らん https://www.youtube.com/channel/UCRvpMpzAXBRKJQuk-8-Sdvg 鷹宮リオン ...\",\n" +
            "    \"thumbnails\": {\n" +
            "     \"default\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/6wLNR3fZFaI/default.jpg\",\n" +
            "      \"width\": 120,\n" +
            "      \"height\": 90\n" +
            "     },\n" +
            "     \"medium\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/6wLNR3fZFaI/mqdefault.jpg\",\n" +
            "      \"width\": 320,\n" +
            "      \"height\": 180\n" +
            "     },\n" +
            "     \"high\": {\n" +
            "      \"url\": \"https://i.ytimg.com/vi/6wLNR3fZFaI/hqdefault.jpg\",\n" +
            "      \"width\": 480,\n" +
            "      \"height\": 360\n" +
            "     }\n" +
            "    },\n" +
            "    \"channelTitle\": \"Aqua Ch. 湊あくあ\",\n" +
            "    \"liveBroadcastContent\": \"none\"\n" +
            "   }\n" +
            "  }\n" +
            " ]\n" +
            "}\n"
}