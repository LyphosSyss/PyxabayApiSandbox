package com.example.mypyxabayapp202301

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    /** GLOBALS **/

    private var recyclerView: RecyclerView ?= null
    private var adapterRecycler: AdapterRecycler ?= null
    private var itemArrayList : ArrayList<ModelItem> ?= null
    private var requestQueue: RequestQueue ?= null
    private var search: String ?= null

    private var btn_Search: Button ?= null
    private var et_search: EditText ?= null


    fun init() {
        //Design Java link
        recyclerView = findViewById(R.id.rV_itemsList)
        btn_Search = findViewById(R.id.btn_search)
        et_search = findViewById(R.id.eT_search)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        itemArrayList = ArrayList()
        requestQueue = Volley.newRequestQueue(this)
    }

    private fun parseJSON() {
        search = et_search!!.text.toString()
        val pixaKey = "33662216-dc2a74f07d05ac9626e395f4b"
        //https://pixabay.com/api/?key=33662216-dc2a74f07d05ac9626e395f4b&q=yellow+flowers&image_type=photo
        val urlJSONFile = ("https://pixabay.com/api/"
                + "?key=" + pixaKey
                + "&q=" + search
                + "&image_type=photo"
                + "&orientation=horizontal"
                + "&pretty=true")
        val request = JsonObjectRequest(
            Request.Method.POST, urlJSONFile, null,
            { response ->
                try {
                    // On récupère le tableau de données JSON à partir de notre objet JsonObjectRequest
                    // dans un try and catch ajouter en auto en corrigeant l'erreur
                    val jsonArray = response.getJSONArray("hits")

                    // On récupère dans un premier temps toutes les données présentent dans le Array avec une boucle for
                    for (i in 0 until jsonArray.length()) {
                        val hit = jsonArray.getJSONObject(i)
                        // Puis on sélectionne celles dn on à besoin soit user - likes - webformatURL
                        val creator = hit.getString("user")
                        val likes = hit.getInt("likes")
                        val imageUrl = hit.getString("webformatURL")

                        // On ajoute les données à notre tableau en utilisant son model
                        itemArrayList!!.add(ModelItem(imageUrl, creator, likes))
                    }
                    // En dehors du try on adapte le tableau de données
                    adapterRecycler = AdapterRecycler(
                        this@MainActivity,
                        itemArrayList
                    ) // Noter MainActivity.this car nous sommes dans une classe interne

                    // Puis on lie l'adpter au Recycler
                    recyclerView!!.adapter = adapterRecycler
                    /** #10.3 On peut alors ajouter le listener  */
                    /** #10.3 On peut alors ajouter le listener  */
                    /** #10.3 On peut alors ajouter le listener  */
                    /** #10.3 On peut alors ajouter le listener  */
                    //adapterRecycler.setOnItemClickListener(MainActivity.this);
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error -> error.printStackTrace() }
        /** #9.1 On rempli la request avec les données récupérées  */
        requestQueue!!.add(request)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        btn_Search!!.setOnClickListener{
            itemArrayList!!.clear()
            parseJSON()
        }


    }
}