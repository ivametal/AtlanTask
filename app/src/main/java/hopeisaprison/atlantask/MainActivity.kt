package hopeisaprison.atlantask

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavView = findViewById(R.id.navigation) as BottomNavigationView

        val cardsFragment = CardsFragment()
        val contactsFragment = ContactsFragment()

        bottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_cards -> {
                    val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cards)
                    if (fragment===null) {
                        val transaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.content, cardsFragment)
                        transaction.commit()
                    }
                    true
                }
                R.id.navigation_contacts -> {
                    val fragment = supportFragmentManager.findFragmentById(R.id.fragment_contacts)
                    if (fragment===null) {
                        val transaction = supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.content, contactsFragment)
                        transaction.commit()
                    }
                    else
                        fragment.onResume()
                    true
                }
                else -> false
            }
        }

        //Show cards when application start
        supportFragmentManager.beginTransaction().replace(R.id.content,cardsFragment).commit()
    }

}
