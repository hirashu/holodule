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
            youtube.Videos().list("liveStreamingDetails,snippet")
        videoSearch.key = apiKey
        videoSearch.id=videoIdList.joinToString(separator=",")
        var gson = Gson()
        return try {
            /**TODO APIで実装を行う。*/
            if (BuildMode.MODE.testMode) {
                val jsonTest = testAPI
                val retJson = gson.fromJson(jsonTest, YTApiVideo::class.java)
                retJson.items?.sortedBy { it.liveStreamingDetails?.scheduledStartTime?.value}
            } else {
                val result = videoSearch.execute()
                val jsonTest = gson.toJson(result)
                val retJson = gson.fromJson(jsonTest, YTApiVideo::class.java)
                retJson.items?.sortedBy { it.liveStreamingDetails?.scheduledStartTime?.value}
            }
        } catch (ex: Exception) {
            null
        }
    }

    override fun onPostExecute(result: List<YTApiVideo.Item>?) {
        //ここで更新処理をおこなう。
    }

    //test用API返却値
    private val testAPI = "{\"etag\":\"OEDGT4pKz5SZz_BK173reHiD4o8\",\"items\":[{\"etag\":\"AHL1ZyOIl_FG9oOjtuyVuTHlDGU\",\"id\":\"3JFiJ7lNdnM\",\"kind\":\"youtube#video\",\"liveStreamingDetails\":{\"actualEndTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595581076000},\"actualStartTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595570428158},\"scheduledStartTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595570400000}},\"snippet\":{\"categoryId\":\"20\",\"channelId\":\"UCeLzT-7b2PBcunJplmWtoDg\",\"channelTitle\":\"Patra Channel / 周防パトラ 【ハニスト】\",\"description\":\"話題のゴーストオブツシマを初見プレイ！ネタバレ禁止でお願いします！\\nI\\u0027m going to play an amazingly cool game of Ghosts of Tsushima!\\n\\n公式からあらすじ\\n圧倒的な兵力で日本に襲来したモンゴル軍の前に、たちまち壊滅の窮地に立たされた対馬。\\n武士団の生き残り・境井 仁（さかい じん）は、故郷の土地や住民を守るためモンゴル軍に戦いを挑む。だが、強大な敵との戦いによって、仁は、武士の道から外れ、冥府から蘇った武者「冥人（くろうど）」となってゆくのだった。\\n\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニスト2周年記念グッズ発売中！★\\n7/14(火)22:00 ~ 7/26(日)23:59まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation\\u003d1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation\\u003d1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation\\u003d1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation\\u003d1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation\\u003d1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\"liveBroadcastContent\":\"none\",\"localized\":{\"description\":\"話題のゴーストオブツシマを初見プレイ！ネタバレ禁止でお願いします！\\nI\\u0027m going to play an amazingly cool game of Ghosts of Tsushima!\\n\\n公式からあらすじ\\n圧倒的な兵力で日本に襲来したモンゴル軍の前に、たちまち壊滅の窮地に立たされた対馬。\\n武士団の生き残り・境井 仁（さかい じん）は、故郷の土地や住民を守るためモンゴル軍に戦いを挑む。だが、強大な敵との戦いによって、仁は、武士の道から外れ、冥府から蘇った武者「冥人（くろうど）」となってゆくのだった。\\n\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニスト2周年記念グッズ発売中！★\\n7/14(火)22:00 ~ 7/26(日)23:59まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation\\u003d1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation\\u003d1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation\\u003d1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation\\u003d1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation\\u003d1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\"title\":\"【ゴーストオブツシマ】初見プレイ！武士の道から外れ、邪道に落ちた兵冥人となれ#4　GhostofTsushima/PS4【周防パトラ / ハニスト】\"},\"publishedAt\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595582258000},\"tags\":[\"プレイステーション\",\"プレステ\",\"PlayStation\",\"PS\",\"Vita\",\"PS4\",\"Game\",\"ゲーム\",\"ソニー\",\"sony\",\"SIE\",\"ソニー・インタラクティブエンタテインメント\",\"プレステ公式\",\"PlayStation4\",\"psjapanizGN1LwfH8XME4mP\",\"Patra\",\"パトラ\",\"周防パトラ\",\"ハニスト\",\"HoneyStrap\",\"Vtuber\",\"バーチャルYouTuber\",\"バーチャルユーチューバー\",\"アニメ\",\"Anime\",\"VR\",\"カワボ\",\"萌え声\",\"アニメ声\",\"Gaming\"],\"thumbnails\":{\"default\":{\"height\":90,\"url\":\"https://i.ytimg.com/vi/3JFiJ7lNdnM/default.jpg\",\"width\":120},\"high\":{\"height\":360,\"url\":\"https://i.ytimg.com/vi/3JFiJ7lNdnM/hqdefault.jpg\",\"width\":480},\"maxres\":{\"height\":720,\"url\":\"https://i.ytimg.com/vi/3JFiJ7lNdnM/maxresdefault.jpg\",\"width\":1280},\"medium\":{\"height\":180,\"url\":\"https://i.ytimg.com/vi/3JFiJ7lNdnM/mqdefault.jpg\",\"width\":320},\"standard\":{\"height\":480,\"url\":\"https://i.ytimg.com/vi/3JFiJ7lNdnM/sddefault.jpg\",\"width\":640}},\"title\":\"【ゴーストオブツシマ】初見プレイ！武士の道から外れ、邪道に落ちた兵冥人となれ#4　GhostofTsushima/PS4【周防パトラ / ハニスト】\"}},{\"etag\":\"Dn1V35fzU9X_vYRMmxSCxYbL6gM\",\"id\":\"TNvHb412FsE\",\"kind\":\"youtube#video\",\"liveStreamingDetails\":{\"actualEndTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595600479000},\"actualStartTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595595615055},\"scheduledStartTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595595600000}},\"snippet\":{\"categoryId\":\"20\",\"channelId\":\"UCeLzT-7b2PBcunJplmWtoDg\",\"channelTitle\":\"Patra Channel / 周防パトラ 【ハニスト】\",\"description\":\"竜胆尊ちゃんと遊ぶよ～\uD83C\uDFB5\\n竜胆尊ちゃん\uD83D\uDC47\\nhttps://www.youtube.com/channel/UCPvGypSgfDkVe7JG2KygK7A/featured\\nTwitter　@RindouMikoto\\n　-----\\n　※この映像は、任天堂のゲーム著作物の利用にあたり、\\n　　収益の全てを投稿者が受け取り、投稿者の所属法人が収益を得ないことで、\\n　　任天堂の個人向けガイドラインの適用を受けています（200004）。\\n　-----\\n\\n\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニスト2周年記念グッズ発売中！★\\n7/14(火)22:00 ~ 7/26(日)23:59まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation\\u003d1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation\\u003d1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation\\u003d1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation\\u003d1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation\\u003d1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\"liveBroadcastContent\":\"none\",\"localized\":{\"description\":\"竜胆尊ちゃんと遊ぶよ～\uD83C\uDFB5\\n竜胆尊ちゃん\uD83D\uDC47\\nhttps://www.youtube.com/channel/UCPvGypSgfDkVe7JG2KygK7A/featured\\nTwitter　@RindouMikoto\\n　-----\\n　※この映像は、任天堂のゲーム著作物の利用にあたり、\\n　　収益の全てを投稿者が受け取り、投稿者の所属法人が収益を得ないことで、\\n　　任天堂の個人向けガイドラインの適用を受けています（200004）。\\n　-----\\n\\n\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニスト2周年記念グッズ発売中！★\\n7/14(火)22:00 ~ 7/26(日)23:59まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation\\u003d1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation\\u003d1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation\\u003d1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation\\u003d1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation\\u003d1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\"title\":\"【世界のアソビ大全51】竜胆尊VS周防パトラ！手加減なしバトルじゃ！！！【周防パトラ / ハニスト】\"},\"publishedAt\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595600932000},\"tags\":[\"ゲーム\",\"Patra\",\"パトラ\",\"周防パトラ\",\"ハニスト\",\"HoneyStrap\",\"Vtuber\",\"バーチャルYouTuber\",\"バーチャルユーチューバー\",\"アニメ\",\"Anime\",\"VR\",\"カワボ\",\"萌え声\",\"アニメ声\",\"Gaming\",\"実況\",\"プレイ\",\"女性実況\",\"世界のアソビ大全51\",\"switch\",\"にじさんじ\",\"竜胆尊\"],\"thumbnails\":{\"default\":{\"height\":90,\"url\":\"https://i.ytimg.com/vi/TNvHb412FsE/default.jpg\",\"width\":120},\"high\":{\"height\":360,\"url\":\"https://i.ytimg.com/vi/TNvHb412FsE/hqdefault.jpg\",\"width\":480},\"maxres\":{\"height\":720,\"url\":\"https://i.ytimg.com/vi/TNvHb412FsE/maxresdefault.jpg\",\"width\":1280},\"medium\":{\"height\":180,\"url\":\"https://i.ytimg.com/vi/TNvHb412FsE/mqdefault.jpg\",\"width\":320},\"standard\":{\"height\":480,\"url\":\"https://i.ytimg.com/vi/TNvHb412FsE/sddefault.jpg\",\"width\":640}},\"title\":\"【世界のアソビ大全51】竜胆尊VS周防パトラ！手加減なしバトルじゃ！！！【周防パトラ / ハニスト】\"}},{\"etag\":\"ung51YywZHKQFxgnlduCTy5qIi0\",\"id\":\"8W8uUtAo_Rg\",\"kind\":\"youtube#video\",\"liveStreamingDetails\":{\"actualEndTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595642876000},\"actualStartTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595635250562},\"scheduledStartTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595635200000}},\"snippet\":{\"categoryId\":\"20\",\"channelId\":\"UCeLzT-7b2PBcunJplmWtoDg\",\"channelTitle\":\"Patra Channel / 周防パトラ 【ハニスト】\",\"description\":\"朝マイクラは作業するぞ！\\nI\\u0027m working on it in a relaxed manner.\\nI also understand English, so feel free to comment.\\nリスナー様へのお願い\\n基本的にはメンバーが自由に楽しくマインクラフトをプレイできるよう\\nアドバイス等嬉しいですが、ルールを強要するコメントは控えていただけると嬉しいです。\\nなにかトラブルがあってもメンバーで協力、解決するので温かく見守っていただけたらと思います。\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニストコミケグッズ\\n4/24(金)18:00 ~ 5/10(日)23:59 まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation\\u003d1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation\\u003d1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation\\u003d1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation\\u003d1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation\\u003d1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\"liveBroadcastContent\":\"none\",\"localized\":{\"description\":\"朝マイクラは作業するぞ！\\nI\\u0027m working on it in a relaxed manner.\\nI also understand English, so feel free to comment.\\nリスナー様へのお願い\\n基本的にはメンバーが自由に楽しくマインクラフトをプレイできるよう\\nアドバイス等嬉しいですが、ルールを強要するコメントは控えていただけると嬉しいです。\\nなにかトラブルがあってもメンバーで協力、解決するので温かく見守っていただけたらと思います。\\n最新曲アップ！一目惚れパラドックス\\nhttps://youtu.be/uC3ORIZfxdE\\n\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#パトライブ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニストコミケグッズ\\n4/24(金)18:00 ~ 5/10(日)23:59 まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation\\u003d1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation\\u003d1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation\\u003d1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation\\u003d1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation\\u003d1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\"title\":\"【Minecraft】朝は色々な作業をまったりしよう！774.incマイクラ！I\\u0027m going to work in peace.【周防パトラ / ハニスト】\"},\"publishedAt\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595643775000},\"tags\":[\"Patra\",\"パトラ\",\"周防パトラ\",\"ハニスト\",\"HoneyStrap\",\"Vtuber\",\"バーチャルYouTuber\",\"バーチャルユーチューバー\",\"アニメ\",\"Anime\",\"VR\",\"カワボ\",\"萌え声\",\"アニメ声\",\"Gaming\",\"マイクラ\",\"Minecraft\",\"マインクラフト ハードコア\",\"マインクラフト 実況\",\"マイクラ 実況\",\"マイクラ 孤島\",\"建築\",\"家\",\"即終了\"],\"thumbnails\":{\"default\":{\"height\":90,\"url\":\"https://i.ytimg.com/vi/8W8uUtAo_Rg/default.jpg\",\"width\":120},\"high\":{\"height\":360,\"url\":\"https://i.ytimg.com/vi/8W8uUtAo_Rg/hqdefault.jpg\",\"width\":480},\"maxres\":{\"height\":720,\"url\":\"https://i.ytimg.com/vi/8W8uUtAo_Rg/maxresdefault.jpg\",\"width\":1280},\"medium\":{\"height\":180,\"url\":\"https://i.ytimg.com/vi/8W8uUtAo_Rg/mqdefault.jpg\",\"width\":320},\"standard\":{\"height\":480,\"url\":\"https://i.ytimg.com/vi/8W8uUtAo_Rg/sddefault.jpg\",\"width\":640}},\"title\":\"【Minecraft】朝は色々な作業をまったりしよう！774.incマイクラ！I\\u0027m going to work in peace.【周防パトラ / ハニスト】\"}},{\"etag\":\"66ll03XCBbGwc2Xsh8n2VfB_yRc\",\"id\":\"gHEe1iFsogs\",\"kind\":\"youtube#video\",\"liveStreamingDetails\":{\"actualEndTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595663327000},\"actualStartTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595656849079},\"scheduledStartTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595656800000}},\"snippet\":{\"categoryId\":\"20\",\"channelId\":\"UCeLzT-7b2PBcunJplmWtoDg\",\"channelTitle\":\"Patra Channel / 周防パトラ 【ハニスト】\",\"description\":\"PS4バイオハザード5初見プレイ！ネタバレは聞いたら教えてほしいです！\\nへっぽこメアパト\\nI understand English, so please feel free to comment!\\nバイオハザードマイリスト\\nhttps://www.youtube.com/playlist?list\\u003dPL1d2KuGf9SKaA6P804s-nwNCMbgY1Juaf\\nバイオハザードＲＥ:2\\nhttps://www.youtube.com/playlist?list\\u003dPL1d2KuGf9SKYmLehlzQLKer1L85EEdXB6\\n\\n\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#メアパトコラボ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニストコミケグッズ\\n4/24(金)18:00 ~ 5/10(日)23:59 まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation\\u003d1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation\\u003d1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation\\u003d1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation\\u003d1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation\\u003d1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\"liveBroadcastContent\":\"none\",\"localized\":{\"description\":\"PS4バイオハザード5初見プレイ！ネタバレは聞いたら教えてほしいです！\\nへっぽこメアパト\\nI understand English, so please feel free to comment!\\nバイオハザードマイリスト\\nhttps://www.youtube.com/playlist?list\\u003dPL1d2KuGf9SKaA6P804s-nwNCMbgY1Juaf\\nバイオハザードＲＥ:2\\nhttps://www.youtube.com/playlist?list\\u003dPL1d2KuGf9SKYmLehlzQLKer1L85EEdXB6\\n\\n\\n撮れ高あったら動画にしてTwitterやニコニコなどに載せてくれると嬉しいです！\\n動画タグ→＃パトラを見て　感想→　#メアパトコラボ\\nNEW\uD83E\uDD80パトラボイス販売中！\uD83E\uDD80\\nhttps://774inc.spwn.jp/events/200427-patra\\n★周防パトラTwitter★フォローすると配信予定などすぐわかる！\\nhttps://twitter.com/Patra_HNST\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\nこちらから登録していただくと名前の後ろにかにかまが増えます♪\\nささやかですが進化していくよ♪登録してもらえると嬉しいです！\\n●SuperChatについて●\\n活動資金になっております！本当にありがとうございます！\\n配信中のため全部のSuperChatに反応することが難しくなっていますが\\n後からどなたからメッセージをいただいたかきちんと確認しています！！！\\n応援の気持ちとっても嬉しいです！\\n\\n配信を楽しくするために以下のお願いをしております。\\n★他のVtuberさんへの暴言はやめてください。\\n********************************************************\\n★ハニストコミケグッズ\\n4/24(金)18:00 ~ 5/10(日)23:59 まで\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n★ハニストLINEスタンプはこちら！\\nhttps://www.774.ai/line-stamp\\n\\n★ハニストライブイベント映像など絶賛配信中！\\nhttps://www.774.ai/live-movie\\n＊ 初ソロイベント「PatLive」も配信中だよ！\\n\\n★ サプライズボックス \\nhttps://surprisebox.jp/lineup/honeystrap\\nhttps://twitter.com/_surprisebox_/status/1234333066732003328\\n\\n*********************************************************\\n★オリジナル楽曲配信中★\\n「シュガーホリック」　https://youtu.be/ApgNam7rFMU\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n星降るメモリー/HoneyStrap\\nhttp://linkco.re/V9AFRVFR \\nHoney Daze / HoneyStrap\\nhttps://linkco.re/g7Uu3q1d\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★公式グッズ販売ページ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation\\u003d1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation\\u003d1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation\\u003d1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation\\u003d1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation\\u003d1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\"title\":\"【バイオハザード5】今度こそ生き残れ！愛と絆？！初見プレイでがんばるぞ！#4【周防パトラ / ハニスト】\"},\"publishedAt\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595664012000},\"tags\":[\"PS4\",\"ゲーム\",\"PlayStation4\",\"CAPCOM\",\"カプコン\",\"BIOHAZARD\",\"バイオハザード\",\"ラクーンシティ\",\"ゾンビ\",\"Patra\",\"パトラ\",\"周防パトラ\",\"ハニスト\",\"HoneyStrap\",\"Vtuber\",\"バーチャルYouTuber\",\"バーチャルユーチューバー\",\"アニメ\",\"Anime\",\"VR\",\"カワボ\",\"萌え声\",\"アニメ声\",\"Gaming\",\"TPS\"],\"thumbnails\":{\"default\":{\"height\":90,\"url\":\"https://i.ytimg.com/vi/gHEe1iFsogs/default.jpg\",\"width\":120},\"high\":{\"height\":360,\"url\":\"https://i.ytimg.com/vi/gHEe1iFsogs/hqdefault.jpg\",\"width\":480},\"maxres\":{\"height\":720,\"url\":\"https://i.ytimg.com/vi/gHEe1iFsogs/maxresdefault.jpg\",\"width\":1280},\"medium\":{\"height\":180,\"url\":\"https://i.ytimg.com/vi/gHEe1iFsogs/mqdefault.jpg\",\"width\":320},\"standard\":{\"height\":480,\"url\":\"https://i.ytimg.com/vi/gHEe1iFsogs/sddefault.jpg\",\"width\":640}},\"title\":\"【バイオハザード5】今度こそ生き残れ！愛と絆？！初見プレイでがんばるぞ！#4【周防パトラ / ハニスト】\"}},{\"etag\":\"VAJd_sj_3sr29xnifFwvkzN0p7w\",\"id\":\"_W-4P1jrHfI\",\"kind\":\"youtube#video\",\"liveStreamingDetails\":{\"actualEndTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595675131000},\"actualStartTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595674813000},\"scheduledStartTime\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595674800000}},\"snippet\":{\"categoryId\":\"24\",\"channelId\":\"UCeLzT-7b2PBcunJplmWtoDg\",\"channelTitle\":\"Patra Channel / 周防パトラ 【ハニスト】\",\"defaultAudioLanguage\":\"ja\",\"description\":\"1stアルバムスペシャルMVの１つぶいちゅっばの歌です。\\nぶいちゅっばを好きな人すべてにきいてほしいです。\\n\\n◆周防パトラ1stアルバム「あいあむなんばーわん！」\\n2020年8月1日(土)発売開始！\\nスペシャルMVが収録されたBlu-ray付き特別盤などを予定しております！\\n*詳細は後日お知らせいたします。\\n\\n◆周防パトラ2ndソロライブ「PatLive2 ～ CYBER LOVE あなたとつながりたい ～」\\n2020年8月29日(土) 開催決定！\\n周防パトラのインターネットライブが再び開催されます！！\\n詳細はこちら：https://spwn.jp/events/200829-patra\\n\\n＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\\n「ぶいちゅっばの歌」\\n作詞/作曲/編曲/歌：周防パトラ（HoneyStrap）\\n\\n企画製作：774inc.\\n映像ディレクター：深山 詠美（Balus) @fukayama_emi\\nCGアーティスト：柳 淳哉(Balus)\\n＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\\n\\n★周防パトラTwitter★\\nhttps://twitter.com/Patra_HNST\\n\\n★オリジナル楽曲配信中★\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\n\\n＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★オリジナルソング / ボイス / LINEスタンプ / Live Movie 配信中★\\nhttps://www.774.ai/music\\n\\n★公式グッズ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★MMD配布★\\nhttps://www.774.ai/special\\n\\n★サプライズボックスに参加してます★\\nhttps://surprisebox.jp/lineup/honeystrap\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation\\u003d1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation\\u003d1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation\\u003d1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation\\u003d1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation\\u003d1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\"liveBroadcastContent\":\"none\",\"localized\":{\"description\":\"1stアルバムスペシャルMVの１つぶいちゅっばの歌です。\\nぶいちゅっばを好きな人すべてにきいてほしいです。\\n\\n◆周防パトラ1stアルバム「あいあむなんばーわん！」\\n2020年8月1日(土)発売開始！\\nスペシャルMVが収録されたBlu-ray付き特別盤などを予定しております！\\n*詳細は後日お知らせいたします。\\n\\n◆周防パトラ2ndソロライブ「PatLive2 ～ CYBER LOVE あなたとつながりたい ～」\\n2020年8月29日(土) 開催決定！\\n周防パトラのインターネットライブが再び開催されます！！\\n詳細はこちら：https://spwn.jp/events/200829-patra\\n\\n＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\\n「ぶいちゅっばの歌」\\n作詞/作曲/編曲/歌：周防パトラ（HoneyStrap）\\n\\n企画製作：774inc.\\n映像ディレクター：深山 詠美（Balus) @fukayama_emi\\nCGアーティスト：柳 淳哉(Balus)\\n＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\\n\\n★周防パトラTwitter★\\nhttps://twitter.com/Patra_HNST\\n\\n★オリジナル楽曲配信中★\\nぶいちゅっばの歌 / 周防パトラ\\nhttps://linkco.re/ZbrvGbnR\\nシュガーホリック / 周防パトラ\\nhttps://linkco.re/xDg51fXe\\n\\n♥★メンバーシップ登録はこちらから★♥\\nhttps://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg/join\\n\\n＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊\\n\\n★公式ホームページ★\\nhttps://www.774.ai/\\n\\n★オリジナルソング / ボイス / LINEスタンプ / Live Movie 配信中★\\nhttps://www.774.ai/music\\n\\n★公式グッズ★\\nhttps://774inc.spwn.jp/events/HoneyStrap/goods\\n\\n★MMD配布★\\nhttps://www.774.ai/special\\n\\n★サプライズボックスに参加してます★\\nhttps://surprisebox.jp/lineup/honeystrap\\n\\n★ハニーストラップのメンバー★\\n周防パトラ// https://www.youtube.com/channel/UCeLzT-7b2PBcunJplmWtoDg?sub_confirmation\\u003d1\\n島村シャルロット// https://www.youtube.com/channel/UCYTz3uIgwVY3ZU-IQJS8r3A?sub_confirmation\\u003d1\\n西園寺メアリ// https://www.youtube.com/channel/UCwePpiw1ocZRSNSkpKvVISw?sub_confirmation\\u003d1\\n堰代ミコ// https://www.youtube.com/channel/UCDh2bWI5EDu7PavqwICkVpA?sub_confirmation\\u003d1\\nハニスト公式動画チャンネル// https://www.youtube.com/channel/UCgqQ5iuvUyPRHp3rBLuOtCw?sub_confirmation\\u003d1\\n\\n★ハニスト公式Twitter★\\nhttps://twitter.com/HNST_official\",\"title\":\"【オリジナル曲】ぶいちゅっばの歌 / 1stアルバムスペシャルMV【周防パトラ / ハニスト】\"},\"publishedAt\":{\"dateOnly\":false,\"tzShift\":0,\"value\":1595674813000},\"tags\":[\"Patra\",\"パトラ\",\"周防パトラ\",\"ハニスト\",\"HoneyStrap\",\"Vtuber\",\"バーチャルYouTuber\",\"バーチャルユーチューバー\",\"アニメ\",\"Anime\",\"VR\",\"カワボ\",\"萌え声\",\"アニメ声\",\"Gaming\"],\"thumbnails\":{\"default\":{\"height\":90,\"url\":\"https://i.ytimg.com/vi/_W-4P1jrHfI/default.jpg\",\"width\":120},\"high\":{\"height\":360,\"url\":\"https://i.ytimg.com/vi/_W-4P1jrHfI/hqdefault.jpg\",\"width\":480},\"maxres\":{\"height\":720,\"url\":\"https://i.ytimg.com/vi/_W-4P1jrHfI/maxresdefault.jpg\",\"width\":1280},\"medium\":{\"height\":180,\"url\":\"https://i.ytimg.com/vi/_W-4P1jrHfI/mqdefault.jpg\",\"width\":320},\"standard\":{\"height\":480,\"url\":\"https://i.ytimg.com/vi/_W-4P1jrHfI/sddefault.jpg\",\"width\":640}},\"title\":\"【オリジナル曲】ぶいちゅっばの歌 / 1stアルバムスペシャルMV【周防パトラ / ハニスト】\"}}],\"kind\":\"youtube#videoListResponse\",\"pageInfo\":{\"resultsPerPage\":5,\"totalResults\":5}}"
}