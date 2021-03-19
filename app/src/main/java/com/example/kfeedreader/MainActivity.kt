package com.example.kfeedreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS

class MainActivity : AppCompatActivity(), Callback{

    private lateinit var listView : RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<ItemOfListNewsAdapter.ItemViewHolder>
    private val listItemOfNews = arrayListOf<ItemOfListNews>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = LinearLayoutManager(this)

        listView = findViewById<RecyclerView>(R.id.list_recycle_view)
        listView.layoutManager = layout

        adapter = ItemOfListNewsAdapter(listItemOfNews, this)
        listView.adapter = adapter

        PkRSS.with(this).load("https://rss.tecmundo.com.br/feed").callback(this).async()
    }

    override fun onLoaded(newArticles: MutableList<Article>?) {
        listItemOfNews.clear()
        newArticles?.mapTo(listItemOfNews){
            ItemOfListNews(it.title,it.author, it.date, it.source, it.enclosure.url)
        }
       
        adapter.notifyDataSetChanged()
    }

    override fun onLoadFailed() {
    }

    data class ItemOfListNews(val title: String, val author : String, val date: Long, val link : Uri, val imagem : String )

    override fun onPreload() {
    }


}