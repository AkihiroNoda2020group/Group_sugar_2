package syrup.group_sugar.a.adapter.displayer

import android.view.View
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_popular.view.*
import kotlinx.android.synthetic.main.item_popular.view.item_name
import syrup.group_sugar.a.adapter.ViewType

class ArticleDisplayer(val i:Int, val Howto_text:String): ItemDisplayer {
    override fun getViewType(): ViewType = ViewType.ARTICLE

    override fun bind(itemView: View) {
        itemView.Howto_text_no.text = (i + 1).toString()
        itemView.Howto_text.text = Howto_text
      }
}