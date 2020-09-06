package com.example.holodule

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerScheduleViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
    //画面部品の定義
    var tvScheduleTime:TextView = itemView.findViewById(R.id.tvScheduleTime)
    var tvDistributor:TextView = itemView.findViewById(R.id.tvDistributor)
    var ivImg:ImageView = itemView.findViewById(R.id.ivImg)
    var tvBroadcastStatus:TextView = itemView.findViewById(R.id.tvBroadcastStatus)
    var tvBroadcastTitle:TextView = itemView.findViewById(R.id.tvBroadcastTitle)
}