package com.example.myapplication;


import android.Manifest;
import android.net.wifi.WifiManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.widget.Toast;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.Map;
import java.util.HashMap;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.net.wifi.WifiConfiguration;
import java.io.*;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private Button bt;
    private Button wf;
    private Button but;
    BluetoothAdapter BA;
    String ip="120.33.33.22";
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;
    public ConnectedThread2 connectedThread2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bluetooth);
        wf = (Button) findViewById(R.id.wifi);
        but=(Button) findViewById(R.id.button);
        BA = BluetoothAdapter.getDefaultAdapter();

        int ivar = 111;
        String str = String.valueOf(ivar);
        if (BA == null) {
            showToast("bluetooth not supported");
            finish();
            return;
        }
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wf.setVisibility(View.VISIBLE);
                bt.setVisibility(View.GONE);
                but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bt.setVisibility(View.VISIBLE);
                        wf.setVisibility(View.GONE);
                        return;
                    }
                });
                return;
            }
        });

        wf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if (wifi.isWifiEnabled()){
                    String ipAddress = Formatter.formatIpAddress(wifi.getConnectionInfo().getIpAddress());
                    showToast(ipAddress);


                    connectedThread2 = new ConnectedThread2(ipAddress);
                    connectedThread2.send(1,ip);

                }
                else
                    showToast("Wifi is not enabled");

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!BA.isEnabled()) {
                    //showToast("Turning ON Bluetooth...");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);


                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.BLUETOOTH_CONNECT }, REQUEST_ENABLE_BT);

                        return;
                    }
                    startActivityForResult(intent,REQUEST_ENABLE_BT);
                    //showToast("Turning ON Bluetooth...");

                }
                else{
                    //showToast("Bluetooth is already ON");
                    openActivity2();
                }
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if (resultCode== RESULT_OK){
                    // bluetooth ON
                    showToast("Bluetooth is ON");
                    openActivity2();
                }else{
                    showToast("Bluetooth is not on");
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public class ConnectedThread2 extends Thread {
        public ConnectedThread2(String ipaddress) {
            Log.e("Status", "Device connected");
        }

        public void run() {
            Log.e("Status", "Device running");
        }

        public void send(int cmdText, String ip) {
            Log.e("Status", "Sending data " + cmdText);
            ip="www.google.com";
            String url = "http://" + ip + "/post";
            showToast("in send");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("Status", response.trim());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Status", error.toString());
                            showToast("error");
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    String value = String.valueOf(cmdText);
                    params.put("data", value);
                    Log.e("Status", value);
                    //showToast("map");
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);
            showToast("end");
        }


    }


    public void openActivity2() {
        Intent A2 = new Intent(this, MainActivity2.class);
        startActivity(A2);
    }
}









