package com.moryzaky.chalkboard.presentation.persons.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moryzaky.chalkboard.databinding.ItemPersonBinding
import com.moryzaky.chalkboard.domain.model.PersonDomainModel
import com.moryzaky.chalkboard.presentation.base.OnItemClickListener

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

class PersonsAdapter :
    PagingDataAdapter<PersonDomainModel, PersonsAdapter.PersonViewHolder>(DiffCallback()) {

    private var itemClickListener: OnItemClickListener<PersonDomainModel>? = null

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener<PersonDomainModel>) {
        this.itemClickListener = clickListener
    }

    inner class PersonViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: PersonDomainModel) {
            binding.apply {
                item = person
                itemLayout.setOnClickListener {
                    itemClickListener?.onItemClick(person)
                }
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<PersonDomainModel>() {
    override fun areItemsTheSame(oldItem: PersonDomainModel, newItem: PersonDomainModel): Boolean {
        return oldItem.firstName == newItem.firstName
                && oldItem.lastName == newItem.lastName
                && oldItem.birthday == newItem.birthday
    }

    override fun areContentsTheSame(
        oldItem: PersonDomainModel,
        newItem: PersonDomainModel
    ): Boolean {
        return oldItem == newItem
    }
}