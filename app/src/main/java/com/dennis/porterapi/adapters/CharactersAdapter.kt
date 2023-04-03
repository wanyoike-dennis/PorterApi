package com.dennis.porterapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dennis.porterapi.R
import com.dennis.porterapi.data.Characters

class CharactersAdapter(private val onClick: (Characters) -> Unit) :
    ListAdapter<Characters, CharactersAdapter.CharacterViewHolder>(CharacterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return CharacterViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    class CharacterViewHolder(itemView: View, val onClick: (Characters) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val characterName: TextView = itemView.findViewById(R.id.txt_character_name)
        private var currentCharacter: Characters? = null



        fun bind(character: Characters) {
            currentCharacter = character
            characterName.text = character.name

        }
        init {
            itemView.setOnClickListener {
                currentCharacter?.let {
                    onClick(it)
                }
            }
        }
    }

}


class CharacterDiffCallback : DiffUtil.ItemCallback<Characters>() {
    override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem == newItem
    }

}