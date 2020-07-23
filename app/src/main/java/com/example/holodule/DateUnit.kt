package com.example.holodule

import java.text.SimpleDateFormat
import java.util.*

class DateUnit{

    /**今の時間を取得する*/
    fun now() :Date= Date()

    /**今日からX日後（0時）の時間を取得する
     *
     * @param addDay X日後
     * */
    private fun todayAfDay(addDay:Int):Date{
        val cal =Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH,addDay)
        cal.set(Calendar.HOUR_OF_DAY,0)
        cal.set(Calendar.MINUTE,0)
        cal.set(Calendar.SECOND,0)
        cal.set(Calendar.MILLISECOND,0)
        return cal.time
    }

    /**今日(0時)の時間を取得する*/
    fun today():Date{
        return todayAfDay(0)
    }

    /**明日（0時）の時間を取得する*/
    fun tomorrow():Date{
        return todayAfDay(1)
    }

    /**昨日（0時）の時間を取得する*/
    fun yesterday():Date{
        return todayAfDay(-1)
    }

    /** RFC3339形式のdate-time値を取得する（1970-01-01T00:00:00Z）*/
    fun formRfc3339Date(date: Date):String{
        val sdf = SimpleDateFormat("yyyy-MM-dd kk:mm:ss")
        return sdf.format(date)
    }

}