package com.mtu.playstorehomescreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppCardAdapter (private val appList: List<AppInfo>): RecyclerView.Adapter<AppCardAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
        private val appName: TextView = itemView.findViewById(R.id.app_label)
        private val appRate: TextView = itemView.findViewById(R.id.app_rate)

        fun bind(app: AppInfo){
            appIcon.setImageResource(app.appIcon)
            appName.text = app.appName
            appRate.text = app.appRate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app: AppInfo = appList[position]
        holder.bind(app)
    }
}