package site.linyuange.android.samples.recoder

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.MediaRecorder
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.aty_media_recorder.*
import site.linyuange.android.samples.R
import site.linyuange.framework.BaseActivity
import java.io.IOException

/**
 * Author: BaHuang
 * Date: 2019/6/18 15:37
 */
class ScreenRecorderActivity : BaseActivity() {

    private val REQUEST_CODE: Int = 1000
    private lateinit var mProjectionManager: MediaProjectionManager
    private var mMediaProjection: MediaProjection? = null
    private lateinit var mMediaRecorder: MediaRecorder
    private var mVirtualDisplay: VirtualDisplay? = null

    companion object {
        const val PERMISSION_REQUEST_CODE = 0x0010
        fun getStartIntent(ctx: Context) {
            val intent = Intent(ctx, ScreenRecorderActivity::class.java)
            ctx.startActivity(intent)
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.aty_media_recorder
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initData() {
        mBtnStart.setOnClickListener {
            if (checkPermission()) {
                startRecordScreen()
            }
        }
        mBtnStop.setOnClickListener {
            stopRecordScreen()
        }

        CommonUtil.init(this)

        mMediaRecorder = MediaRecorder()
        mProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
    }

    public override fun onDestroy() {
        super.onDestroy()
        destroyMediaProjection()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mMediaProjection = mProjectionManager.getMediaProjection(resultCode, data)
                    mVirtualDisplay = createVirtualDisplay()
                    mMediaRecorder.start()
                    isRecording = true
                }
            }
            else -> {
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                kotlin.run breaking@{
                    grantResults.forEach { result ->
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_LONG).show()
                            return@breaking
                        }
                    }
                    startRecordScreen()
                }
            }
            else -> {
                Log.d("BaHuangCN -- ", "#onRequestPermissionsResult#47:UNKNOWN REQUEST CODE ")
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startRecordScreen() {
        if (isRecording) return
        initRecorder()
        shareScreen()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initRecorder() {
        try {
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE)
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mMediaRecorder.setOutputFile(Environment
                    .getExternalStoragePublicDirectory(Environment
                            .DIRECTORY_DOWNLOADS).toString() + "/video.mp4")
            mMediaRecorder.setVideoSize(CommonUtil.getScreenWidth(), CommonUtil.getScreenHeight())
            mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mMediaRecorder.setVideoEncodingBitRate(512 * 1000)
            mMediaRecorder.setVideoFrameRate(30)
//            mMediaRecorder.setOrientationHint(orientation)
            mMediaRecorder.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun shareScreen() {
        if (mMediaProjection == null) {
            val intent = mProjectionManager.createScreenCaptureIntent()
            val packageManager = getPackageManager()
            if (packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                //存在录屏授权的Activity
                startActivityForResult(intent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "无法录制", Toast.LENGTH_SHORT).show()
            }
            return
        }
        mVirtualDisplay = createVirtualDisplay()
        mMediaRecorder.start()
        isRecording = true
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createVirtualDisplay(): VirtualDisplay {
        return mMediaProjection!!.createVirtualDisplay("MainActivity",
                CommonUtil.getScreenWidth(), CommonUtil.getScreenHeight(),
                CommonUtil.getScreenDpi(),
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                mMediaRecorder.surface, null/*Handler*/, null/*Callbacks*/
        )
    }

    private var isRecording = false
    private fun stopRecordScreen() {
        if (!isRecording) return
        isRecording = false
        mMediaRecorder.stop()
        mMediaRecorder.reset()
        stopScreenSharing()
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun stopScreenSharing() {
        if (mVirtualDisplay == null) {
            return
        }
        mVirtualDisplay?.release()
        //mMediaRecorder.release(); //If used: mMediaRecorder object cannot
        // be reused again
        destroyMediaProjection()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun destroyMediaProjection() {
//        mMediaProjection?.unregisterCallback(mMediaProjectionCallback)
        mMediaProjection?.stop()
        mMediaProjection = null
    }

    fun checkPermission(): Boolean {
        val checkPermission = (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                + ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                + ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE))
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            return false
        } else {
            return true
        }
    }
}
