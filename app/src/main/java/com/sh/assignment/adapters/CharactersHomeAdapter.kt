package com.sh.assignment.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sh.assignment.R
import com.sh.assignment.activities.CharactersDetailsActivity
import com.sh.assignment.databinding.CharactersItemBinding
import com.sh.assignment.models.Result
import com.sh.assignment.utils.FavoritesCharHelper


class CharactersHomeAdapter(private val list: List<Result>) :
    RecyclerView.Adapter<CharactersHomeAdapter.CharactersViewHolder>() {
    class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CharactersItemBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.characters_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            textCharName.text = item.name
            imgCharacter.load(item.image)
            textCharStatus.text = "Status : " + item.status
            mainCard.setOnClickListener {
                val intent = Intent(it.context, CharactersDetailsActivity::class.java)
                intent.putExtra("id", item.id)
                it.context.startActivity(intent)
            }
        }
    }
}


