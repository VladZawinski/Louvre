package non.shahad.stayhomegallery.common.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFatory : ViewModelProvider.Factory

    private fun inputMethodManager() : InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState, persistentState)
    }

    fun wantToMakeStatusBarWhite(yes : Boolean){
        if (yes){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

    fun showSoftKeyboard() {
        inputMethodManager().toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
    }

    fun hideSoftKeyboard() {
        inputMethodManager().toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0)
    }

    protected inline fun <reified VM : ViewModel>
            injectViewModels(): Lazy<VM> =  viewModels { viewModelFatory }

    protected inline fun <reified T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }

}