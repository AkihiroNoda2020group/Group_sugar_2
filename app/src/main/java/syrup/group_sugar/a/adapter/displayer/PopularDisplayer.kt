package syrup.group_sugar.a.adapter.displayer

import android.view.View
import kotlinx.android.synthetic.main.fragment_b.view.*
import kotlinx.android.synthetic.main.item_popular.view.*
import syrup.group_sugar.a.adapter.ViewType
import syrup.group_sugar.a.adapter.displayer.ItemDisplayer

class PopularDisplayer(val Item_name:String, val Item_serving:String): ItemDisplayer {
    override fun getViewType(): ViewType = ViewType.POPULAR

    override fun bind(itemView: View) {
        itemView.item_name.text = Item_name
        itemView.item_serving.text = Item_serving
    }
}