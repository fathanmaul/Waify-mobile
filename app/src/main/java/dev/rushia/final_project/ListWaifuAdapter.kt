package dev.rushia.final_project

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class ListWaifuAdapter(private val listWaifu: ArrayList<Waifu>) :
    RecyclerView.Adapter<ListWaifuAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_waifu, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listWaifu.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, animeName, description, vote, photo) = listWaifu[position]
        Glide.with(holder.itemView.context).load(photo).into(holder.imgPhoto)
//        holder.tvName.text = "#".also {  }
        "#${position+1} $name - $vote Votes".also { holder.tvName.text = it }
        holder.tvDescription.text = description
        holder.tvAnime.text = animeName
        holder.itemView.setOnClickListener {
            Intent(holder.itemView.context, DetailWaifuActivity::class.java).also {
                it.putExtra("key_waifu", listWaifu[position])
                holder.itemView.context.startActivity(it)
            }
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvAnime: TextView = itemView.findViewById(R.id.tv_item_anime)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val imgPhoto: CircleImageView = itemView.findViewById(R.id.img_item_photo)
    }
}