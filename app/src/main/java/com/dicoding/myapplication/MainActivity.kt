package com.dicoding.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myapplication.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        //TODO 4 Create viewModel

        recycler = activityMainBinding.rvUser
        searchView = activityMainBinding.searchView
        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(UserViewModel::class.java)


        //TODO 3 Initialize adapter
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        userViewModel.getUsers().observe(this,{ listUser ->
            if(listUser != null){
                adapter.setData(listUser)
                adapter.notifyDataSetChanged()
            }

        })

        searchUser()



    }

    private fun searchUser() {
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    return if (query.isEmpty()){
                        true
                    }else{
                        userViewModel.setUser(query)
                        true
                    }
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

            }
        )
    }

}