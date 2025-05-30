package com.example.helloworld

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.db.Activity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


public class ActivityAdapter(
    activities: MutableList<Activity>
) : ListAdapter<Activity, RecyclerView.ViewHolder>(ActivityDiffUtil()) {

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

    fun parseDate(dateStr: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return LocalDate.parse(dateStr, formatter)
    }

    fun addActivity(activity: Activity, view: View) {
        view.visibility = View.GONE
        mutableActivities.add(activity)
        notifyItemInserted(mutableActivities.size - 1)
    }

    fun removeActivity(position: Int, view: View) {
        if (position in mutableActivities.indices) {
            mutableActivities.removeAt(position)
            notifyItemRemoved(position)

            if (itemCount == 0)
                view.visibility = View.VISIBLE
        }
    }

    fun getActivity(position: Int) : Activity {
        return mutableActivities[position]
    }

    fun setItemClickListener(listener: (Int) -> Unit) {
        itemClickListener = listener
    }

    fun updateData(newList: List<Activity>, view: View) {
        val sortedActivities = newList.sortedBy { parseDate(it.date) }

        val groupedActivities = mutableListOf<Activity>()

        var previousDate: String? = null

        for (i in sortedActivities.size - 1 downTo 0 step 1) {
            if (sortedActivities[i].date != previousDate) {
                groupedActivities.add(
                    Activity(
                        id = null,
                        user = "",
                        date = sortedActivities[i].date,
                        distance = 0,
                        timeSpentHours = 0,
                        timeSpentMinutes = 0,
                        timeStart = "",
                        timeFinish = "",
                        activityType = "",
                        comment = null
                    )
                )
                previousDate = sortedActivities[i].date
            }
            groupedActivities.add(sortedActivities[i])
        }

        mutableActivities.clear()
        mutableActivities.addAll(groupedActivities)
        notifyDataSetChanged()

        if (itemCount == 0)
            view.visibility = View.VISIBLE
        else
            view.visibility = View.GONE
    }


    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvUser: TextView = itemView.findViewById(R.id.user)
        private val tvDistance: TextView = itemView.findViewById(R.id.distance)
        private val tvTimeSpent: TextView = itemView.findViewById(R.id.timeSpent)
        private val tvActivityType: TextView = itemView.findViewById(R.id.activityType)
        private val tvTimePassed: TextView = itemView.findViewById(R.id.timePassed)

        @SuppressLint("SetTextI18n")
        fun bind(activity: Activity) {
            if (activity.user != "me") tvUser.text = "@${activity.user}"

            if (activity.timeSpentHours == 0)
                tvTimeSpent.text = "${activity.timeSpentMinutes} мин"
            else
                tvTimeSpent.text = "${activity.timeSpentHours} ч ${activity.timeSpentMinutes} мин"

            tvDistance.text = "${activity.distance} м"
            tvActivityType.text = activity.activityType
            tvTimePassed.text = "Сейчас"
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
        fun bind(activity: Activity) {
            tvTime.text = activity.date
        }
    }

    class ActivityDiffUtil : DiffUtil.ItemCallback<Activity>() {
        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean =
            oldItem == newItem
    }
}
