package hopeisaprison.atlantask.listeners

import android.os.AsyncTask
import android.view.View
import hopeisaprison.atlantask.adapter.CardsAdapter
import hopeisaprison.atlantask.connection.ConnectionManager
import org.json.JSONObject

/**
 * Created by hopeisaprison on 10/17/17.
 */
class GetCommentlistener(private val viewHolder : CardsAdapter.CommentsViewHolder) : View.OnClickListener {
    override fun onClick(v: View?) {
        if (viewHolder.positionEditTxt.text.isEmpty() ||
                viewHolder.positionEditTxt.text.toString().toInt()>500)
            return
        InfoLoader().execute()
    }

    private inner class InfoLoader : AsyncTask<Void?, Void?, String?>() {
        override fun doInBackground(vararg params: Void?): String? {
            val connection = ConnectionManager()
            return (connection.getComment(viewHolder.positionEditTxt.text.toString().toInt()))
        }

        override fun onPostExecute(result: String?) {
            val jsonAnswer = JSONObject(result)
            viewHolder.body.text = jsonAnswer.getString("body")
            viewHolder.body.visibility = View.VISIBLE
            viewHolder.mail.text = jsonAnswer.getString("email")
            viewHolder.mail.visibility = View.VISIBLE
            viewHolder.name.text = jsonAnswer.getString("name")
            viewHolder.name.visibility = View.VISIBLE
            viewHolder.getBtn.visibility = View.INVISIBLE
            viewHolder.positionEditTxt.visibility = View.INVISIBLE
            viewHolder.deleteButton.visibility = View.VISIBLE
        }
    }
}