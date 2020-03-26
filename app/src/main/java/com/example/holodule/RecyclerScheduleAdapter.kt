package com.example.holodule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerScheduleAdapter (private val context:Context,val items:List<YTApiSearchReslt.Item>):RecyclerView.Adapter<RecyclerScheduleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerScheduleViewHolder {
        //インフレータを設定
        val inflater = LayoutInflater.from(context)
        //1行分の画像部品として設定する
        val view = inflater.inflate(R.layout.recycler_schedule_view,parent,false)
        //ビューホルダオブジェクトを作成する
        val holder = RecyclerScheduleViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerScheduleViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        //リストデータの件数をリターン
        return items.size
    }

}