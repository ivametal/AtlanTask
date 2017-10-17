package hopeisaprison.atlantask.listeners

import android.os.AsyncTask
import android.view.View
import hopeisaprison.atlantask.adapter.CardsAdapter
import hopeisaprison.atlantask.connection.ConnectionManager
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by hopeisaprison on 10/17/17.
 */
class GetUsersListener(private val viewHolder : CardsAdapter.UsersViewHolder) : View.OnClickListener {
    override fun onClick(v: View?) {
        InfoLoader().execute()
    }

    private inner class InfoLoader : AsyncTask<Void?, Void?, String?>() {
        override fun doInBackground(vararg params: Void?): String? {
            val connection = ConnectionManager()
            return (connection.getUsers())
        }

        override fun onPostExecute(result: String?) {
            val jsonAnswer = JSONArray(result)
            var info = ""
            (0 until 5)
                    .map { jsonAnswer.getJSONObject(it) }
                    .forEach {
                        info+= it.getString("name")+" "+ it.getString("username") +
                                " " + it.getString("website") +"\n"
                    }
            viewHolder.content.text = info
            viewHolder.getBtn.visibility = View.INVISIBLE
            viewHolder.deleteButton.visibility = View.VISIBLE
        }
    }
}