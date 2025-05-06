package com.example.helloworld

import ActivityInfo
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


public class ActivityAdapter(
    activities: List<ActivityInfo>
) : ListAdapter<ActivityInfo, RecyclerView.ViewHolder>(ActivityDiffUtil()) {

    companion object {
        private const val ITEM_TYPE_ACTIVITY = 1
        private const val ITEM_TYPE_DATE = 2
    }

    private val mutableActivities = activities.toMutableList()

    private var itemClickListener: (Int) -> Unit = {}

    override fun getItemViewType(position: Int): Int {
        if (mutableActivities[position].id == null) return ITEM_TYPE_DATE
        return ITEM_TYPE_ACTIVITY
    }

    override fun getItemCount(): Int {
        return mutableActivities.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_TYPE_ACTIVITY) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_activity, parent, false)
            return ActivityViewHolder(view)
        }
        else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_date, parent, false)
            return ActivityDateViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE_ACTIVITY)
            ((holder as ActivityViewHolder).bind(mutableActivities[position]))
        else
            ((holder as ActivityDateViewHolder).bind(mutableActivities[position]))
    }

    fun addActivity(activityInfo: ActivityInfo) {
        mutableActivities.add(activityInfo)
        notifyItemInserted(mutableActivities.size - 1)
    }

    fun removeActivity(position: Int) {
        if (position in mutableActivities.indices) {
            mutableActivities.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getActivity(position: Int) : ActivityInfo {
        return mutableActivities[position]
    }

    fun setActivity(activityInfos: List<ActivityInfo>) {
        submitList(activityInfos)
    }

    fun setItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }


    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvUser: TextView = itemView.findViewById(R.id.user)
        private val tvDistance: TextView = itemView.findViewById(R.id.distance)
        private val tvTimeSpent: TextView = itemView.findViewById(R.id.timeSpent)
        private val tvActivityType: TextView = itemView.findViewById(R.id.activityType)
        private val tvTimePassed: TextView = itemView.findViewById(R.id.timePassed)

        @SuppressLint("SetTextI18n")
        fun bind(activityInfo: ActivityInfo) {
            if (activityInfo.user != "myProfile") tvUser.text = "@${activityInfo.user}"
            tvDistance.text = "${activityInfo.distance} м"
            tvTimeSpent.text = "${activityInfo.timeSpentHours} часов ${activityInfo.timeSpentMinutes} минут"
            tvActivityType.text = activityInfo.activityType
            tvTimePassed.text = activityInfo.timePassed
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    itemClickListener.invoke(position)
            }
        }
    }

    inner class ActivityDateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTime: TextView = itemView.findViewById(R.id.date)

        @SuppressLint("SetTextI18n")
        fun bind(activityInfo: ActivityInfo) {
            tvTime.text = activityInfo.date
        }
    }

    class ActivityDiffUtil : DiffUtil.ItemCallback<ActivityInfo>() {
        override fun areItemsTheSame(oldItem: ActivityInfo, newItem: ActivityInfo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ActivityInfo, newItem: ActivityInfo): Boolean =
            oldItem == newItem
    }
}
