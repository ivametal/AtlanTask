package hopeisaprison.atlantask

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hopeisaprison.atlantask.adapter.ContactsAdapter

/**
 * Created by hopeisaprison on 10/17/17.
 */
class ContactsFragment : Fragment() {

    private val permissionReadContacts = 1231
    private var recyclerView : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_contacts, container, false)
        recyclerView = view?.findViewById(R.id.recycler_contacts)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        if (ContextCompat.
                checkSelfPermission(context, android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), permissionReadContacts)
        } else {
            recyclerView?.adapter =  ContactsAdapter(context)
        }

        return view
    }

    override fun onResume() {
        super.onResume()



    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionReadContacts && grantResults[0] == PackageManager.PERMISSION_GRANTED)
           recyclerView?.adapter =  ContactsAdapter(context)
        else {
            Toast.makeText(context, "Give me permission, plz :c", Toast.LENGTH_SHORT).show()
        }
    }
}