package syrup.group_sugar.a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import syrup.group_sugar.R

/**
 * Created by Govind on 01/09/2018.
 */

class MyAdapter(private val imageList: ArrayList<Image>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // Viewの初期化
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView
        val title: TextView

        init {
            image = view.findViewById(R.id.imageView)
            title = view.findViewById(R.id.textView)
        }
    }

    // レイアウトの設定
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.parse_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Viewの設定
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val images = imageList[position]

        viewHolder.title.text = images.title
//        viewHolder.url.text = images.url
        Picasso.get()
            .load(images.imageId)
            .priority(Picasso.Priority.HIGH)
            .fit()
            .centerCrop() //resizeで指定した範囲になるよう中央から切り出し
            .into(viewHolder.image) //imageViewに流し込み

        viewHolder.image.setOnClickListener { v ->
            //ここにアイテムをクリックした際の挙動を記載
            val bundle = bundleOf("url" to images.url)
            v.findNavController().navigate(R.id.action_fragmentA_to_fragmentAA, bundle)
        }
    }

    // 表示数を返す
    override fun getItemCount() = imageList.size
}
