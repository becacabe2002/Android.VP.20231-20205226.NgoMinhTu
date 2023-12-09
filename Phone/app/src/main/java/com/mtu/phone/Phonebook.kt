package com.mtu.phone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Phonebook : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE = 123
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactCardAdapter
    private var contactList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phonebook)

        contactList.add(Contact(id = 12223, name = "A contact", number = "123456789", email = "sample@gmail.com"))
        contactList.add(Contact(id = 122321, name = "B contact", number = "123456789", email = "sample@gmail.com"))
        contactList.add(Contact(id = 12323, name = "C contact", number = "123456789", email = "sample@gmail.com"))
        contactList.add(Contact(id = 12313, name = "D contact", number = "123456789", email = "sample@gmail.com"))
        contactList.add(Contact(id = 12323, name = "E contact", number = "123456789", email = "sample@gmail.com"))
        contactList.add(Contact(id = 123123, name = "F contact", number = "123456789", email = "sample@gmail.com"))
        contactList.add(Contact(id = 123213, name = "G contact", number = "123456789", email = "sample@gmail.com"))

        recyclerView = findViewById(R.id.contact_rcv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ContactCardAdapter(contactList
//            object: ContactCardAdapter.ItemClickListener{
//            override fun itemClicked(position: Int) {
//                val contact: Contact = contactList[position]
//                val intent = Intent(parent, ContactInfo::class.java)
//                intent.putExtra("ID", contact.id)
//                intent.putExtra("name", contact.name)
//                intent.putExtra("number", contact.name)
//                intent.putExtra("email", contact.email)
//                startActivity(intent)
//            }
//        }
        )

        recyclerView.adapter = adapter
    }
}