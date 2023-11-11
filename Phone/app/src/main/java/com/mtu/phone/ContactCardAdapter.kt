package com.mtu.phone

import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class ContactCardAdapter (private val contactList: List<Contact>,
//                          val listener: ItemClickListener? = null
) : RecyclerView.Adapter<ContactCardAdapter.ContactCardViewHolder>(){

    inner class ContactCardViewHolder(itemView: View,
//                                      val listener: ItemClickListener?
    ): RecyclerView.ViewHolder(itemView){
        private val tv_contactName: TextView = itemView.findViewById(R.id.contact_tv)
        private val img_contactAva: ShapeableImageView = itemView.findViewById(R.id.contact_img)

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

        fun bind(contact: Contact){
            tv_contactName.text = contact.name
            val firstChar: Char = contact.name[0].lowercaseChar()
            img_contactAva.setImageResource(avaMap.getOrDefault(firstChar, R.drawable.icons8_a_100))

//            itemView.setOnClickListener{
//                listener?.itemClicked(adapterPosition)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_card, parent, false)
        return ContactCardViewHolder(view,
//            listener
        )
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactCardViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)

        holder.itemView.setOnClickListener{
            val selectedContact: Contact = contactList[position]
            val intent = Intent(it.context, ContactInfo::class.java)
            intent.putExtra("id", selectedContact.id.toString())
            intent.putExtra("name", selectedContact.name)
            intent.putExtra("number", selectedContact.number)
            intent.putExtra("email", selectedContact.email)
            it.context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener{ v ->
            val popup = PopupMenu(v.context, v)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.popup_menu, popup.menu)
            popup.show()
            true
        }
    }

//    interface ItemClickListener{
//        fun itemClicked(position: Int)
//    }
}