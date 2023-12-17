package com.mtu.filemanager

import android.content.pm.PackageManager
import android.Manifest
import android.content.Intent

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.mtu.filemanager.databinding.ActivityMainBinding
import com.mtu.filemanager.ui.FileAdapter

import java.io.File
import java.util.Stack

class MainActivity : AppCompatActivity(), FileAdapter.OnItemClickListener {
    companion object{
        private const val PERMISSION_REQUEST_CODE = 101
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var fileAdapter: FileAdapter
    private val directories = Stack<File>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.listView
        recyclerView.layoutManager = LinearLayoutManager(this)
        fileAdapter = FileAdapter(emptyArray(), this)
        recyclerView.adapter = fileAdapter // change it later
        registerForContextMenu(recyclerView)
        directories.push(Environment.getExternalStorageDirectory())
        // checkPermission before load file
        checkPermission()
    }

    override fun onItemClick(file: File) {
        if(file.isDirectory){
            directories.push(file)
            loadFiles(directories.peek())
        } else{
            openFile(file)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.create_new_folder -> {
                createNewFolder()
                true
            }
            R.id.create_new_file -> {
                createNewFile()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_REQUEST_CODE -> {// if requestCode == 101
                if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    loadFiles(Environment.getExternalStorageDirectory())
                } else {
                    Log.w("Permission", "User did not grant permission.")
                }
                return
            }
            else -> {
                return
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if(directories.size>1){
            directories.pop()
            loadFiles(directories.peek())
        }
        super.onBackPressed()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val file = fileAdapter.getSelectedFile()
        if(file.isDirectory){
            menuInflater.inflate(R.menu.direct_context_menu, menu)
        } else {
            menuInflater.inflate(R.menu.item_menu, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val file = fileAdapter.getSelectedFile()
        return when (item.itemId){
            R.id.renameFile -> {
                renameChooseFile(file)
                true
            }
            R.id.deleteFile -> {
                deleteChooseFile(file)
                true
            }
            R.id.copyFile -> {
                copyChooseFile(file)
                true
            }
            R.id.deleteDirectory -> {
                AlertDialog.Builder(this)
                    .setTitle("Confirm delete")
                    .setMessage("Are you sure you want to delete this directory?")
                    .setPositiveButton("Yes") {_, _ -> deleteChooseDirectory(file)}
                    .setNegativeButton("Cancel", null).show()
                true
            }
            R.id.renameDirectory ->{
                renameChooseDirectory(file)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
        } else {
            directories.push(Environment.getExternalStorageDirectory())
            loadFiles(Environment.getExternalStorageDirectory())
        }
    }

    private fun loadFiles(currentDirectory: File) {
        val path = currentDirectory
        val directory = File(path.absolutePath)
        val files = directory.listFiles()
        if(files != null && files.isNotEmpty()){
            fileAdapter.files = files
            fileAdapter.notifyDataSetChanged()
        } else{
            AlertDialog.Builder(this)
                .setTitle("Noti")
                .setMessage("SD card is empty.")
                .setPositiveButton("OK"){ dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }

    private fun openFile(file: File){
        val uri = FileProvider.getUriForFile(
            this,
            "com.mtu.filemanager.fileprovider", file
        )

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = uri
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        startActivity(Intent.createChooser(intent, "Open file with:"))
    }

    private fun deleteChooseFile(file: File){
        if(file.delete()){
            Toast.makeText(this, "File deleted", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "Failed to delete file", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteChooseDirectory(file: File) {
        if (file.deleteRecursively()) {
            Toast.makeText(this, "Directory deleted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to delete directory", Toast.LENGTH_SHORT).show()
        }

    }

    private fun renameChooseFile(file: File) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Rename File")
        val dialogLayout = inflater.inflate(R.layout.rename_dialog, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Rename") { _, _ ->
            val newName = editText.text.toString()
            val newFile = File(file.parent, newName)
            if (file.renameTo(newFile)) {
                Toast.makeText(this, "File renamed successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to rename file", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    private fun renameChooseDirectory(file: File) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Rename Directory")
        val dialogLayout = inflater.inflate(R.layout.rename_dialog, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Rename") { _, _ ->
            val newName = editText.text.toString()
            val newFile = File(file.parent, newName)
            if (file.renameTo(newFile)) {
                Toast.makeText(this, "Directory renamed successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to rename directory", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    private fun copyChooseFile(file: File) {
        val parentDir = file.parentFile
        val siblingDirs = parentDir.parentFile.listFiles { _, name ->
            val sibling = File(parentDir.parent, name)
            sibling.isDirectory && sibling.name != parentDir.name
        }
        val dirNames = siblingDirs.map { it.name }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle("Select Folder")
            .setItems(dirNames) { _, which ->
                val selectedDir = siblingDirs[which]
                val newFile = File(selectedDir, file.name)
                file.inputStream().use { input ->
                    newFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
                Toast.makeText(this, "File copied successfully", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun createNewFolder() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("New Folder")
        val dialogLayout = inflater.inflate(R.layout.rename_dialog, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Create") { _, _ ->
            val newFolderName = editText.text.toString()
            val newFolder = File(directories.peek(), newFolderName) // replace currentDirectory with the current directory
            if (newFolder.mkdir()) {
                Toast.makeText(this, "Folder created successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to create folder", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    private fun createNewFile() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("New Text File")
        val dialogLayout = inflater.inflate(R.layout.rename_dialog, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Create") { _, _ ->
            val newFileName = editText.text.toString()
            val newFile = File(directories.peek(), newFileName) // replace currentDirectory with the current directory
            if (newFile.createNewFile()) {
                Toast.makeText(this, "File created successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to create file", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

}