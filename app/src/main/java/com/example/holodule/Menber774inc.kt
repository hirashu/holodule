package com.example.holodule

enum class Menber774inc(val group: Group774Inc, val ChId: String) {
    //774incメンバー（所属,ChId）
    /**あにまーれ*/
    INABA_HANERU(Group774Inc.ANIMARE,"UC0Owc36U9lOyi9Gx9Ic-4qg"),
    SOUYA_ICHIKA(Group774Inc.ANIMARE,"UC2kyQhzGOB-JPgcQX9OMgEw"),
    HINOKUMA_RAN(Group774Inc.ANIMARE,"UCRvpMpzAXBRKJQuk-8-Sdvg"),
    KAZAMI_KUKU(Group774Inc.ANIMARE,"UCXp7sNC0F_qkjickvlYkg-Q"),
    YUNOHARA_IZUMI(Group774Inc.ANIMARE,"UCW8WKciBixmaqaGqrlTITRQ"),
    SHIROMIYA_MIMI(Group774Inc.ANIMARE,"UCtzCQnCT9E4o6U3mHHSHbQQ"),
    HASHIBA_NATSUMI(Group774Inc.ANIMARE,"UC_BlXOQe5OcRC7o0GX8kp8A"),
    /**ハニーストラップ*/
    SUO_PATRA(Group774Inc.HONEY_STRAP,"UCeLzT-7b2PBcunJplmWtoDg"),
    SAIENJI_MARY(Group774Inc.HONEY_STRAP,"UCwePpiw1ocZRSNSkpKvVISw"),
    SEKISHIRO_MICO(Group774Inc.HONEY_STRAP,"UCDh2bWI5EDu7PavqwICkVpA"),
    SHIMAMURA_CHARLOTTE(Group774Inc.HONEY_STRAP,"UCYTz3uIgwVY3ZU-IQJS8r3A"),
    /**シュガーリリック*/
    RYUGASAKI_RENE(Group774Inc.SUGAR_LYRIC,"UC2hc-00y-MSR6eYA4eQ4tjQ"),
    KOJO_ANNA(Group774Inc.SUGAR_LYRIC,"UCvPPBoTOor5gm8zSlE2tg4w"),
    SHISHIO_CHRIS(Group774Inc.SUGAR_LYRIC,"UC--A2dwZW7-M2kID0N6_lfA"),
    /**Vアパ*/
    ANDO_YUGE(Group774Inc.V_APA,"UC3EhsuKdEkI99TWZwZgWutg"),
    KISAKI_ANKO(Group774Inc.V_APA,"UChXm-xAYPfygrbyLo2yCASQ"),
    CAMOMI_CAMOMI(Group774Inc.V_APA,"UCL-2thbJ7grC9fmGF4OLuTg"),
    KANADE_KANON(Group774Inc.V_APA,"UCmqrvfLMws-GLGHQcB5dasg"),
    KOMORI_METO(Group774Inc.V_APA,"UCzUNASdzI4PV5SlqtYwAkKQ"),
    FUMA_MAT(Group774Inc.V_APA,"UCV4EoK6BVNl7wxuxpUvvSWA");

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
        return Menber774inc.values().map { it.ChId }
    }

    return Menber774inc.values().filter { group==it.group }.map { it.ChId }
    /** 以下と同じ処理のはず
    val chanelIdList= mutableListOf<String>()
    val temp =Menber774inc.values().filter { group==it.group }
    temp.forEach { chanelIdList.add(it.ChId) }
    return chanelIdList
    */
}

