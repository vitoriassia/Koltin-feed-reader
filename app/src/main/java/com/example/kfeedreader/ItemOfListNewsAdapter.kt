package com.example.kfeedreader

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemOfListNewsAdapter(
    private val listOfNews: ArrayList<MainActivity.ItemOfListNews>,
    private val context: Context
) : RecyclerView.Adapter<ItemOfListNewsAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById(R.id.title_view_item_list) as TextView
        val author = view.findViewById(R.id.author) as TextView
        val dateOfNews = view.findViewById(R.id.date_of_news) as TextView
        val image = view.findViewById<ImageView>(R.id.image_item_list)
        val btnSeeMore = view.findViewById<Button>(R.id.button_see_more)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        val itemViewHolder = ItemViewHolder(view);

        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = listOfNews[position].title
        holder.author.text = listOfNews[position].author
        holder.dateOfNews.text = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale("pt", "br")
        ).format(Date(listOfNews[position].date))
        holder.btnSeeMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, listOfNews[position].link)
            context.startActivity(intent)
        }
        DownloadImageTask(holder.image!!).execute(listOfNews[position].imagem)
    }

    override fun getItemCount(): Int = listOfNews.size
}