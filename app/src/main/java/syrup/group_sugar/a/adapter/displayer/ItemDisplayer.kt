package syrup.group_sugar.a.adapter.displayer

import android.view.View
import syrup.group_sugar.a.adapter.ViewType

interface ItemDisplayer {
    fun getViewType(): ViewType
    fun bind(itemView:View)
}