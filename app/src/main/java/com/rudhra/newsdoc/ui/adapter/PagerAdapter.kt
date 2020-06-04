package com.rudhra.newsdoc.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rudhra.newsdoc.R
import com.rudhra.newsdoc.ui.news.NewsFragment
import java.lang.ref.WeakReference
import java.util.*

class PagerAdapter(context: Context, fa: FragmentActivity, private val count: Int) :
    FragmentStateAdapter(fa) {

    private val context: WeakReference<Context> = WeakReference(context)

    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment {
        val category: String?

        when (position) {
            0 -> category =
                context.get()!!.getString(R.string.cat_business).toLowerCase(Locale.getDefault())
            1 -> category = context.get()!!.getString(R.string.cat_entertainment)
                .toLowerCase(Locale.getDefault())
            2 -> category =
                context.get()!!.getString(R.string.cat_health).toLowerCase(Locale.getDefault())
            3 -> category =
                context.get()!!.getString(R.string.cat_science).toLowerCase(Locale.getDefault())
            4 -> category =
                context.get()!!.getString(R.string.cat_sports).toLowerCase(Locale.getDefault())
            5 -> category =
                context.get()!!.getString(R.string.cat_technology).toLowerCase(Locale.getDefault())
            else -> category =
                context.get()!!.getString(R.string.cat_business).toLowerCase(Locale.getDefault())
        }
        return NewsFragment(category)
    }

}
