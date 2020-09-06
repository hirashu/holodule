package com.example.holodule

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class RecyclerScheduleAdapter(
    private val context: Context?,
    private val items: List<YTApiVideo.Item>
) : RecyclerView.Adapter<RecyclerScheduleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerScheduleViewHolder {
        //インフレータを設定
        val inflater = LayoutInflater.from(context)
        //1行分の画像部品として設定する
        val view = inflater.inflate(R.layout.recycler_schedule_view, parent, false)
        //ビューホルダオブジェクトを作成する
        return RecyclerScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerScheduleViewHolder, position: Int) {
        // TODO 値を格納する
        val item = items[position]

        val videoId = item.id

        //TODO前の項目と同じ時間なら
        if (position != 0) {
            if (item.liveStreamingDetails?.scheduledStartTime?.value == items[position - 1].liveStreamingDetails?.scheduledStartTime?.value) {
                holder.tvScheduleTime.visibility = View.GONE
            } else {
                holder.tvScheduleTime.visibility = View.VISIBLE
                holder.tvScheduleTime.text =
                    DateUnit().formHmmDate(item.liveStreamingDetails?.scheduledStartTime?.value) + " ~"
            }
        } else {
            holder.tvScheduleTime.visibility = View.VISIBLE
            holder.tvScheduleTime.text =
                DateUnit().formHmmDate(item.liveStreamingDetails?.scheduledStartTime?.value) + " ~"
        }

        item?.snippet?.let {
            var distributor = memberName(it.channelId)
            var imgStr = it.thumbnails?.medium?.url
            var broadcastStatus = "配信中（非表示）$videoId"
            var broadcastTitle = it.title

            // TODO Viewholderに値を設定する
            holder.tvDistributor.text = distributor
            holder.tvBroadcastStatus.text = broadcastStatus
            holder.tvBroadcastTitle.text = broadcastTitle
            //画像を設定
            if (context != null) {
                Glide.with(context).load(imgStr).into(holder.ivImg)

                holder.ivImg.setOnClickListener {
                    val uri: Uri = Uri.parse("https://www.youtube.com/watch?v="+item.id)
                    val i = Intent(Intent.ACTION_VIEW, uri)
                    // アプリが見つからなければ、ActivityNotFoundException
                    startActivity(context,i,null)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        //リストデータの件数をリターン
        return items.size
    }


}