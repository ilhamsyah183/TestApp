package com.dicoding.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class DetailViewModel : ViewModel() {

    private val _detailUser = MutableLiveData<DetailUser>()
    val detailUser: LiveData<DetailUser> = _detailUser

    fun getDetail(username: String) {
        val dataUser = DetailUser()
        val url = "https://api.github.com/users/$username"
        val client = AsyncHttpClient()
        client.apply {
            addHeader("Authorization", BuildConfig.GITHUB_TOKEN)
            addHeader("User-Agent", "request")
            get(url, object : AsyncHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>,
                    responseBody: ByteArray
                ) {
                    try {
                        val result = String(responseBody)
                        val resultObject = JSONObject(result)
                        dataUser.apply {
                            avatarUrl = resultObject.getString("avatar_url")
                            company = resultObject.getString("company")
                            followers = resultObject.getInt("followers")
                            following = resultObject.getInt("following")
                            id = resultObject.getInt("id")
                            location = resultObject.getString("location")
                            this.username = resultObject.getString("login")
                            name = resultObject.getString("name")
                            publicRepos = resultObject.getInt("public_repos")
                        }
                        _detailUser.value = dataUser

                    } catch (e: Exception) {
                        Log.e("Error", e.message.toString())
                    }
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
                ) {
                    Log.e("Error", error?.message.toString())
                }
            })
        }

    }

}