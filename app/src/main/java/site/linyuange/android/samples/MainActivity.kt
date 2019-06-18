package site.linyuange.android.samples

import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import site.linyuange.android.samples.recoder.ScreenRecorderActivity
import site.linyuange.framework.BaseActivity

/**
 * Author: BaHuang
 * Date: 2019/5/24 12:59
 */
class MainActivity : BaseActivity() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        mBtnMediaRecorder.setOnClickListener { naviToMediaRecorderPage() }
    }

    private fun naviToMediaRecorderPage() {
        Toast.makeText(this, "naviToMediaRecorderPage", Toast.LENGTH_LONG).show();
        ScreenRecorderActivity.getStartIntent(this@MainActivity)
    }
}