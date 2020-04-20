package non.shahad.stayhomegallery.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UnlimitedTabHistoryStrategy
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.activity.BaseActivity
import non.shahad.stayhomegallery.ui.collections.CollectionsFragment
import non.shahad.stayhomegallery.ui.explore.ExploreFragment
import non.shahad.stayhomegallery.ui.favorite.FavoriteFragment
import non.shahad.stayhomegallery.ui.home.HomeFragment
import non.shahad.stayhomegallery.utils.constants.BottomNavigation
import non.shahad.stayhomegallery.utils.constants.Navigation
import java.lang.IllegalStateException

class MainActivity : BaseActivity(),FragNavController.RootFragmentListener
    ,FragNavController.TransactionListener {

    private val fragNavController = FragNavController(supportFragmentManager, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)
        wantToMakeStatusBarWhite(yes = true)
        InitBottomNavigation()
        InitNavigation()

        // Firstly instantiate current fragment
        fragNavController.initialize(BottomNavigation.HOME_INDEX,savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        fragNavController.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override val numberOfRootFragments: Int
        get() = Navigation.NUM_OF_ROOT_FRAGMENT

    /**
     * Pass all of the root fragments
     */
    override fun getRootFragment(index: Int): Fragment {
        return when(index){
            BottomNavigation.HOME_INDEX ->  HomeFragment.newInstance()
            BottomNavigation.EXPLORE_INDEX ->  ExploreFragment.newInstance()
            BottomNavigation.COLLECTIONS_INDEX ->  CollectionsFragment.newInstance()
            BottomNavigation.FAVORITE_INDEX -> FavoriteFragment.newInstance()

            else -> throw IllegalStateException("No such fragment")
        }
    }

    /**
     * If you want to add transaction options,
     * add options parameter to local switchTab function
     */
    private fun InitNavigation(){
        fragNavController.apply {
            transactionListener = this@MainActivity
            rootFragmentListener = this@MainActivity
            createEager = true
            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH

            navigationStrategy = UnlimitedTabHistoryStrategy(object : FragNavSwitchController {
                override fun switchTab(index: Int, transactionOptions: FragNavTransactionOptions?) {
                    switchTab(index)
                }
            })
        }
    }

    private fun InitBottomNavigation(){
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    fragNavController.switchTab(BottomNavigation.HOME_INDEX)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_explore -> {
                    fragNavController.switchTab(BottomNavigation.EXPLORE_INDEX)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_collections -> {
                    fragNavController.switchTab(BottomNavigation.COLLECTIONS_INDEX)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_favorite -> {
                    fragNavController.switchTab(BottomNavigation.FAVORITE_INDEX)
                    return@setOnNavigationItemSelectedListener true
                }

                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    /**
     * If you don't want to be looping around while backpress
     * check the current fragment is home or not
     * if it's home kill the activity
     */
    override fun onBackPressed() {
        if (fragNavController.popFragment().not() ){
            super.onBackPressed()
        }
    }

    /**
     * If you want to kill child fragment(like child of root)
     * or on backPressedOfChildFragment
     * call this function
     */
    fun clearStack(){
        this.fragNavController.clearStack()
    }


    fun switchTab(tabIndex : Int){
        this.fragNavController.switchTab(tabIndex)
    }

    /**
     * Call whenever fragment transaction occurs
     */
    override fun onFragmentTransaction(
        fragment: Fragment?,
        transactionType: FragNavController.TransactionType
    ) {
    }

    /**
     * On bottom Nav transaction occurs
     */
    override fun onTabTransaction(fragment: Fragment?, index: Int) {
    }
}
