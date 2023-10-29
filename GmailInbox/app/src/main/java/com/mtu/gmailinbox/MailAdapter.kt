package com.mtu.gmailinbox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class MailAdapter(private val context: Context, private val res: Int, private val inboxList: List<Mail>) : ArrayAdapter<Mail>(context, res, inboxList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if(view == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.gmail_card, null)
        }
        val item = getItem(position)

        val imageView : ImageView? = view?.findViewById(R.id.iv_ava)
        val textViewSender: TextView? = view?.findViewById(R.id.gmail_sender)
        val textViewDate: TextView? = view?.findViewById(R.id.gmail_date)
        val textViewContent: TextView? = view?.findViewById(R.id.gmail_content)
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
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
        val mail: Mail = inboxList[position]
        val firstChar: Char = mail.senderName[0].lowercaseChar()
        imageView?.setImageResource(avaMap.getOrDefault(firstChar, R.drawable.icons8_a_100))
        textViewSender?.setText(mail.senderName)
        textViewDate?.setText(simpleDateFormat.format(mail.sendDate))
        textViewContent?.setText(mail.content)

        return view!!
    }
}