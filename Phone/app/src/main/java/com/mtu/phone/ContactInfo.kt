package com.mtu.phone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView

class ContactInfo : AppCompatActivity() {
    val avaMap = mapOf(
        'a' to R.drawable.icons8_a_100,
        'b' to R.drawable.icons8_b_100,
        'c' to R.drawable.icons8_c_100,
        'd' to R.drawable.icons8_d_100,
        'e' to R.drawable.icons8_e_100,
        'f' to R.drawable.icons8_f_100,
        'g' to R.drawable.icons8_g_100,
        'h' to R.drawable.icons8_h_100,
        'i' to R.drawable.icons8_i_100,
        'j' to R.drawable.icons8_j_100,
        'k' to R.drawable.icons8_k_100,
        'l' to R.drawable.icons8_l_100,
        'm' to R.drawable.icons8_m_100,
        'n' to R.drawable.icons8_n_100,
        'o' to R.drawable.icons8_o_100,
        'p' to R.drawable.icons8_p_100,
        'q' to R.drawable.icons8_q_100,
        'r' to R.drawable.icons8_r_100,
        's' to R.drawable.icons8_s_100,
        't' to R.drawable.icons8_t_100,
        'u' to R.drawable.icons8_u_100,
        'v' to R.drawable.icons8_v_100,
        'w' to R.drawable.icons8_w_100,
        'x' to R.drawable.icons8_x_100,
        'y' to R.drawable.icons8_y_100,
        'z' to R.drawable.icons8_z_100
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)
        val intent = intent
        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")
        val email = intent.getStringExtra("email")

        val detailAva: ShapeableImageView = findViewById(R.id.detail_avatar)
        val detailId: TextView = findViewById(R.id.detail_id)
        val detailName: TextView = findViewById(R.id.detail_name)
        val detailNumber: TextView = findViewById(R.id.detail_number)
        val detailEmail: TextView = findViewById(R.id.detail_email)

        val firstChar: Char = name!![0].lowercaseChar()
        detailAva.setImageResource(avaMap.getOrDefault(firstChar, R.drawable.icons8_a_100))

        detailId.text = id.toString()
        detailName.text = name
        detailNumber.text = number
        detailEmail.text = email
    }
}