package com.example.holodule

import android.os.AsyncTask
import androidx.recyclerview.widget.RecyclerView
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.SearchListResponse
import com.google.gson.Gson
import java.lang.Exception

class ApiSearchVideoList() : AsyncTask<List<String>, String, List<YTApiVideo.Item>>() {

    companion object {
        private const val apiKey: String = "AIzaSyCQKLwyW0ZgQd_itFgrVLj68xhCxFcOiO0"
    }

    override fun doInBackground(vararg params: List<String>?): List<YTApiVideo.Item>? {

        val videoIdList = params[0] ?: return null

        val youtube = YouTube.Builder(NetHttpTransport(), JacksonFactory(), null)
            .setApplicationName("youtube-cmdline-search-sample").build()
        val videoSearch =
            youtube.Videos().list("liveStreamingDetails,snippet").setId(videoIdList.joinToString())
        videoSearch.key = apiKey
        var gson = Gson()
        return try {
            /**TODO APIで実装を行う。*/
            if (BuildMode.MODE.testMode) {
                val jsonTest = testAPI
                val retJson = gson.fromJson(jsonTest, YTApiVideo::class.java)
                retJson.items?.sortedBy { it.liveStreamingDetails?.scheduledStartTime}
            } else {
                val result = videoSearch.execute()
                val jsonTest = gson.toJson(result)
                val retJson = gson.fromJson(jsonTest, YTApiVideo::class.java)
                retJson.items?.sortedBy { it.liveStreamingDetails?.scheduledStartTime}
            }
        } catch (ex: Exception) {
            null
        }
    }

    override fun onPostExecute(result: List<YTApiVideo.Item>?) {
        //ここで更新処理をおこなう。
    }

