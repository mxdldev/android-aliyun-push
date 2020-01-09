package com.mxdl.push;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.taobao.sophix.SophixManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int result = 10/2;
                Log.v("MYTAG","result:"+result);
            }
        });
    }
}
