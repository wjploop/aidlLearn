package com.loop.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;


import com.loop.aidldemo.SsoAuth;

import androidx.annotation.Nullable;

/**
 * 可以看到,这里的服务只是提供一个外壳,具体实现是在Stub中
 */
public class SinaSsoAuthService extends Service {

    SinaSsoImpl mBinder = new SinaSsoImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("wolf", "sso auth created");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class SinaSsoImpl extends SsoAuth.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override
        public void ssoAuth(String username, String password) {
            Log.d("wolf", "这里是服务服务端,接受到客户的登录请求,执行SSO登录.用户名:" + username + ",密码:" + password);
            //
            Looper.prepare();
            Toast.makeText(SinaSsoAuthService.this, "接受到了登录请求", Toast.LENGTH_SHORT).show();
        }
    }
}
