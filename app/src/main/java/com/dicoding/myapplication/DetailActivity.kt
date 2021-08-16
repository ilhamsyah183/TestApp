package com.dicoding.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.myapplication.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        username?.let {
            observeDetailUser(it)
        }

    }

    private fun observeDetailUser(username: String) {
        viewModel.getDetail(username)
        viewModel.detailUser.observe(this) { detailUser ->
            binding.apply {
                tvUsername.text = detailUser.username
                Glide.with(this@DetailActivity)
                    .load(detailUser.avatarUrl)
                    .into(imgUser)
                tvTotalFollowers.text = detailUser.followers.toString()
                tvTotalFollowing.text = detailUser.following.toString()
                tvTotalRepo.text = detailUser.publicRepos.toString()
                tvName.text = detailUser.name
                tvLocation.text = detailUser.location
                tvCompany.text = detailUser.company
            }
        }
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
}