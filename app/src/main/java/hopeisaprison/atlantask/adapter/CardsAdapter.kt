package hopeisaprison.atlantask.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView.ViewHolder
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import hopeisaprison.atlantask.R
import hopeisaprison.atlantask.listeners.*

/**
 * Created by hopeisaprison on 10/17/17.
 */
class CardsAdapter(private val mContext : Context) : RecyclerView.Adapter<CardsAdapter.BasicViewHolder>() {

    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BasicViewHolder? {
        return when (viewType) {
            0 -> PostsViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.cardview_posts, parent, false))
            1 -> CommentsViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.cardview_comments, parent, false))
            2 -> UsersViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.cardview_users, parent, false))
            3 -> PhotoViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.cardview_photo, parent, false))
            4 -> TodosViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.cardview_todos, parent, false))
            else -> null
        }
    }

    override fun onBindViewHolder(holder: BasicViewHolder?, position: Int) {
        holder?.deleteButton?.setOnClickListener(DeleteButtonListener(holder))
        when (position) {
            0 -> (holder as PostsViewHolder).getBtn.setOnClickListener(GetPostsListener(holder))
            1 -> (holder as CommentsViewHolder).getBtn.setOnClickListener(GetCommentlistener(holder))
            2 -> (holder as UsersViewHolder).getBtn.setOnClickListener(GetUsersListener(holder))
            3 -> (holder as PhotoViewHolder).getBtn.setOnClickListener(GetPhotoListener(mContext, holder))
            4 -> (holder as TodosViewHolder).getBtn.setOnClickListener(GetTodosListener(holder))
        }
    }

    override fun getItemCount() = 5

    open class BasicViewHolder(itemView: View) : ViewHolder(itemView) {
        val deleteButton = itemView.findViewById<ImageView>(R.id.cardview_deletebutton)
    }

    class PostsViewHolder(itemView: View) : BasicViewHolder(itemView) {
        val positionEditTxt = itemView.findViewById<EditText>(R.id.cardview_posts_positionedittext)
        val getBtn = itemView.findViewById<Button>(R.id.cardview_posts_getbutton)
        val title = itemView.findViewById<TextView>(R.id.cardview_posts_title)
        val body = itemView.findViewById<TextView>(R.id.cardview_posts_body)
        fun clear() {
            title.visibility = View.INVISIBLE
            body.visibility = View.INVISIBLE
            positionEditTxt.text.clear()
            positionEditTxt.visibility = View.VISIBLE
            getBtn.visibility = View.VISIBLE
            super.deleteButton.visibility = View.INVISIBLE
        }
    }

    class CommentsViewHolder(itemView: View) : BasicViewHolder(itemView) {
        val positionEditTxt = itemView.findViewById<EditText>(R.id.cardview_comment_positionedittext)
        val getBtn = itemView.findViewById<Button>(R.id.cardview_comment_getbutton)
        val name = itemView.findViewById<TextView>(R.id.cardview_comment_name)
        val mail = itemView.findViewById<TextView>(R.id.cardview_comment_mail)
        val body = itemView.findViewById<TextView>(R.id.cardview_comment_body)

        fun clear() {
            name.visibility = View.INVISIBLE
            body.visibility = View.INVISIBLE
            mail.visibility = View.INVISIBLE
            positionEditTxt.text.clear()
            positionEditTxt.visibility = View.VISIBLE
            getBtn.visibility = View.VISIBLE
            super.deleteButton.visibility = View.INVISIBLE
        }
    }

    class UsersViewHolder(itemView: View) : BasicViewHolder(itemView) {
        val getBtn = itemView.findViewById<Button>(R.id.cardview_users_getbutton)
        val content = itemView.findViewById<TextView>(R.id.cardview_users_content)

        fun clear() {
            content.text = ""
            getBtn.visibility = View.VISIBLE
            super.deleteButton.visibility = View.INVISIBLE
        }
    }

    class PhotoViewHolder(itemView: View) : BasicViewHolder(itemView) {
        val getBtn = itemView.findViewById<Button>(R.id.cardview_image_getbutton)
        val image = itemView.findViewById<CircleImageView>(R.id.cardview_image_container)

        fun clear() {
            image.setImageBitmap(null)
            getBtn.visibility = View.VISIBLE
            super.deleteButton.visibility = View.INVISIBLE
        }
    }

    class TodosViewHolder(itemView: View) : BasicViewHolder(itemView) {

        val getBtn = itemView.findViewById<Button>(R.id.cardview_todos_getbutton)
        val content = itemView.findViewById<TextView>(R.id.cardview_todos_content)

        fun clear() {
            content.text = ""
            getBtn.visibility = View.VISIBLE
            super.deleteButton.visibility = View.INVISIBLE
        }
    }
}