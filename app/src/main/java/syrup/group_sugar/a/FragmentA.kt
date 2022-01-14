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
//                FragmentC()?.textViewC?.text="aaa"
                requireFragmentManager().beginTransaction().apply {
                    replace(R.id.fragmentContainer, FragmentC())
//                    addToBackStack(null)
                    commit()
                }
//            val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
//            ft.detach(this).attach(this).commit()
        }

//            findNavController().navigate(R.id.action_home_to_detail)

//        val img1 = listOf(imageView, imageView2, imageView3, imageView4, imageView5,
//                imageView6, imageView7, imageView8, imageView9, imageView10)
//        val img2 = listOf(imageView11, imageView12, imageView13, imageView14, imageView15,
//                imageView16, imageView17, imageView18, imageView19, imageView20)
//        val img3 = listOf(imageView21, imageView22, imageView23, imageView24, imageView25,
//                imageView26, imageView27, imageView28, imageView29, imageView30)
//        val img4 = listOf(imageView31, imageView32, imageView33, imageView34, imageView35,
//                imageView36, imageView37, imageView38, imageView39, imageView40)
//        val imgs = listOf<List<ImageView>>(img1, img2, img3, img4)
//
        val url = listOf(
                "https://recipe.rakuten.co.jp/category/36-496",
                "https://recipe.rakuten.co.jp/category/37-498",
                "https://recipe.rakuten.co.jp/category/37-500",
                "https://recipe.rakuten.co.jp/search/%E5%8C%85%E4%B8%81%E4%B8%8D%E8%A6%81/"
        )
//
//        val title = listOf("5分以内の簡単料理", "100円以下の節約料理", "500円前後の節約料理", "包丁不要")
//
//        val tv = listOf(textView, textView2, textView3, textView4)

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
//        // データの作成
//        val cat = Animal("ネコ", 2, list.get(0).get(1))
//        val elephant = Animal("ゾウ", 10, list.get(0).get(2))
//        val horse = Animal("ウマ", 4, list.get(0).get(3))
//        val lion = Animal("ライオン", 6,list.get(0).get(4))
//        mAnimalList = arrayListOf(dog, cat, elephant, horse, lion)

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