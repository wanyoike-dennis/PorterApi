package com.dennis.porterapi.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dennis.porterapi.R
import com.dennis.porterapi.data.Characters
import java.util.*

class RecyclerAdapter(private val onClick: (Characters) -> Unit) : RecyclerView.Adapter<MyViewHolder>() , Filterable {
    private var characters:List<Characters> = emptyList()
    private var filteredCharacters:List<Characters> = emptyList()


    @SuppressLint("NotifyDataSetChanged")
    fun setCharacters(characters:List<Characters>){
        this.characters = characters
        this.filteredCharacters = characters
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return MyViewHolder(view , onClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = filteredCharacters[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return filteredCharacters.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString()
                filteredCharacters = if (query.isEmpty()){
                    characters
                }
                else {
                    val lowercaseQuery = query.lowercase(Locale.getDefault())
                    characters.filter {
                        character ->
                        character.name.lowercase(Locale.getDefault()).contains(lowercaseQuery) ||
                                character.house.lowercase(Locale.getDefault()).contains(lowercaseQuery)
                    }

                }
                val results = FilterResults()
                results.values = filteredCharacters
                return results
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredCharacters = results?.values as List<Characters>
                notifyDataSetChanged()
            }

        }
    }


}

class MyViewHolder(itemView: View,val onClick: (Characters) -> Unit)
    : RecyclerView.ViewHolder(itemView) {
    private val nameTextView : TextView = itemView.findViewById(R.id.txt_name)
    private val houseTextView :TextView = itemView.findViewById(R.id.text_house)
    private var currentCharacter: Characters? = null

    fun bind(character:Characters){
        currentCharacter = character
        nameTextView.text = character.name
        houseTextView.text = character.house
    }

    init {
        itemView.setOnClickListener {
            currentCharacter?.let {
                onClick(it)
            }
        }
    }
}
