package com.example.holodule

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerScheduleViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
    //画面部品の定義
    var tvScheduleTime:TextView
    var tvDistributor:TextView
    var tvImg:TextView
    var tvBroadcastStatus:TextView
    var tvBroadcastTitle:TextView

    //画面部品に表示に使われるIDを設定する。
    init{
        tvScheduleTime=itemView.findViewById(R.id.tvScheduleTime)
        tvDistributor=itemView.findViewById(R.id.tvDistributor)
        tvImg=itemView.findViewById(R.id.tvImg)
        tvBroadcastStatus=itemView.findViewById(R.id.tvBroadcastStatus)
        tvBroadcastTitle=itemView.findViewById(R.id.tvBroadcastTitle)
    }
}