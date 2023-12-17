package com.mtu.filemanager.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtu.filemanager.R
import com.mtu.filemanager.databinding.FileItemBinding
import java.io.File

class FileAdapter(var files: Array<File>, var listener: OnItemClickListener) : RecyclerView.Adapter<FileAdapter.ViewHolder>() {
    private var filePos: Int = -1
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnLongClickListener{
        init {
            view.setOnLongClickListener(this)
        }
        val fileName: TextView = view.findViewById(R.id.fileName)
        override fun onLongClick(v: View?): Boolean {
            filePos = adapterPosition
            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.file_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return files.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val file = files[position]
        holder.fileName.text = file.name
        holder.itemView.setOnClickListener {
            listener.onItemClick(file)
        }
    }

    fun getSelectedFile(): File{
        return files[filePos]
    }
    // interface to handle item click
    interface OnItemClickListener{
        fun onItemClick(file: File)
    }
}