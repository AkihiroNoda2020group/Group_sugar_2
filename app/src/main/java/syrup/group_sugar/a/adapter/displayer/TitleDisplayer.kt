package syrup.group_sugar.a.adapter.displayer

import android.view.View
import syrup.group_sugar.a.adapter.ViewType
import kotlinx.android.synthetic.main.item_title.view.*

class TitleDisplayer(val title:String): ItemDisplayer {
    override fun getViewType(): ViewType = ViewType.TITLE

    override fun bind(itemView: View) {
        itemView.apply { itemTitle.text = title }
    }

}