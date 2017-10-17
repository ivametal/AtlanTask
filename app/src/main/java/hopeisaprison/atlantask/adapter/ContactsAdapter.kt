package hopeisaprison.atlantask.adapter

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import hopeisaprison.atlantask.R
import hopeisaprison.atlantask.model.Contact

/**
 * Created by hopeisaprison on 10/17/17.
 */
class ContactsAdapter(private val mContext: Context) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    private val contactsList = ArrayList<Contact>()

    init {
        val cursor = mContext.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.CONTACT_ID),
                null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
        while (cursor.moveToNext()) {
            val contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
                    cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)))
            val avatarUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY)
            val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            contactsList.add(Contact(name, avatarUri))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.name?.text = contactsList[position].name

        Picasso.with(mContext)
                .load(contactsList[position].avatarUri)
                .error(R.drawable.ic_contact_default)
                .into(holder?.avatar)


    }

    override fun getItemCount() = contactsList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent?.context).inflate(R.layout.cardview_contact, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val avatar = itemView?.findViewById<CircleImageView>(R.id.cardview_avatar)
        val name = itemView?.findViewById<TextView>(R.id.cardview_name)
    }

}