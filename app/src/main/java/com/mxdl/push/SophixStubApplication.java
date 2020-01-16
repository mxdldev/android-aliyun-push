package com.mxdl.push;

/**
 * Description: <SophixStubApplication><br>
 * Author:      mxdl<br>
 * Date:        2020/1/9<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

import androidx.annotation.Keep;

/**
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * 此类不应与项目中的其他类有任何互相调用的逻辑，必须完全做到隔离。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */
public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";
    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MainApplication.class)
    static class RealApplicationStub {}
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
        Log.v(TAG,"attachBaseContext start...");
        initSophix();
    }
    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        String rsa = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDF0mYpWOnakbaDr3Tl3nCgnz5aCmLoTDPIblPMtFpiBTFFplvLKBw7kTVynzS+TxEq5F/RnWglreFYrbMzKYiSFpCOp2gpifq13j+6xDs0VQhp5BrmyBaSACqUWqbNclKiLm1Il67uv3CxcCSWCOkxYEn7+ye6Jr/ydSJJyGLX0SoYHKpsWT22pIjoNLgeI3GMoYuq3iqfXURxHtO5550gW7qDnSpeAPPawLIAwQJXssj4nXsxbsz3Okk/ZwAxaVTX20AIsHjcmleechUDAG8lowoP7N5288gojLsPzbOO5hDtRkRyz6fl1mV6onm3mGm+SAbKUaVC2F4H8bolxRhNAgMBAAECggEAY41nlkVFopYT6J9iz8+qlFqvUTbK4aZ/pM1I5MqadZuxWSDILyvnUV7ZK+7M5zz5IcG+KsnxQI0Jy8vWU9gB/6rVYsUCmWzzu5S2M7JPLR3h1xBRLXTiTuVAiFo6eUegKDf/DTowcbvr5Y/PCaZAXHXdpa6WJosbq9OgxsrOW1boMMKLDuA5roAPKD+OWU7QRfM1wcUs5t4VGj+oAPCroLppOceNY6bjmATU3pLhMGIjTcEDXSDOtNpIh/HQ8g3bwdPXLf7uMbQSX+9ZRw3IZUHnEgwAox1pQ48gtins5/MfGUwKsH1V0M4snFZJuiQ+g745mDlkfxlzj1w7zdl5DQKBgQDlq8dXDz9CTbI3i4dTIr4mqs5n/xXc9s5/jlvhWd0KVyUwYR/IAmcl824CijcuDAs1ntNrglRT3Nb3JZTNA7XLH/F1qc4L7UBo6yLeHTVpkrdaJ0vrh2tybpySL9egXvKUUCTVxp14xDbhIPwLDF1p30c1Y30gMehnvLQ/gVkdbwKBgQDcf+89mnlJszc/WAus5f6Uo4/IV8wx1sPohM3H16A6nSPNaCni9y5aOAOPWyflvBvKlhcVR4KETHAOJHPQdjs8SWSCJWXd1DiMGV/sqoOLnckC9Ij9b9LBIUVscu48GCESqJwMqDy4+/I73zxXFZ4FxzLzTYSqgaaBNnJ+GIJAAwKBgAHx6XdARs3d8JNt2BCJK8kSCztwOpQrduY3o2mOJEQRJtx5BhbghlUY8aaN2xfFvFaQ6MNxUBuBtERViiQOUvzMYXQYowkQ0knRK/Nrlec1+d2GlasB85P9gc/vHcla5H0DDLzOuMisCLbxW5EQYr4hyO2X0Rhzs78lJvWet817AoGAZFDwDx7QP2+Bmxf7d9XLVOR07bjD9Yi36HlDBcBb7U7Akxros5GxTOpe9EGMjUBfhHN0XQIDpxz/fe+noRepK2xYhb1t8tYyQ8rnkUyiPEq0wNtv152gTK1OYGCJcl2n7k03caq4tZAqTILreNMk6YwO6hJCYTBLCE41GJkd6PECgYAbpLtA0jtpehfA2XWKf5J40KGcIWYmfNsZ+ggXiCaYngKayflHTzIHzgJnvkg8cO0nDJugGyFiNIen2AuVR5F5uGWcLVQppeKfoXyfUgEZakxoE/fJUogmu0ugSw2vK6+TBGwG20KBB1E9psZQWwVI7McmMcQjnulTQ5L6dhBO9A==";
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData("28257624", "3c9a39d3cf83d5d7932aae36269c2d60", rsa)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        String log = "mode:" + mode + ";code:" + code + ";info:" + info + ";handlePatchVersion:" + handlePatchVersion;
                        Intent intent = new Intent("com.mxdl.push");
                        intent.putExtra("log",log);
                        sendBroadcast(intent);
                        Log.v(TAG, log);
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.v(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.v(TAG, "sophix preload patch success. restart app to make effect.");
                        }
                    }
                }).initialize();
    }
}