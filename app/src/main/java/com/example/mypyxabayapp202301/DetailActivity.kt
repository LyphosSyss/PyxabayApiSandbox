package com.example.mypyxabayapp202301

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mypyxabayapp202301.Constantes.*

class DetailActivity : AppCompatActivity() {

    var imageView: ImageView ?= null
    var tvCreator: TextView ?= null
    var tvLikes: TextView ?= null
    private var tvViews: TextView ?= null
    var tvTags: TextView ?= null
    var tvDown: TextView ?= null
    var llayout: LinearLayout ?= null

    var tagList : MutableList<String> = ArrayList()

    fun tagsSplitting(string: String){

        var arrayOfTags = string.split(", ")
        var i = 0;
        for(tag in arrayOfTags){
            tagList[i] = tag
            i ++
        }

    }
    fun initUI(){
        imageView = findViewById(R.id.iV_picture)
        tvCreator = findViewById(R.id.tV_authorName)
        tvLikes = findViewById(R.id.tV_likes)

        tvViews = object : androidx.appcompat.widget.AppCompatTextView(this@DetailActivity){}
        tvDown = object : androidx.appcompat.widget.AppCompatTextView(this@DetailActivity){}
        tvTags = object : androidx.appcompat.widget.AppCompatTextView(this@DetailActivity){}

        llayout = findViewById(R.id.lL_CardInfos)

        //add items to the view
        llayout!!.addView(tvViews)
        llayout!!.addView(tvDown)
        llayout!!.addView(tvTags)

        tvViews!!.gravity = Gravity.CENTER
        tvDown!!.gravity = Gravity.CENTER
        tvTags!!.gravity = Gravity.CENTER
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //graph compos
        initUI()

        //recup des data
        var i: Intent = intent
        var imgUrl: String? = i.getStringExtra(EXTRA_URL)
        var author: String?= i.getStringExtra(EXTRA_CREATOR)
        var likes: String?= i.getStringExtra(EXTRA_LIKES)
        var downloads: String?= i.getStringExtra(EXTRA_DOWNLOADS)
        var views: String? = i.getStringExtra(EXTRA_VIEWS)
        var tags: String? = i.getStringExtra(EXTRA_TAGS)

        //TODO : create buttons en fonction du nombre de tags présents

        tvCreator!!.text = "By $author"
        tvLikes!!.text = "$likes personnes ont liké le post 🥰"
        tvViews!!.text = "Il y a eu $views personnes qui ont vu cette image 👀"
        tvDown!!.text = "Cette image a été téléchargée $downloads fois 💚"
        tvTags!!.text = "$tags"

        //var holder: Context = this@DetailActivity
        //manages images display / load errors
        val options = RequestOptions()
            .centerCrop()
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher_round)
        /**Context context = holder.ivImageView.getContext(); //if error
        Glide.with(context)
        .load(imageUrl)
        .into(context);**/

        /**Context context = holder.ivImageView.getContext(); //if error
         * Glide.with(context)
         * .load(imageUrl)
         * .into(context); */
        Glide.with(imageView!!)
            .load(imgUrl)
            .apply(options)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView!!)

    }
}