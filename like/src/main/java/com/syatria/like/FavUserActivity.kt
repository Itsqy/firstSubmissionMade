package com.syatria.like

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.syatria.core.ui.UserAdapter
import com.syatria.like.databinding.ActivityFavUserBinding
import com.syatria.myapplication.detail.DetailUserActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavUserBinding
    private val favViewModel: FavViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite User"
        loadKoinModules(favModule)


        favViewModel.dataFavUser.observe(this@FavUserActivity) { favData ->
            if (favData != null) {
                val userAdapter = UserAdapter {
                    val intent = Intent(applicationContext, DetailUserActivity::class.java)
                    intent.putExtra(DetailUserActivity.EXTRA_DETAIL, it)
                    startActivity(intent)
                }
                userAdapter.submitList(favData)
                binding?.rvFavUsers?.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = userAdapter
                }
            }



        }
    }
}