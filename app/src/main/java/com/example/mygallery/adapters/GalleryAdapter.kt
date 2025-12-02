package com.example.mygallery.adapters

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.mygallery.R

data class GalleryItem(val imageResId: Int, val title: String)

class GalleryAdapter(private val galleryItems: List<GalleryItem>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = galleryItems[position]
        holder.cardImage.setImageResource(item.imageResId)
        holder.cardTitle.text = item.title

        holder.itemView.setOnClickListener { view ->
            showPopupMenu(view, item)
        }

        holder.itemView.setOnLongClickListener { view ->
            val activity = view.context as AppCompatActivity
            activity.startSupportActionMode(object : androidx.appcompat.view.ActionMode.Callback {
                override fun onCreateActionMode(mode: androidx.appcompat.view.ActionMode, menu: Menu): Boolean {
                    mode.menuInflater.inflate(R.menu.gallery_action_mode_menu, menu)
                    mode.title = "Options"
                    return true
                }

                override fun onPrepareActionMode(mode: androidx.appcompat.view.ActionMode, menu: Menu): Boolean {
                    return false
                }

                override fun onActionItemClicked(mode: androidx.appcompat.view.ActionMode, menuItem: MenuItem): Boolean {
                    handleMenuClick(menuItem, item, view)
                    mode.finish()
                    return true
                }

                override fun onDestroyActionMode(mode: androidx.appcompat.view.ActionMode) {}
            })
            true
        }
    }

    private fun showPopupMenu(view: View, item: GalleryItem) {
        val popup = PopupMenu(view.context, view)
        popup.menuInflater.inflate(R.menu.gallery_context_menu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            handleMenuClick(menuItem, item, view)
            true
        }
        popup.show()
    }

    private fun handleMenuClick(menuItem: MenuItem, item: GalleryItem, view: View) {
        when (menuItem.itemId) {
            R.id.action_edit -> {
                Toast.makeText(view.context, "Editing ${item.title}", Toast.LENGTH_SHORT).show()
            }
            R.id.action_delete -> {
                Toast.makeText(view.context, "Deleting ${item.title}", Toast.LENGTH_SHORT).show()
            }
            R.id.action_share -> {
                Toast.makeText(view.context, "Sharing ${item.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return galleryItems.size
    }

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImage: ImageView = itemView.findViewById(R.id.card_image)
        val cardTitle: TextView = itemView.findViewById(R.id.card_title)
        val acceptButton: Button = itemView.findViewById(R.id.accept_button)
        val cancelButton: Button = itemView.findViewById(R.id.cancel_button)
    }
}