package hopeisaprison.atlantask

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hopeisaprison.atlantask.adapter.CardsAdapter
import java.security.Permission
import java.util.jar.Manifest

/**
 * Created by hopeisaprison on 10/17/17.
 */
class CardsFragment : Fragment() {

    private var mRecyclerView : RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_cards, container, false)
        mRecyclerView = view?.findViewById(R.id.recycler_cards)
        return view
    }

    override fun onResume() {
        super.onResume()
        mRecyclerView?.layoutManager = LinearLayoutManager(context)
        mRecyclerView?.adapter = CardsAdapter(context)
    }
}