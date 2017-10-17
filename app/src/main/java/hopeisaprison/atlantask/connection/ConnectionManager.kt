package hopeisaprison.atlantask.connection

import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by hopeisaprison on 10/17/17.
 */
class ConnectionManager {
    private val client = OkHttpClient()

    fun getPost(position : Int) = getResponse("https://jsonplaceholder.typicode.com/posts/$position")

    fun getComment(position: Int) = getResponse("https://jsonplaceholder.typicode.com/comments/$position")


    fun getUsers() = getResponse("https://jsonplaceholder.typicode.com/users")

    fun getImage(position : Int) = getResponse("https://jsonplaceholder.typicode.com/photos/$position")


    fun getTodo() : String? {
        val randomValue = System.currentTimeMillis()%200
        return getResponse("https://jsonplaceholder.typicode.com/todos/$randomValue")
    }

    fun getResponse(url : String) : String? {
        val request = Request.Builder()
                .get()
                .url(url)
                .build()
        val response = client.newCall(request).execute()

        return response.body()?.string()
    }
}