    //test用API返却値
    private val testAPI = "{\n" +
            "  \"kind\": \"youtube#videoListResponse\",\n" +
            "  \"etag\": \"_l6Kdw4CDwdfnCAo3XvaK2HRphU\",\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"kind\": \"youtube#video\",\n" +
            "      \"etag\": \"AYhZjTSli7TkYo6iouo2bMZRHDU\",\n" +
            "      \"id\": \"GvYht8MsqpE\",\n" +
            "      \"snippet\": {\n" +
            "        \"publishedAt\": \"2020-07-23T14:07:33Z\",\n" +
            "        \"channelId\": \"UCeLzT-7b2PBcunJplmWtoDg\",\n" +
            "        \"title\": \"【2.0お披露目】パトラ進化！あの顔までできる！めちゃくちゃ動く！しっぽも足も動く！やばああああ！【周防パトラ / ハニスト】\",\n" +
            "        \"description\": \"みんなみてみてみてー！！！！！\\n\\n新曲　一目惚れパラドックス　https://youtu.be/uC3ORIZfxdE\\n\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\n　内緒のモンスター⚡\\nhttps://youtu.be/eyJkLKYGaoU\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774shouten.booth.pm/\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation=1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation=1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation=1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation=1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation=1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\n" +
            "        \"thumbnails\": {\n" +
            "          \"default\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/GvYht8MsqpE/default.jpg\",\n" +
            "            \"width\": 120,\n" +
            "            \"height\": 90\n" +
            "          },\n" +
            "          \"medium\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/GvYht8MsqpE/mqdefault.jpg\",\n" +
            "            \"width\": 320,\n" +
            "            \"height\": 180\n" +
            "          },\n" +
            "          \"high\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/GvYht8MsqpE/hqdefault.jpg\",\n" +
            "            \"width\": 480,\n" +
            "            \"height\": 360\n" +
            "          },\n" +
            "          \"standard\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/GvYht8MsqpE/sddefault.jpg\",\n" +
            "            \"width\": 640,\n" +
            "            \"height\": 480\n" +
            "          },\n" +
            "          \"maxres\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/GvYht8MsqpE/maxresdefault.jpg\",\n" +
            "            \"width\": 1280,\n" +
            "            \"height\": 720\n" +
            "          }\n" +
            "        },\n" +
            "        \"channelTitle\": \"Patra Channel / 周防パトラ 【ハニスト】\",\n" +
            "        \"tags\": [\n" +
            "          \"Patra\",\n" +
            "          \"パトラ\",\n" +
            "          \"周防パトラ\",\n" +
            "          \"ハニスト\",\n" +
            "          \"HoneyStrap\",\n" +
            "          \"Vtuber\",\n" +
            "          \"バーチャルYouTuber\",\n" +
            "          \"バーチャルユーチューバー\",\n" +
            "          \"アニメ\",\n" +
            "          \"Anime\",\n" +
            "          \"VR\",\n" +
            "          \"カワボ\",\n" +
            "          \"萌え声\",\n" +
            "          \"アニメ声\",\n" +
            "          \"Gaming\",\n" +
            "          \"雑談\",\n" +
            "          \"talk\",\n" +
            "          \"まったり\",\n" +
            "          \"深夜\"\n" +
            "        ],\n" +
            "        \"categoryId\": \"24\",\n" +
            "        \"liveBroadcastContent\": \"none\",\n" +
            "        \"localized\": {\n" +
            "          \"title\": \"【2.0お披露目】パトラ進化！あの顔までできる！めちゃくちゃ動く！しっぽも足も動く！やばああああ！【周防パトラ / ハニスト】\",\n" +
            "          \"description\": \"みんなみてみてみてー！！！！！\\n\\n新曲　一目惚れパラドックス　https://youtu.be/uC3ORIZfxdE\\n\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\n　内緒のモンスター⚡\\nhttps://youtu.be/eyJkLKYGaoU\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774shouten.booth.pm/\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation=1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation=1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation=1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation=1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation=1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"liveStreamingDetails\": {\n" +
            "        \"actualStartTime\": \"2020-07-23T13:00:10.479000Z\",\n" +
            "        \"actualEndTime\": \"2020-07-23T14:02:07Z\",\n" +
            "        \"scheduledStartTime\": \"2020-07-23T13:00:00Z\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"kind\": \"youtube#video\",\n" +
            "      \"etag\": \"EMkTCuf9upkhNJAXdSg8WDpDGZE\",\n" +
            "      \"id\": \"Pt1h52RnFnc\",\n" +
            "      \"snippet\": {\n" +
            "        \"publishedAt\": \"2020-07-23T09:39:16Z\",\n" +
            "        \"channelId\": \"UCeLzT-7b2PBcunJplmWtoDg\",\n" +
            "        \"title\": \"【Minecraft】トライデントが欲しいから耐久しよう！774.incマイクラ！【周防パトラ / ハニスト】\",\n" +
            "        \"description\": \"メアリお姉さまとトライデントGETだぜ！\\nI'm working on it in a relaxed manner.\\nI also understand English, so feel free to comment.\\nリスナー様へのお願い\\n基本的にはメンバーが自由に楽しくマインクラフトをプレイできるよう\\nアドバイス等嬉しいですが、ルールを強要するコメントは控えていただけると嬉しいです。\\nなにかトラブルがあってもメンバーで協力、解決するので温かく見守っていただけたらと思います。\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニストコミケグッズ\\n4/24(金)18:00 ~ 5/10(日)23:59 まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation=1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation=1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation=1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation=1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation=1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\n" +
            "        \"thumbnails\": {\n" +
            "          \"default\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/Pt1h52RnFnc/default.jpg\",\n" +
            "            \"width\": 120,\n" +
            "            \"height\": 90\n" +
            "          },\n" +
            "          \"medium\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/Pt1h52RnFnc/mqdefault.jpg\",\n" +
            "            \"width\": 320,\n" +
            "            \"height\": 180\n" +
            "          },\n" +
            "          \"high\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/Pt1h52RnFnc/hqdefault.jpg\",\n" +
            "            \"width\": 480,\n" +
            "            \"height\": 360\n" +
            "          },\n" +
            "          \"standard\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/Pt1h52RnFnc/sddefault.jpg\",\n" +
            "            \"width\": 640,\n" +
            "            \"height\": 480\n" +
            "          },\n" +
            "          \"maxres\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/Pt1h52RnFnc/maxresdefault.jpg\",\n" +
            "            \"width\": 1280,\n" +
            "            \"height\": 720\n" +
            "          }\n" +
            "        },\n" +
            "        \"channelTitle\": \"Patra Channel / 周防パトラ 【ハニスト】\",\n" +
            "        \"tags\": [\n" +
            "          \"Patra\",\n" +
            "          \"パトラ\",\n" +
            "          \"周防パトラ\",\n" +
            "          \"ハニスト\",\n" +
            "          \"HoneyStrap\",\n" +
            "          \"Vtuber\",\n" +
            "          \"バーチャルYouTuber\",\n" +
            "          \"バーチャルユーチューバー\",\n" +
            "          \"アニメ\",\n" +
            "          \"Anime\",\n" +
            "          \"VR\",\n" +
            "          \"カワボ\",\n" +
            "          \"萌え声\",\n" +
            "          \"アニメ声\",\n" +
            "          \"Gaming\",\n" +
            "          \"マイクラ\",\n" +
            "          \"Minecraft\",\n" +
            "          \"マインクラフト ハードコア\",\n" +
            "          \"マインクラフト 実況\",\n" +
            "          \"マイクラ 実況\",\n" +
            "          \"マイクラ 孤島\",\n" +
            "          \"建築\",\n" +
            "          \"家\",\n" +
            "          \"即終了\"\n" +
            "        ],\n" +
            "        \"categoryId\": \"20\",\n" +
            "        \"liveBroadcastContent\": \"none\",\n" +
            "        \"localized\": {\n" +
            "          \"title\": \"【Minecraft】トライデントが欲しいから耐久しよう！774.incマイクラ！【周防パトラ / ハニスト】\",\n" +
            "          \"description\": \"メアリお姉さまとトライデントGETだぜ！\\nI'm working on it in a relaxed manner.\\nI also understand English, so feel free to comment.\\nリスナー様へのお願い\\n基本的にはメンバーが自由に楽しくマインクラフトをプレイできるよう\\nアドバイス等嬉しいですが、ルールを強要するコメントは控えていただけると嬉しいです。\\nなにかトラブルがあってもメンバーで協力、解決するので温かく見守っていただけたらと思います。\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニストコミケグッズ\\n4/24(金)18:00 ~ 5/10(日)23:59 まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation=1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation=1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation=1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation=1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation=1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"liveStreamingDetails\": {\n" +
            "        \"actualStartTime\": \"2020-07-23T07:00:14.295000Z\",\n" +
            "        \"actualEndTime\": \"2020-07-23T09:20:52Z\",\n" +
            "        \"scheduledStartTime\": \"2020-07-23T07:00:00Z\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"kind\": \"youtube#video\",\n" +
            "      \"etag\": \"Y155Sv4k3u_24QK57Y1P4TihXD4\",\n" +
            "      \"id\": \"3JFiJ7lNdnM\",\n" +
            "      \"snippet\": {\n" +
            "        \"publishedAt\": \"2020-07-24T00:33:54Z\",\n" +
            "        \"channelId\": \"UCeLzT-7b2PBcunJplmWtoDg\",\n" +
            "        \"title\": \"【ゴーストオブツシマ】初見プレイ！武士の道から外れ、邪道に落ちた兵冥人となれ#4　GhostofTsushima/PS4【周防パトラ / ハニスト】\",\n" +
            "        \"description\": \"話題のゴーストオブツシマを初見プレイ！ネタバレ禁止でお願いします！\\nI'm going to play an amazingly cool game of Ghosts of Tsushima!\\n\\n公式からあらすじ\\n圧倒的な兵力で日本に襲来したモンゴル軍の前に、たちまち壊滅の窮地に立たされた対馬。\\n武士団の生き残り・境井 仁（さかい じん）は、故郷の土地や住民を守るためモンゴル軍に戦いを挑む。だが、強大な敵との戦いによって、仁は、武士の道から外れ、冥府から蘇った武者「冥人（くろうど）」となってゆくのだった。\\n\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニスト2周年記念グッズ発売中！★\\n7/14(火)22:00 ~ 7/26(日)23:59まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation=1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation=1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation=1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation=1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation=1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\n" +
            "        \"thumbnails\": {\n" +
            "          \"default\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/3JFiJ7lNdnM/default_live.jpg\",\n" +
            "            \"width\": 120,\n" +
            "            \"height\": 90\n" +
            "          },\n" +
            "          \"medium\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/3JFiJ7lNdnM/mqdefault_live.jpg\",\n" +
            "            \"width\": 320,\n" +
            "            \"height\": 180\n" +
            "          },\n" +
            "          \"high\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/3JFiJ7lNdnM/hqdefault_live.jpg\",\n" +
            "            \"width\": 480,\n" +
            "            \"height\": 360\n" +
            "          },\n" +
            "          \"standard\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/3JFiJ7lNdnM/sddefault_live.jpg\",\n" +
            "            \"width\": 640,\n" +
            "            \"height\": 480\n" +
            "          },\n" +
            "          \"maxres\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/3JFiJ7lNdnM/maxresdefault_live.jpg\",\n" +
            "            \"width\": 1280,\n" +
            "            \"height\": 720\n" +
            "          }\n" +
            "        },\n" +
            "        \"channelTitle\": \"Patra Channel / 周防パトラ 【ハニスト】\",\n" +
            "        \"tags\": [\n" +
            "          \"プレイステーション\",\n" +
            "          \"プレステ\",\n" +
            "          \"PlayStation\",\n" +
            "          \"PS\",\n" +
            "          \"Vita\",\n" +
            "          \"PS4\",\n" +
            "          \"Game\",\n" +
            "          \"ゲーム\",\n" +
            "          \"ソニー\",\n" +
            "          \"sony\",\n" +
            "          \"SIE\",\n" +
            "          \"ソニー・インタラクティブエンタテインメント\",\n" +
            "          \"プレステ公式\",\n" +
            "          \"PlayStation4\",\n" +
            "          \"psjapanizGN1LwfH8XME4mP\",\n" +
            "          \"Patra\",\n" +
            "          \"パトラ\",\n" +
            "          \"周防パトラ\",\n" +
            "          \"ハニスト\",\n" +
            "          \"HoneyStrap\",\n" +
            "          \"Vtuber\",\n" +
            "          \"バーチャルYouTuber\",\n" +
            "          \"バーチャルユーチューバー\",\n" +
            "          \"アニメ\",\n" +
            "          \"Anime\",\n" +
            "          \"VR\",\n" +
            "          \"カワボ\",\n" +
            "          \"萌え声\",\n" +
            "          \"アニメ声\",\n" +
            "          \"Gaming\"\n" +
            "        ],\n" +
            "        \"categoryId\": \"20\",\n" +
            "        \"liveBroadcastContent\": \"upcoming\",\n" +
            "        \"localized\": {\n" +
            "          \"title\": \"【ゴーストオブツシマ】初見プレイ！武士の道から外れ、邪道に落ちた兵冥人となれ#4　GhostofTsushima/PS4【周防パトラ / ハニスト】\",\n" +
            "          \"description\": \"話題のゴーストオブツシマを初見プレイ！ネタバレ禁止でお願いします！\\nI'm going to play an amazingly cool game of Ghosts of Tsushima!\\n\\n公式からあらすじ\\n圧倒的な兵力で日本に襲来したモンゴル軍の前に、たちまち壊滅の窮地に立たされた対馬。\\n武士団の生き残り・境井 仁（さかい じん）は、故郷の土地や住民を守るためモンゴル軍に戦いを挑む。だが、強大な敵との戦いによって、仁は、武士の道から外れ、冥府から蘇った武者「冥人（くろうど）」となってゆくのだった。\\n\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニスト2周年記念グッズ発売中！★\\n7/14(火)22:00 ~ 7/26(日)23:59まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation=1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation=1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation=1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation=1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation=1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"liveStreamingDetails\": {\n" +
            "        \"scheduledStartTime\": \"2020-07-24T06:00:00Z\",\n" +
            "        \"activeLiveChatId\": \"Cg0KCzNKRmlKN2xOZG5NKicKGFVDZUx6VC03YjJQQmN1bkpwbG1XdG9EZxILM0pGaUo3bE5kbk0\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"kind\": \"youtube#video\",\n" +
            "      \"etag\": \"fWXI5He9KRT6923smq8nyGwxO3Q\",\n" +
            "      \"id\": \"TNvHb412FsE\",\n" +
            "      \"snippet\": {\n" +
            "        \"publishedAt\": \"2020-07-24T01:09:28Z\",\n" +
            "        \"channelId\": \"UCeLzT-7b2PBcunJplmWtoDg\",\n" +
            "        \"title\": \"【世界のアソビ大全51】竜胆尊VS周防パトラ！手加減なしバトルじゃ！！！【周防パトラ / ハニスト】\",\n" +
            "        \"description\": \"竜胆尊ちゃんと遊ぶよ～\uD83C\uDFB5\\n竜胆尊ちゃん\uD83D\uDC47\\nhttps://www.youtube.com/channel/UCPvGypSgfDkVe7JG2KygK7A/featured\\nTwitter　@RindouMikoto\\n　-----\\n　※この映像は、任天堂のゲーム著作物の利用にあたり、\\n　　収益の全てを投稿者が受け取り、投稿者の所属法人が収益を得ないことで、\\n　　任天堂の個人向けガイドラインの適用を受けています（200004）。\\n　-----\\n\\n\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニスト2周年記念グッズ発売中！★\\n7/14(火)22:00 ~ 7/26(日)23:59まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation=1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation=1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation=1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation=1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation=1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\n" +
            "        \"thumbnails\": {\n" +
            "          \"default\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/TNvHb412FsE/default_live.jpg\",\n" +
            "            \"width\": 120,\n" +
            "            \"height\": 90\n" +
            "          },\n" +
            "          \"medium\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/TNvHb412FsE/mqdefault_live.jpg\",\n" +
            "            \"width\": 320,\n" +
            "            \"height\": 180\n" +
            "          },\n" +
            "          \"high\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/TNvHb412FsE/hqdefault_live.jpg\",\n" +
            "            \"width\": 480,\n" +
            "            \"height\": 360\n" +
            "          },\n" +
            "          \"standard\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/TNvHb412FsE/sddefault_live.jpg\",\n" +
            "            \"width\": 640,\n" +
            "            \"height\": 480\n" +
            "          },\n" +
            "          \"maxres\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/TNvHb412FsE/maxresdefault_live.jpg\",\n" +
            "            \"width\": 1280,\n" +
            "            \"height\": 720\n" +
            "          }\n" +
            "        },\n" +
            "        \"channelTitle\": \"Patra Channel / 周防パトラ 【ハニスト】\",\n" +
            "        \"tags\": [\n" +
            "          \"ゲーム\",\n" +
            "          \"Patra\",\n" +
            "          \"パトラ\",\n" +
            "          \"周防パトラ\",\n" +
            "          \"ハニスト\",\n" +
            "          \"HoneyStrap\",\n" +
            "          \"Vtuber\",\n" +
            "          \"バーチャルYouTuber\",\n" +
            "          \"バーチャルユーチューバー\",\n" +
            "          \"アニメ\",\n" +
            "          \"Anime\",\n" +
            "          \"VR\",\n" +
            "          \"カワボ\",\n" +
            "          \"萌え声\",\n" +
            "          \"アニメ声\",\n" +
            "          \"Gaming\",\n" +
            "          \"実況\",\n" +
            "          \"プレイ\",\n" +
            "          \"女性実況\",\n" +
            "          \"世界のアソビ大全51\",\n" +
            "          \"switch\",\n" +
            "          \"にじさんじ\",\n" +
            "          \"竜胆尊\"\n" +
            "        ],\n" +
            "        \"categoryId\": \"20\",\n" +
            "        \"liveBroadcastContent\": \"upcoming\",\n" +
            "        \"localized\": {\n" +
            "          \"title\": \"【世界のアソビ大全51】竜胆尊VS周防パトラ！手加減なしバトルじゃ！！！【周防パトラ / ハニスト】\",\n" +
            "          \"description\": \"竜胆尊ちゃんと遊ぶよ～\uD83C\uDFB5\\n竜胆尊ちゃん\uD83D\uDC47\\nhttps://www.youtube.com/channel/UCPvGypSgfDkVe7JG2KygK7A/featured\\nTwitter　@RindouMikoto\\n　-----\\n　※この映像は、任天堂のゲーム著作物の利用にあたり、\\n　　収益の全てを投稿者が受け取り、投稿者の所属法人が収益を得ないことで、\\n　　任天堂の個人向けガイドラインの適用を受けています（200004）。\\n　-----\\n\\n\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニスト2周年記念グッズ発売中！★\\n7/14(火)22:00 ~ 7/26(日)23:59まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation=1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation=1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation=1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation=1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation=1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"liveStreamingDetails\": {\n" +
            "        \"scheduledStartTime\": \"2020-07-24T13:00:00Z\",\n" +
            "        \"activeLiveChatId\": \"Cg0KC1ROdkhiNDEyRnNFKicKGFVDZUx6VC03YjJQQmN1bkpwbG1XdG9EZxILVE52SGI0MTJGc0U\"\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"pageInfo\": {\n" +
            "    \"totalResults\": 4,\n" +
            "    \"resultsPerPage\": 4\n" +
            "  }\n" +
            "}\n"
}