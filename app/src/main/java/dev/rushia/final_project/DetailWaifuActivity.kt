package dev.rushia.final_project

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import dev.rushia.final_project.databinding.ActivityDetailWaifuBinding
import dev.rushia.final_project.databinding.ActivityMainBinding

class DetailWaifuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailWaifuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailWaifuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibBack.setOnClickListener{
            finish()
        }

        val dataWaifu = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_waifu", Waifu::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_waifu")!!
        }

        binding.tvName.text = dataWaifu.name
        binding.tvAnime.text = dataWaifu.animeName
        binding.tvDescription.text = dataWaifu.description
        "${dataWaifu.vote} Votes".also {
            binding.tvVote.text = it
        }
        Glide.with(this).load(dataWaifu.photo).into(binding.ivAnime)

        binding.ibShare.setOnClickListener{
            val shareIntent: Intent = Intent(Intent.ACTION_SEND).putExtra(Intent.EXTRA_TEXT, "Name : ${dataWaifu.name}\nAnime : ${dataWaifu.animeName}\n" +
                    "Total Vote : ${dataWaifu.vote}")
            shareIntent.type = "text/plain"
            val chooserIntent = Intent.createChooser(shareIntent, null)
            startActivity(chooserIntent)
        }
    }
}