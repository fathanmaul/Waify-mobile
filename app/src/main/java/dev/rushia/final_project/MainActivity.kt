package dev.rushia.final_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(){
    private lateinit var rvWaifus: RecyclerView
    private val list = ArrayList<Waifu>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvWaifus = findViewById(R.id.rv_waifu)
        rvWaifus.setHasFixedSize(true)

        list.addAll(getListWaifu())
        showRecyclerView()

        val btAbout: ImageButton = findViewById(R.id.ib_about)
        btAbout.setOnClickListener {
            val toAboutActivity = Intent(this, AboutActivity::class.java)
            startActivity(toAboutActivity)
        }

    }

    private fun getListWaifu(): ArrayList<Waifu> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataAnimeName = resources.getStringArray(R.array.data_anime)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataVote = resources.getStringArray(R.array.data_vote)
        val listWaifu = ArrayList<Waifu>()
        for (i in dataName.indices) {
            val waifu =
                Waifu(dataName[i], dataAnimeName[i], dataDescription[i], dataVote[i], dataPhoto[i])
            listWaifu.add(waifu)
        }
        return listWaifu
    }

    private fun showRecyclerView() {
        rvWaifus.layoutManager = LinearLayoutManager(this)
        val listWaifuAdapter = ListWaifuAdapter(list)
        rvWaifus.adapter = listWaifuAdapter
    }

}