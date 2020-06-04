package com.rudhra.newsdoc

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.rudhra.newsdoc.databinding.ActivityMainBinding
import com.rudhra.newsdoc.ui.adapter.PagerAdapter


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var tabTitleList: List<String>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflate layout using data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.appBarLayout.toolbar)

        setupDrawerLayout(binding)

        prepareTabTitles()

        setUpTabLayout(
            binding.appBarLayout.contentMainLayout.viewpager,
            binding.appBarLayout.slidingTabs, tabTitleList
        )

        // must for navigation listener to work
        binding.navView.bringToFront()
        //handling navigation from navigation dravwer
        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun prepareTabTitles() {

        tabTitleList = listOf(
            getString(R.string.cat_business),
            getString(R.string.cat_entertainment),
            getString(R.string.cat_health),
            getString(R.string.cat_science),
            getString(R.string.cat_sports),
            getString(R.string.cat_technology)
        )
    }

    private fun setupDrawerLayout(binding: ActivityMainBinding) {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarLayout.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setUpTabLayout(viewPager: ViewPager2, tabLayout: TabLayout, list: List<String>) {

        viewPager.adapter = PagerAdapter(this, this, list.size)
        // attaching tab mediator
        TabLayoutMediator(tabLayout, viewPager,
            TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                tab.text = list[position]
            }
        ).attach()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position: Int = when (item.itemId) {
            R.id.nav_business -> 0
            R.id.nav_entertainment -> 1
            R.id.nav_health -> 2
            R.id.nav_science -> 3
            R.id.nav_sports -> 4
            R.id.nav_technology -> 5
            else -> 0
        }
        binding.appBarLayout.slidingTabs.setScrollPosition(position, 0f, true)
        binding.appBarLayout.contentMainLayout.viewpager.currentItem = position
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
