package hopeisaprison.atlantask.listeners

import android.os.AsyncTask
import android.view.View
import hopeisaprison.atlantask.adapter.CardsAdapter
import hopeisaprison.atlantask.connection.ConnectionManager
import org.json.JSONObject

/**
 * Created by hopeisaprison on 10/17/17.
 */
class GetPostsListener(private val viewHolder: CardsAdapter.PostsViewHolder) : View.OnClickListener {
    override fun onClick(v: View?) {
        if (viewHolder.positionEditTxt.text.isEmpty() ||
                viewHolder.positionEditTxt.text.toString().toInt()>100)
            return
        InfoLoader().execute()
    }

    private inner class InfoLoader : AsyncTask<Void?, Void?, String?>() {
        override fun doInBackground(vararg params: Void?): String? {
            val connection = ConnectionManager()
            return (connection.getPost(viewHolder.positionEditTxt.text.toString().toInt()))
        }

        override fun onPostExecute(result: String?) {
            val jsonAnswer = JSONObject(result)
            viewHolder.body.text = jsonAnswer.getString("body")
            viewHolder.body.visibility = View.VISIBLE
            viewHolder.title.text = jsonAnswer.getString("title")
            viewHolder.title.visibility = View.VISIBLE
            viewHolder.getBtn.visibility = View.INVISIBLE
            viewHolder.positionEditTxt.visibility = View.INVISIBLE
            viewHolder.deleteButton.visibility = View.VISIBLE
        }
    }
}