package hopeisaprison.atlantask.listeners

import android.os.AsyncTask
import android.view.View
import hopeisaprison.atlantask.adapter.CardsAdapter
import hopeisaprison.atlantask.connection.ConnectionManager
import org.json.JSONObject

/**
 * Created by hopeisaprison on 10/17/17.
 */
class GetTodosListener(private val viewHolder : CardsAdapter.TodosViewHolder) : View.OnClickListener {
    override fun onClick(v: View?) {
        InfoLoader().execute()
    }

    private inner class InfoLoader : AsyncTask<Void?, Void?, String?>() {
        override fun doInBackground(vararg params: Void?): String? {
            val connection = ConnectionManager()
            return (connection.getTodo())
        }

        override fun onPostExecute(result: String?) {
            val jsonAnswer = JSONObject(result)
            viewHolder.content.text = jsonAnswer.toString()
            viewHolder.getBtn.visibility = View.INVISIBLE
            viewHolder.deleteButton.visibility = View.VISIBLE
        }
    }
}