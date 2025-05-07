package com.example.ktor.ui.fragment.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktor.data.model.response.Result
import com.example.ktor.databinding.ItemCharacterBinding
import com.example.ktor.util.loadImage

class CharacterAdapter(private val characterList : List<Result>
    ) : RecyclerView.Adapter<CharacterAdapter.CharacterVH>() {

    class CharacterVH(val binding : ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val view = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterVH(view)
    }
    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        with(holder.binding){
            val data = characterList[position]
            imageCharacter.loadImage(characterList[position].image)
            textCharacterName.text = data.name

        }
    }
    override fun getItemCount(): Int {
        return characterList.size
    }
}