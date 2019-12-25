package com.mxdl.push.receiver;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;

/**
 * Description: <MyReceiver><br>
 * Author:      mxdl<br>
 * Date:        2019/12/25<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class MyReceiver extends MessageReceiver {
    @Override
    protected void onNotification(Context context, String s, String s1, Map<String, String> map) {
        super.onNotification(context, s, s1, map);
        Log.v("TAG","onNotification start...");
        Log.v("TAG", "s:" + s + ";s1:" + s1);
    }

    @Override
    protected void onNotificationOpened(Context context, String s, String s1, String s2) {
        super.onNotificationOpened(context, s, s1, s2);
        Log.v("TAG","onNotificationOpened start...");
        Log.v("TAG", "s:" + s + ";s1:" + s1 + ";s2:" + s2);
    }

    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        super.onMessage(context, cPushMessage);
        Log.v("TAG","onMessage start...");
    }
}
