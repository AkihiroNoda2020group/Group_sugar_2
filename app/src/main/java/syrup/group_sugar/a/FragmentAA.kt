package syrup.group_sugar.a

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import syrup.group_sugar.a.adapter.DelegateAdapter
import kotlinx.android.synthetic.main.fragment_a.message
import kotlinx.android.synthetic.main.fragment_a.progressBar
import kotlinx.android.synthetic.main.fragment_a.reload
import kotlinx.android.synthetic.main.fragment_aa.*
import syrup.group_sugar.R
import syrup.group_sugar.a.adapter.displayer.*
import java.lang.reflect.Array.get
import java.util.ArrayList

class FragmentAA: Fragment(R.layout.fragment_aa) {
    private var mItemList: ArrayList<ItemDisplayer> = ArrayList()
    private val mAdapter: DelegateAdapter = DelegateAdapter()
    override fun onResume() {
        super.onResume()
        Log.i("bottomNavigationTest", "onResume-FragmentAa")
    }

    override fun onPause() {
        super.onPause()
        Log.i("bottomNavigationTest", "onPause-FragmentAa")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("bottomNavigationTest", "onViewCreated-FragmentAa")

        val url = arguments?.getString("url")
        val asynk = AsyncRecipe(this, message, reload, progressBar, url)
        asynk.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    fun Toast1(list: List<Recipe>){
        Log.d("size", mItemList.size.toString())
        if(mItemList.size == 0) {

            mItemList.add(TitleDisplayer(list.get(0).title))
            mItemList.add(HeaderDisplayer(list.get(0).ImageId))

            mItemList.add(TitleDisplayer("材料"))
            for (i in 0..list.get(0).Item_name.size - 1) {
                mItemList.add(PopularDisplayer(list.get(0).Item_name.get(i),
                        list.get(0).Item_serving.get(i)))
            }
            mItemList.add(TitleDisplayer("Article"))
            mItemList.add(ArticleDisplayer())
            mItemList.add(ArticleDisplayer())
//            mItemList.add(ArticleDisplayer())
//            mItemList.add(ArticleDisplayer())
//            mItemList.add(ArticleDisplayer())
//            mItemList.add(ArticleDisplayer())
//            mItemList.add(ArticleDisplayer())
//            mItemList.add(ArticleDisplayer())
//            mItemList.add(ArticleDisplayer())
//            mItemList.add(ArticleDisplayer())

            mAdapter.setData(mItemList)
        }

        RecipeRV.layoutManager = LinearLayoutManager(
                context, // context
                RecyclerView.VERTICAL, // orientation
                false // reverse layout
        )
        RecipeRV.adapter = mAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("bottomNavigationTest", "onCreateView-FragmentAa")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        Log.i("bottomNavigationTest", "onDestroyView-FragmentAa")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.i("bottomNavigationTest", "onDestroy-FragmentAa")
        super.onDestroy()
    }
}