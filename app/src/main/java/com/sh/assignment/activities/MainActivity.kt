package com.sh.assignment.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sh.assignment.R
import com.sh.assignment.adapters.CharactersHomeAdapter
import com.sh.assignment.api.ApiService
import com.sh.assignment.api.RetrofitHelper
import com.sh.assignment.databinding.ActivityMainBinding
import com.sh.assignment.viewModels.MainViewModel
import com.sh.assignment.viewModels.MainViewModelFactory
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val service = RetrofitHelper.getInstance().create(ApiService::class.java)
        val repo = com.sh.assignment.repo.ApiService(service)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repo)).get(MainViewModel::class.java)

        mainViewModel.characters.observe(this) {
            Log.d("Result", it.toString())
            binding.recyclerView.adapter = CharactersHomeAdapter(it.results)
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }

    }
}
