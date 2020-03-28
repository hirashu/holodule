package com.example.holodule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerScheduleAdapter (private val context:Context?, private val items:List<YTApiSearchResult.Item>):RecyclerView.Adapter<RecyclerScheduleViewHolder>(){
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
        // TODO 値を格納する
        val item = items[position]
        item?.snippet?.let {
            val scheduleTime ="YYYY-MM-DD:hh,mm"
            var distributor ="湊あくあ（仮）"
            var img=it.thumbnails?.medium?.url
            var broadcastStatus="配信中（仮）"
            var broadcastTitle=it.title

            // TODO Viewholderに値を設定する
            holder.tvScheduleTime.text=scheduleTime
            holder.tvDistributor.text=distributor
            holder.tvImg.text=img
            holder.tvBroadcastStatus.text=broadcastStatus
            holder.tvBroadcastTitle.text=broadcastTitle
        }
    }

    override fun getItemCount(): Int {
        //リストデータの件数をリターン
        return items.size
    }

}