package hopeisaprison.atlantask.listeners

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import hopeisaprison.atlantask.adapter.CardsAdapter


/**
 * Created by hopeisaprison on 10/17/17.
 */
class DeleteButtonListener(private val viewHolder : CardsAdapter.BasicViewHolder) : View.OnClickListener {
    override fun onClick(v: View?) {
        (viewHolder as? CardsAdapter.PostsViewHolder)?.clear()
        (viewHolder as? CardsAdapter.CommentsViewHolder)?.clear()
        (viewHolder as? CardsAdapter.UsersViewHolder)?.clear()
        (viewHolder as? CardsAdapter.PhotoViewHolder)?.clear()
        (viewHolder as? CardsAdapter.TodosViewHolder)?.clear()
    }
}