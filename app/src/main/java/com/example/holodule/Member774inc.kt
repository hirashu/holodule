package com.example.holodule

enum class Member774inc(val group: Group774Inc,val castName:String, val chId: String) {
    //774incメンバー（所属,ChId）
    /**あにまーれ*/
    INABA_HANERU(Group774Inc.ANIMARE,"因幡はねる","UC0Owc36U9lOyi9Gx9Ic-4qg"),
    SOUYA_ICHIKA(Group774Inc.ANIMARE,"宗谷いちか","UC2kyQhzGOB-JPgcQX9OMgEw"),
    HINOKUMA_RAN(Group774Inc.ANIMARE,"日ノ隈らん","UCRvpMpzAXBRKJQuk-8-Sdvg"),
    KAZAMI_KUKU(Group774Inc.ANIMARE,"風見くく","UCXp7sNC0F_qkjickvlYkg-Q"),
    YUNOHARA_IZUMI(Group774Inc.ANIMARE,"柚原いづみ","UCW8WKciBixmaqaGqrlTITRQ"),
    SHIROMIYA_MIMI(Group774Inc.ANIMARE,"白宮みみ","UCtzCQnCT9E4o6U3mHHSHbQQ"),
    HASHIBA_NATSUMI(Group774Inc.ANIMARE,"羽柴なつみ","UC_BlXOQe5OcRC7o0GX8kp8A"),
    /**ハニーストラップ*/
    SUO_PATRA(Group774Inc.HONEY_STRAP,"周防パトラ","UCeLzT-7b2PBcunJplmWtoDg"),
    SAIENJI_MARY(Group774Inc.HONEY_STRAP,"西園寺メアリ","UCwePpiw1ocZRSNSkpKvVISw"),
    SEKISHIRO_MICO(Group774Inc.HONEY_STRAP,"堰代ミコ","UCDh2bWI5EDu7PavqwICkVpA"),
    SHIMAMURA_CHARLOTTE(Group774Inc.HONEY_STRAP,"島村シャルロット","UCYTz3uIgwVY3ZU-IQJS8r3A"),
    /**シュガーリリック*/
    RYUGASAKI_RENE(Group774Inc.SUGAR_LYRIC,"龍ヶ崎リン","UC2hc-00y-MSR6eYA4eQ4tjQ"),
    KOJO_ANNA(Group774Inc.SUGAR_LYRIC,"虎城アンナ","UCvPPBoTOor5gm8zSlE2tg4w"),
    SHISHIO_CHRIS(Group774Inc.SUGAR_LYRIC,"獅子王クリス","UC--A2dwZW7-M2kID0N6_lfA"),
    /**Vアパ*/
    ANDO_YUGE(Group774Inc.V_APA,"杏戸ゆげ","UC3EhsuKdEkI99TWZwZgWutg"),
    KISAKI_ANKO(Group774Inc.V_APA,"季咲あんこ","UChXm-xAYPfygrbyLo2yCASQ"),
    KANADE_KANON(Group774Inc.V_APA,"花奏かのん","UCmqrvfLMws-GLGHQcB5dasg"),
    KOMORI_METO(Group774Inc.V_APA,"小森めと","UCzUNASdzI4PV5SlqtYwAkKQ"),
    FUMA_MAT(Group774Inc.V_APA,"不磨わっと","UCV4EoK6BVNl7wxuxpUvvSWA");

}

enum class Group774Inc(val groupId: Int) {
    ALL(0),
    ANIMARE(1),
    HONEY_STRAP(2),
    SUGAR_LYRIC(3),
    V_APA(4)
}

/**メンバーのchIDを返す*/
fun groupChannelIdList(group: Group774Inc):List<String>{
    if(group==Group774Inc.ALL){
        return Member774inc.values().map { it.chId }
    }
    return Member774inc.values().filter { group==it.group }.map { it.chId }
}

/**ChannelIdからメンバー名を所得する*/
fun memberName(chanelId: String?):String{
    return Member774inc.values().firstOrNull{it.chId==chanelId}?.castName ?:""
}
