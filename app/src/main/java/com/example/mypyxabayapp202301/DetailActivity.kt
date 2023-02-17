package com.example.mypyxabayapp202301

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.Orientation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mypyxabayapp202301.Constantes.*

class DetailActivity : AppCompatActivity() {
    /** variables declaration **/
    //layout items
    var imageView: ImageView ?= null
    var tvCreator: TextView ?= null
    var tvLikes: TextView ?= null
    var tvViews: TextView ?= null
    var tvTags: TextView ?= null
    var tvDown: TextView ?= null
    var llayout: LinearLayout ?= null
    var llayoutH: LinearLayout ?= null

    /** Linking code to layouts UI **/
    fun initUI(){
        //linking our variables to the items present in the layout
        imageView = findViewById(R.id.iV_picture)
        tvCreator = findViewById(R.id.tV_authorName)
        tvLikes = findViewById(R.id.tV_likes)
        llayout = findViewById(R.id.lL_CardInfos)

        //create some items to be put in the layout
        tvViews = object : androidx.appcompat.widget.AppCompatTextView(this@DetailActivity){}
        tvDown = object : androidx.appcompat.widget.AppCompatTextView(this@DetailActivity){}


        //add items to the view
        llayout!!.addView(tvViews)
        llayout!!.addView(tvDown)

        //adjusting some display options
        tvViews!!.gravity = Gravity.CENTER
        tvDown!!.gravity = Gravity.CENTER

    }

    @SuppressLint("ResourceAsColor")
    fun initUITags(strings: List<String>){
        llayoutH = object : LinearLayout(this@DetailActivity){}
        llayout!!.addView(llayoutH)
        llayoutH!!.orientation = HORIZONTAL
        llayoutH!!.gravity = Gravity.CENTER

        for (string in strings){ //for each item found in the array we :
            val btnTag = object : androidx.appcompat.widget.AppCompatButton(this@DetailActivity){}//1 : create a Button object
            llayoutH!!.addView(btnTag) //2 : add it in our layout
            btnTag.gravity = Gravity.CENTER //3: set it into the center
            btnTag.text = string //4: set our item text value
            //btnTags!!.isClickable = false

            btnTag.setOnClickListener(){
                val intentS: Intent = object : Intent(this@DetailActivity, MainActivity::class.java){}
                        intentS.putExtra(EXTRA_SEARCH, btnTag.text.toString())
                startActivity(intentS)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //data recorvery
        var i: Intent = intent
        var imgUrl: String? = i.getStringExtra(EXTRA_URL)
        var author: String?= i.getStringExtra(EXTRA_CREATOR)
        var likes: String?= i.getStringExtra(EXTRA_LIKES)
        var downloads: String?= i.getStringExtra(EXTRA_DOWNLOADS)
        var views: String? = i.getStringExtra(EXTRA_VIEWS)
        var tagsFromJson: String? = i.getStringExtra(EXTRA_TAGS)

        //other vaariables
        var index = 0
        var tags = tagsFromJson!!.split(", ")

        //graph compos
        initUI()
        initUITags(tags)

        //TODO : create buttons en fonction du nombre de tags présents

        tvCreator!!.text = "By $author"
        tvLikes!!.text = "$likes personnes ont liké le post 🥰"
        tvViews!!.text = "Il y a eu $views personnes qui ont vu cette image 👀"
        tvDown!!.text = "Cette image a été téléchargée $downloads fois 💚"



        //manages images display / load errors
        val options = RequestOptions()
            .centerCrop()
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher_round)
        Glide.with(imageView!!)
            .load(imgUrl)
            .apply(options)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView!!)

    }
}