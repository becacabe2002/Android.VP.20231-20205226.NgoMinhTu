package com.mtu.formregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.mtu.formregister.adapter.UserCardAdapter
import com.mtu.formregister.model.User
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE = 123 // Use any unique request code
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserCardAdapter
    private var simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    private var userList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // populate the list
        userList.add(User(firstName = "Minh Tu 1", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002"), email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 2", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002"), email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 3", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002"), email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 4", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002"), email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 5", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002"), email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 6", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002"), email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 7", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002"), email = "ngominhtu@gmail.com", address = "abcded"))

        recyclerView = findViewById(R.id.rv_cards)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserCardAdapter(userList)
        recyclerView.adapter = adapter

        val btnAddUser: Button = findViewById(R.id.btn_addUser)
        btnAddUser.setOnClickListener {
            val intent = Intent(this, RegisterUser::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        // add predefined animation for recycleView
        recyclerView.itemAnimator = SlideInUpAnimator()

//        recyclerView.addOnItemTouchListener(object : OnItemTouchListener {
//            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
//                return true
//            }
//
//            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
//                val childView = rv.findChildViewUnder(e.x, e.y)
//                if(childView != null){
//                    val pos = rv.getChildAdapterPosition(childView)
//                    if(pos != RecyclerView.NO_POSITION){
//                        selectItem = userList[pos]
//                        Toast.makeText(rv.context, "${selectItem.firstName.toString()} is selected", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//
//            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//                TODO("Not yet implemented")
//            }
//        })


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            val newUser = data?.let {
                User(
                    data.getStringExtra("newUserFirstname").toString(),
                    data.getStringExtra("newUserLastname").toString(),
                    it.getBooleanExtra("newUserGender", true),
                    simpleDateFormat.parse(data.getStringExtra("newUserDob").toString())!!,
                    data.getStringExtra("newUserAddress").toString(),
                    data.getStringExtra("newUserEmail").toString())
            }
            if (newUser != null){
                userList.add(newUser)
                adapter.notifyItemInserted(userList.size - 1)
            }
        }
    }


}