package com.dicoding.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.rv_user)
        searchView = findViewById(R.id.searchView)

        //TODO 3 Initialize adapter
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        searchUser()


        //TODO 4 Create viewModel


    }

    private fun searchUser() {
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    return if (query.isEmpty()){
                        true
                    }else{
                        setUser(query)
                        true
                    }
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

            }
        )
    }

    private fun setUser(username: String) {
        val listUser = ArrayList<User>()
        val url = "https://api.github.com/search/users?q=$username"
        val client = AsyncHttpClient()
        client.addHeader("Authorization", BuildConfig.GITHUB_TOKEN)
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val resultObject =JSONObject(result)
                    val list = resultObject.getJSONArray("items")
                    for (i in 0 until list.length()){
                        val user = list.getJSONObject(i)
                        val userList = User()
                        userList.id = user.getString("id")
                        userList.username = user.getString("login")
                        userList.url = user.getString("html_url")
                        userList.photo = user.getString("avatar_url")
                        listUser.add(userList)
                    }
                    adapter.setData(listUser)
                    adapter.notifyDataSetChanged()
                }catch (e: Exception){
                    Log.d("Error ", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                Log.d("Failure ", error.message.toString())
            }

        })
    }


    private fun getUser() {
        TODO("Not yet implemented")
    }
}