package com.example.holodule

import java.text.ParseException
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

    /** RFC3339形式のdate-time値をローカル時間に変換する*/
    @Synchronized
    @Throws(ParseException::class, IndexOutOfBoundsException::class)
    fun parseRFC3339Date(dateString: String): Date? {
        var dateString = dateString
        var d: Date

        //if there is no time zone, we don't need to do any special parsing.
        if (dateString.endsWith("Z")) {
            try {
                val s = SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss'Z'",
                    Locale.getDefault()
                ) //spec for RFC3339 with a 'Z'
                s.timeZone = TimeZone.getTimeZone("UTC")
                d = s.parse(dateString)
            } catch (pe: ParseException) { //try again with optional decimals
                val s = SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
                    Locale.getDefault()
                ) //spec for RFC3339 with a 'Z' and fractional seconds
                s.timeZone = TimeZone.getTimeZone("UTC")
                s.isLenient = true
                d = s.parse(dateString)
            }
            return d
        }

        //step one, split off the timezone.
        val firstPart: String
        var secondPart: String
        if (dateString.lastIndexOf('+') == -1) {
            firstPart = dateString.substring(0, dateString.lastIndexOf('-'))
            secondPart = dateString.substring(dateString.lastIndexOf('-'))
        } else {
            firstPart = dateString.substring(0, dateString.lastIndexOf('+'))
            secondPart = dateString.substring(dateString.lastIndexOf('+'))
        }

        //step two, remove the colon from the timezone offset
        secondPart = secondPart.substring(
            0,
            secondPart.indexOf(':')
        ) + secondPart.substring(secondPart.indexOf(':') + 1)
        dateString = firstPart + secondPart
        var s = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssZ",
            Locale.getDefault()
        ) //spec for RFC3339
        try {
            d = s.parse(dateString)
        } catch (pe: ParseException) { //try again with optional decimals
            s = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ",
                Locale.getDefault()
            ) //spec for RFC3339 (with fractional seconds)
            s.isLenient = true
            d = s.parse(dateString)
        }
        return d
    }

    /**DateTimeをHH:MM形式に変換する*/
    fun formHmmDate(date: Date?):String{
        date?:return ""
        val sdf = SimpleDateFormat("H:mm")
        return sdf.format(date)
    }

    fun formHmmDate(dateTime: Long?):String{
        dateTime?:return ""
        val date= Date()
        date.time=dateTime
        val sdf = SimpleDateFormat("H:mm")
        return sdf.format(date)
    }
}