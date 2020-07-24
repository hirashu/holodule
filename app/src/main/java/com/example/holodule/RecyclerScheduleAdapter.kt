package com.example.holodule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerScheduleAdapter (private val context:Context?, private val items:List<YTApiVideo.Item>):RecyclerView.Adapter<RecyclerScheduleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerScheduleViewHolder {
        //インフレータを設定
        val inflater = LayoutInflater.from(context)
        //1行分の画像部品として設定する
        val view = inflater.inflate(R.layout.recycler_schedule_view,parent,false)
        //ビューホルダオブジェクトを作成する
        return RecyclerScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerScheduleViewHolder, position: Int) {
        // TODO 値を格納する
        val item = items[position]

        val videoId =item.id

        holder.tvScheduleTime.text=item.liveStreamingDetails?.scheduledStartTime

        item?.snippet?.let {
            var distributor = memberName(it.channelId)
            var imgStr=it.thumbnails?.medium?.url
            var broadcastStatus= "配信中（仮）$videoId"
            var broadcastTitle=it.title

            // TODO Viewholderに値を設定する
            holder.tvDistributor.text=distributor
            holder.tvBroadcastStatus.text=broadcastStatus
            holder.tvBroadcastTitle.text=broadcastTitle
            //画像を設定
            if(context!=null) {
                Glide.with(context).load(imgStr).into(holder.ivImg)
            }
        }
    }

    override fun getItemCount(): Int {
        //リストデータの件数をリターン
        return items.size
    }



}