package hopeisaprison.atlantask.listeners

import android.content.Context
import android.os.AsyncTask
import android.view.View
import com.squareup.picasso.Picasso
import hopeisaprison.atlantask.adapter.CardsAdapter
import hopeisaprison.atlantask.connection.ConnectionManager
import org.json.JSONObject

/**
 * Created by hopeisaprison on 10/17/17.
 */
class GetPhotoListener(private val mContext : Context, val viewHolder : CardsAdapter.PhotoViewHolder) : View.OnClickListener {
    override fun onClick(v: View?) {
        InfoLoader().execute()
    }

    private inner class InfoLoader : AsyncTask<Void?, Void?, String?>() {
        override fun doInBackground(vararg params: Void?): String? {
            val connection = ConnectionManager()
            return (connection.getImage(3))
        }

        override fun onPostExecute(result: String?) {
            val answerJson = JSONObject(result)
            Picasso.with(mContext)
                    .load(answerJson.getString("url"))
                    .into(viewHolder.image)
            viewHolder.getBtn.visibility = View.INVISIBLE
            viewHolder.deleteButton.visibility = View.VISIBLE

        }
    }
}