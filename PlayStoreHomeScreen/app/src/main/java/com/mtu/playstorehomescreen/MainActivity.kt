package com.mtu.playstorehomescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rcView1: RecyclerView
    private lateinit var adapter1: AppCardAdapter
    private lateinit var rcView2: RecyclerView
    private lateinit var adapter2: AppCardAdapter
    private lateinit var rcView3: RecyclerView
    private lateinit var adapter3: AppCardAdapter
    private var appList = mutableListOf<AppInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // populate the list
        appList.add(AppInfo(appName = "App A", appIcon = R.drawable.icons8_a_100, appRate = "4.1⭐"))
        appList.add(AppInfo(appName = "App B", appIcon = R.drawable.icons8_b_100, appRate = "4.1⭐"))
        appList.add(AppInfo(appName = "App C", appIcon = R.drawable.icons8_c_100, appRate = "4.1⭐"))
        appList.add(AppInfo(appName = "App D", appIcon = R.drawable.icons8_d_100, appRate = "4.1⭐"))
        appList.add(AppInfo(appName = "App E", appIcon = R.drawable.icons8_e_100, appRate = "4.1⭐"))
        appList.add(AppInfo(appName = "App F", appIcon = R.drawable.icons8_f_100, appRate = "4.1⭐"))

        rcView1 = findViewById(R.id.rcv1)
        rcView1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter1 = AppCardAdapter(appList)
        rcView1.adapter = adapter1

        rcView2 = findViewById(R.id.rcv2)
        rcView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter2 = AppCardAdapter(appList)
        rcView2.adapter = adapter2

        rcView3 = findViewById(R.id.rcv3)
        rcView3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter3 = AppCardAdapter(appList)
        rcView3.adapter = adapter3
    }
}