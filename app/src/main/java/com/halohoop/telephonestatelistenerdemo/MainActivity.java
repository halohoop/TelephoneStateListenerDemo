package com.halohoop.telephonestatelistenerdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private TelephonyManager mTelephonyManager;
    private static final String TAG = "halohoop:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTelephonyManager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
        //不要忘记了权限的添加
        //<uses-permission Android:name="android.permission.READ_PHONE_STATE" />
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTelephonyManager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_NONE);
    }

    class MyPhoneStateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            /**
             * 返回电话状态
             *
             * CALL_STATE_IDLE 无任何状态时
             * CALL_STATE_OFFHOOK 接起电话时
             * CALL_STATE_RINGING 电话进来时
             */
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE://无任何状态时
                    logi("CALL_STATE_IDLE");
                    break;
                case TelephonyManager.CALL_STATE_RINGING://电话进来时
                    logi("CALL_STATE_RINGING");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK://接起电话时
                    logi("CALL_STATE_OFFHOOK");
                    break;
                default:
                    super.onCallStateChanged(state, incomingNumber);
                    break;
            }
        }
    }


    public void logi(String s) {
        Log.i(TAG, "halohoop:" + s);
    }
}
