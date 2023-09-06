package com.syatria.myapplication.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.syatria.core.domain.model.User
import com.syatria.myapplication.R
import com.syatria.myapplication.databinding.ActivityDetailUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "detail_data"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userdata = intent.getParcelableExtra<User>(EXTRA_DETAIL)
        if (userdata != null) {
            showDetailUser(userdata)
        }
    }

    private fun showDetailUser(userdata: User?) {
        userdata?.let {
            Glide.with(this@DetailUserActivity)
                .load(userdata.imageUser)
                .into(binding.ivDetail)
            supportActionBar?.title = userdata.name
            binding.tvUsernameDetail.text = userdata.name
            binding.tvCompanyDetail.text = userdata.status


            var statusFav = userdata.isFavorite
            setStatusFav(statusFav)
            binding.fabLike.setOnClickListener {
                statusFav = !statusFav
                detailViewModel.setFavUser(userdata, statusFav)
                setStatusFav(statusFav)
                Log.d("fav", "showDetailTourism: $userdata")
            }
        }

    }

    private fun setStatusFav(statusFav: Boolean) {
        if (statusFav) {
            binding.fabLike.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_24
                )
            )
        } else {
            binding.fabLike.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_border_24
                )
            )
        }

    }
}