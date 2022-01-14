package syrup.group_sugar.a.adapter.displayer

import android.util.Log
import android.view.View
import com.squareup.picasso.Picasso
import syrup.group_sugar.a.adapter.ViewType
import kotlinx.android.synthetic.main.item_header.view.*
import syrup.group_sugar.R

class HeaderDisplayer(val Imageurl:String): ItemDisplayer {
    override fun getViewType(): ViewType = ViewType.HEADER



    override fun bind(itemView: View) {
        Picasso.get()
                .load(Imageurl)
                .priority(Picasso.Priority.HIGH)
                .fit()
                .centerCrop() //resizeで指定した範囲になるよう中央から切り出し
                .into(itemView.imageView) //imageViewに流し込み
//        itemView.imageView.setImageResource(R.drawable.ic_launcher_foreground)
//        Log.d("urlaaa", Imageurl)
    }
}