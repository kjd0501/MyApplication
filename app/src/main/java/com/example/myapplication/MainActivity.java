package com.example.myapplication;


import static android.content.ContentValues.TAG;
import android.Manifest;
import android.net.wifi.WifiManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
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
import java.io.*;
import java.net.*;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private Button bt;
    private Button wf;
    public static DataOutputStream dout;
    public static Socket s;
    BluetoothAdapter BA;
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;
    public ConnectedThread2 connectedThread2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bluetooth);
        wf = (Button) findViewById(R.id.wifi);
        BA = BluetoothAdapter.getDefaultAdapter();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        int ivar = 111;
        String str = String.valueOf(ivar);
        if (BA == null) {
            showToast("bluetooth not supported");
            finish();
            return;
        }
        wf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if (wifi.isWifiEnabled()){
                    String ipAddress = Formatter.formatIpAddress(wifi.getConnectionInfo().getIpAddress());
                    showToast(ipAddress);
                    try{
                        //change ip address, this is just for testing
                        s = new Socket("10.248.128.125",6666);
                        dout=new DataOutputStream(s.getOutputStream());
                        Intent act2 = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(act2);
                        //dout.flush();
                        //dout.close();
                        //s.close();
                    }catch(Exception e){
                        Log.e(TAG, "failed", e);
                        showToast("not sent");
                    }
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

                }
                else{
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
        Intent A2 = new Intent(this, Device_NetworkList.class);
        startActivity(A2);
    }
}









