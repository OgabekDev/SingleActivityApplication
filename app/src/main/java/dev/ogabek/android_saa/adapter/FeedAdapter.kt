package dev.ogabek.android_saa.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ogabek.android_saa.R
import dev.ogabek.android_saa.model.Image
import java.io.ByteArrayOutputStream


class FeedAdapter(private val feeds: ArrayList<Image>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_feed, parent, false)

        return HomeFeedViewHolder(view)
    }

    class HomeFeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item: LinearLayout = view.findViewById(R.id.item_home_feed)
        val image: ImageView = view.findViewById(R.id.iv_home_feed)
        val likes: TextView = view.findViewById(R.id.tv_home_feed_likes)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = feeds[position]

        if (holder is HomeFeedViewHolder) {
            holder.apply {
                Glide.with(image).load(feed.urls.thumb)
                    .placeholder(ColorDrawable(Color.parseColor(feed.color))).into(image)
                likes.text = feed.likes.toString()
                item.setOnClickListener {

                }
            }
        }

    }

    fun update(items: ArrayList<Image>) {
        feeds.addAll(items)
        notifyDataSetChanged()
    }

    companion object {
        fun getBitmapImage(image: ImageView): ByteArray {
            val draw: BitmapDrawable = image.drawable as BitmapDrawable
            val bitmap: Bitmap = draw.bitmap
            val steam = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, steam)
            return steam.toByteArray()
        }
    }

    override fun getItemCount() = feeds.size
}