package com.mtu.gmailinbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import java.text.SimpleDateFormat

class InboxActivity : AppCompatActivity() {
    private var simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inbox_activity)

        val listView : ListView = findViewById(R.id.list_gmail)
        val tempMails: List<Mail> = listOf(
            Mail("A sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la A, xin gui ban mail nay"),
            Mail("D sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la D, xin gui ban mail nay"),
            Mail("E sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la E, xin gui ban mail nay"),
            Mail("F sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la F, xin gui ban mail nay"),
            Mail("G sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la G, xin gui ban mail nay"),
            Mail("W sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la W, xin gui ban mail nay"),
            Mail("A sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la A, xin gui ban mail nay"),
            Mail("D sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la D, xin gui ban mail nay"),
            Mail("E sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la E, xin gui ban mail nay"),
            Mail("F sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la F, xin gui ban mail nay"),
            Mail("G sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la G, xin gui ban mail nay"),
            Mail("W sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la W, xin gui ban mail nay"),
            Mail("A sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la A, xin gui ban mail nay"),
            Mail("D sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la D, xin gui ban mail nay"),
            Mail("E sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la E, xin gui ban mail nay"),
            Mail("F sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la F, xin gui ban mail nay"),
            Mail("G sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la G, xin gui ban mail nay"),
            Mail("W sender", simpleDateFormat.parse("29/10/2023"), "Xin chao ban, toi ten la W, xin gui ban mail nay"),
            )

        val adapter = MailAdapter(this, R.layout.gmail_card, tempMails)
        listView.adapter = adapter
    }
}