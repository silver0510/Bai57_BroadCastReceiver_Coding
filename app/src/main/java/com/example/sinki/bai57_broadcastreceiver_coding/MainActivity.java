package com.example.sinki.bai57_broadcastreceiver_coding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getActiveNetworkInfo()!=null)
            {
                btnLogin.setEnabled(true);
                Toast.makeText(context,"Bạn vừa mở Internet",Toast.LENGTH_SHORT).show();
            }
            else
            {
                btnLogin.setEnabled(false);
                Toast.makeText(context,"Bạn vừa tắt Internet",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //tạo bộ lọc để lắng nghe
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(wifiReceiver != null)
        {
            unregisterReceiver(wifiReceiver);
        }
    }
}
