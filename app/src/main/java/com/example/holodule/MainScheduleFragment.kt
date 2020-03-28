package com.example.holodule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [MainScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainScheduleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main_schedule, container, false)
        //APIから値を取得する
        val searchReslt =ApiYTSearch().execute().get()
        //RecyclerViewの取得
        val lvSchedule =view.findViewById<RecyclerView>(R.id.lvSchedule)
        //LinearLayoutManagerオブジェクトの作成
        val layout = LinearLayoutManager(this.context)
        //RecyclerViewにレイアウトマネージャーを設定する
        lvSchedule.layoutManager=layout
        //アダプタp部ジェクトの作成
        val adapter =RecyclerScheduleAdapter(this.context,searchReslt)

        lvSchedule.adapter=adapter
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainScheduleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                MainScheduleFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
