package com.udhipe.musemviewer.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.udhipe.musemviewer.R
import com.udhipe.musemviewer.data.collection.Collection
import com.udhipe.musemviewer.databinding.ItemCollectionBinding

class CollectionAdapter(private val onItemClickCallback: OnItemClickCallback) :
    RecyclerView.Adapter<CollectionAdapter.CollectionHolder>() {
    private val collectionList = mutableListOf<Collection>()

    interface OnItemClickCallback {
        fun onItemClick(objectNumber: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionHolder {
        val binding =
            ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionHolder(binding, onItemClickCallback)
    }

    fun updateData(newCollectionList: MutableList<Collection>) {
        collectionList.clear()
        collectionList.addAll(newCollectionList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CollectionHolder, position: Int) {
        holder.bindItem(collectionList[position])
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }

    class CollectionHolder(
        private val binding: ItemCollectionBinding,
        private val clickCallBack: OnItemClickCallback
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(collection: Collection) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(collection.webImage.url)
                    .centerCrop()
                    .placeholder(R.color.magenta)
                    .into(imgCollection)

                tvCollectionName.text = collection.title

                itemView.setOnClickListener {
                    clickCallBack.onItemClick(collection.objectNumber)
                }
            }
        }
    }

}