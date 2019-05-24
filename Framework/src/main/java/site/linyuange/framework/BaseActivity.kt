package site.linyuange.framework

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * Author: BaHuang
 * Date: 2019/5/24 12:36
 */
abstract class BaseActivity : AppCompatActivity() {

    companion object {
        const val NO_LAYOUT_RES: Int = 0;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preHandleSavedInstanceState()
        handleSavedInstanceState(savedInstanceState)
        val layoutRes = getLayoutRes()
        if (layoutRes > NO_LAYOUT_RES) {
            initContentView(layoutRes)
        }
    }

    protected open fun preHandleSavedInstanceState() {}

    open fun handleSavedInstanceState(bundle: Bundle?) {}

    open fun initContentView(@LayoutRes layoutRes: Int) {
        setContentView(layoutRes)
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

}