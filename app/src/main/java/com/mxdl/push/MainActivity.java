package com.mxdl.push;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.taobao.sophix.SophixManager;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = 10 / 2;
                Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_LONG).show();
                Log.v("MYTAG", "result:" + result);
            }
        });
        mTxtLog = findViewById(R.id.txt_log);
        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String log = intent.getStringExtra("log");
                mTxtLog.setText(log);
            }
        },new IntentFilter("com.mxdl.push"));
    }
}
