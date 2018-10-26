package com.loop.aidldemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mSsoAuth: SsoAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sso.setOnClickListener {
            if (mSsoAuth == null) bindSsoAuthService() else doSsoAuth()
        }

    }

    fun bindSsoAuthService() {
        val intent = Intent("wolf.ssoService").apply { setPackage("com.loop.aidlserver") }

        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mConnection)
    }

    val mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mSsoAuth = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            //建立连接之后,将IBinder转换未我们想要的服务
            mSsoAuth = SsoAuth.Stub.asInterface(service)
            doSsoAuth()
        }

    }

    private fun doSsoAuth() {
        //执行登录,实际上调用的是Server端的ssoAuth函数 //注:kotlin中,对于异常不用显性捕获,这里可能会抛RemoteException
        mSsoAuth?.ssoAuth("wjploop", "qwer1234")
    }


}
