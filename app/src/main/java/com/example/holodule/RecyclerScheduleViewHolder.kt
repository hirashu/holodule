package com.example.holodule

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerScheduleViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
    //画面部品の定義
    var tvScheduleTime:TextView
    var tvDistributor:TextView
    var ivImg:ImageView
    var tvBroadcastStatus:TextView
    var tvBroadcastTitle:TextView

    //画面部品に表示に使われるIDを設定する。
    init{
        tvScheduleTime=itemView.findViewById(R.id.tvScheduleTime)
        tvDistributor=itemView.findViewById(R.id.tvDistributor)
        ivImg=itemView.findViewById(R.id.ivImg)
        tvBroadcastStatus=itemView.findViewById(R.id.tvBroadcastStatus)
        tvBroadcastTitle=itemView.findViewById(R.id.tvBroadcastTitle)
    }
}