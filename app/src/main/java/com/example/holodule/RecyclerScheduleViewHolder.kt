package com.example.holodule

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerScheduleViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
    //画面部品の定義
    var tvScheduleTime:TextView

    //画面部品に表示に使われるIDを設定する。
    init{
        tvScheduleTime=itemView.findViewById(R.id.tvScheduleTime)
    }
}