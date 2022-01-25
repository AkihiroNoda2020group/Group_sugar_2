package syrup.group_sugar.a

import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_a.reload
import kotlinx.android.synthetic.main.fragment_c.*
import kotlinx.android.synthetic.main.parse_item.*
import syrup.group_sugar.R
import syrup.group_sugar.c.FragmentC
import java.util.*


class FragmentA : Fragment(R.layout.fragment_a) {

    lateinit var mAdapter: MyAdapter
    private var mImageList: ArrayList<Image> = ArrayList()

    private var urlList1: List<String>? = null
    private var urlList2: List<String>? = null
    private var urlList3: List<String>? = null
    private var urlList4: List<String>? = null

    override fun onResume() {
        super.onResume()
        Log.i("bottomNavigationTest", "onResume-FragmentA")
    }

    override fun onPause() {
        super.onPause()
        Log.i("bottomNavigationTest", "onPause-FragmentA")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomNavigationTest", "onViewCreated-FragmentA")

        reload.setOnClickListener {
            val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
            ft.detach(this).attach(this).commit()
        }

        val url = listOf(
                "https://recipe.rakuten.co.jp/category/36-496",
                "https://recipe.rakuten.co.jp/category/37-498",
                "https://recipe.rakuten.co.jp/category/37-500",
                "https://recipe.rakuten.co.jp/search/%E5%8C%85%E4%B8%81%E4%B8%8D%E8%A6%81/"
        )

//        for (i in url.indices) {
        val asynk = AsyncImages(this, message, reload, progressBar, url.get(0))
        //            asynk.execute()
        asynk.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
//        }
    }

    fun Toast(list: List<List<String>>) {
        Log.d("urllist", mImageList.size.toString())
        if(mImageList.size == 0) {
            for (i in 0 until list.get(0).size) {
                mImageList.add(Image(list.get(0).get(i), list.get(1).get(i), list.get(2).get(i)))
            }
        }

        // LayoutManagerの設定
        recyclerView.layoutManager = LinearLayoutManager(
                context, // context
                RecyclerView.VERTICAL, // orientation
                false // reverse layout
        )

        // CustomAdapterの生成と設定
        mAdapter = MyAdapter(mImageList)
        recyclerView.adapter = mAdapter
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.i("bottomNavigationTest", "onCreateView-FragmentA")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        Log.i("bottomNavigationTest", "onDestroyView-FragmentA")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i("bottomNavigationTest", "onDestroy-FragmentA")
        super.onDestroy()
    }
}