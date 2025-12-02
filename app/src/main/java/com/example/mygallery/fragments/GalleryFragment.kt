package com.example.mygallery.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mygallery.R
import com.example.mygallery.adapters.GalleryAdapter
import com.example.mygallery.adapters.GalleryItem

class GalleryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.gallery_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        val galleryItems = createGalleryItems()
        recyclerView.adapter = GalleryAdapter(galleryItems)
    }

    private fun createGalleryItems(): List<GalleryItem> {
        val items = mutableListOf<GalleryItem>()
        val imageResources = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9
        )

        for (i in imageResources.indices) {
            items.add(GalleryItem(imageResources[i], "Card ${i + 1}"))
        }

        return items
    }
}