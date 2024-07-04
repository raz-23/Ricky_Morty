package com.sh.assignment.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.sh.assignment.R
import com.sh.assignment.api.ApiService
import com.sh.assignment.api.RetrofitHelper
import com.sh.assignment.databinding.ActivityCharactersDetailsBinding
import com.sh.assignment.utils.FavoritesCharHelper
import com.sh.assignment.viewModels.DetailsViewModel
import com.sh.assignment.viewModels.DetailsViewModelFactory
import com.sh.assignment.viewModels.MainViewModel
import com.sh.assignment.viewModels.MainViewModelFactory

class CharactersDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCharactersDetailsBinding
    lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getIntExtra("id", 0)
        val service = RetrofitHelper.getInstance().create(ApiService::class.java)
        val repo = com.sh.assignment.repo.ApiService(service)
        viewModel = ViewModelProvider(this, DetailsViewModelFactory(repo,id)).get(DetailsViewModel::class.java)

        viewModel.character.observe(this){character->

            binding.apply {
                textCharName.text = character.name
                imgChar.load(character.image)
                charStatus.text = character.status.ifEmpty { "None" }
                charGender.text = character.gender.ifEmpty { "None" }
                charType.text = character.type.ifEmpty { "None" }
                charSpcies.text = character.species.ifEmpty { "None" }
                if(FavoritesCharHelper.isFavorite(baseContext,id)){
                    addToFav.text = "Remove from Favorites"
                    addToFav.background = getDrawable(R.color.light_red)

                }else{
                    addToFav.text = "Add to Favorites"
                    addToFav.background = getDrawable(R.color.light_green)
                }
                addToFav.setOnClickListener {
                    if(FavoritesCharHelper.isFavorite(baseContext,id)){
                        FavoritesCharHelper.removeFavorite(baseContext,id)
                        addToFav.text = "Add to Favorites"
                        addToFav.background = getDrawable(R.color.light_green)
                        Toast.makeText(baseContext,character.name + " Removed From Favorites" , Toast.LENGTH_SHORT).show()
                    }else{
                        FavoritesCharHelper.addFavorite(baseContext,id)
                        addToFav.text = "Remove from Favorites"
                        addToFav.background = getDrawable(R.color.light_red)
                        Toast.makeText(baseContext,character.name + " Added To Favorites" , Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}