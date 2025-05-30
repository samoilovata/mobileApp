package com.example.helloworld

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TypeAdapter (
    activities: List<String>
) : ListAdapter<String, RecyclerView.ViewHolder>(TypeDiffUtil()) {

    private var _selected = 0;

    companion object {
        private const val ITEM_TYPE = 1
        private const val ITEM_TYPE_SELECTED = 2
    }

    private val mutableActivities = activities.toMutableList()

    private var itemClickListener: (Int) -> Unit = {}

    override fun getItemViewType(position: Int): Int {
        if (position == _selected) return ITEM_TYPE_SELECTED
        return ITEM_TYPE
    }

    override fun getItemCount(): Int {
        return mutableActivities.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_TYPE) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_type, parent, false)
            return TypeViewHolder(view)
        }
        else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_type_selected, parent, false)
            return TypeSelectedViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE)
            ((holder as TypeViewHolder).bind(mutableActivities[position]))
        else
            ((holder as TypeSelectedViewHolder).bind(mutableActivities[position]))
    }

    fun updateType(string: String) {
        if (string in mutableActivities) {
            val temp = _selected
            _selected = mutableActivities.indexOf(string)
            notifyItemChanged(_selected)
            notifyItemChanged(temp)
        }
    }

    fun getType(position: Int) : String {
        return mutableActivities[position]
    }

    fun setItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }


    inner class TypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvType: TextView = itemView.findViewById(R.id.type)

        fun bind(string: String) {
            tvType.text = string
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    itemClickListener.invoke(position)
            }
        }
    }

    inner class TypeSelectedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvType: TextView = itemView.findViewById(R.id.type)

        fun bind(string: String) {
            tvType.text = string
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    itemClickListener.invoke(position)
            }
        }
    }

    class TypeDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }
}