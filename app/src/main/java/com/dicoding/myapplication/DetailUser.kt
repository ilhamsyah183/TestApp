package com.dicoding.myapplication

data class DetailUser(
    var avatarUrl: String? = null,
    var company: String? = null,
    var followers: Int = 0,
    var following: Int = 0,
    var id: Int = 0,
    var location: String? = null,
    var username: String? = null,
    var name: String? = null,
    var publicRepos: Int = 0,
)