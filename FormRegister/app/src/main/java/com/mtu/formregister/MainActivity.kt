package com.mtu.formregister

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
        userList.add(User(firstName = "Minh Tu 1", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002")!!, email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 2", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002")!!, email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 3", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002")!!, email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 4", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002")!!, email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 5", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002")!!, email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 6", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002")!!, email = "ngominhtu@gmail.com", address = "abcded"))
        userList.add(User(firstName = "Minh Tu 7", lastName = "Ngo",sex = true, dob = simpleDateFormat.parse("29/12/2002")!!, email = "ngominhtu@gmail.com", address = "abcded"))

        recyclerView = findViewById(R.id.rv_cards)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserCardAdapter(userList, object: UserCardAdapter.ItemClickListener{
            override fun itemClicked(position: Int) {
                val selectItem: User = userList[position]
                Toast.makeText(recyclerView.context, "${selectItem.firstName.toString()} is selected", Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.adapter = adapter

        val btnAddUser: Button = findViewById(R.id.btn_addUser)
        btnAddUser.setOnClickListener {
            startRegisterFormForResult()
        }

        // add predefined animation for recycleView
        recyclerView.itemAnimator = SlideInUpAnimator()

    }
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            val newUser = User(
                data!!.getStringExtra("newUserFirstname").toString(),
                data.getStringExtra("newUserLastname").toString(),
                data.getBooleanExtra("newUserGender", true),
                simpleDateFormat.parse(data.getStringExtra("newUserDob").toString())!!,
                data.getStringExtra("newUserAddress").toString(),
                data.getStringExtra("newUserEmail").toString())
            userList.add(newUser)
            adapter.notifyItemInserted(userList.size - 1)
        }
    }

    private fun startRegisterFormForResult(){
        val intent = Intent(this, RegisterUser::class.java)
        resultLauncher.launch(intent)
    }
}