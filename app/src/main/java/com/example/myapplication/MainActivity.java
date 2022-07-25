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
    //declare the variables to be used
    private Button bt;
    private Button wf;

    // s & dout are public and accessible from any activity
    public static DataOutputStream dout;
    public static Socket s;


    BluetoothAdapter BA;
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bluetooth);  // bluetooth button
        wf = (Button) findViewById(R.id.wifi);  // wifi button
        BA = BluetoothAdapter.getDefaultAdapter();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // check if device supports bluetooth
        if (BA == null) {
            showToast("bluetooth not supported");
            finish();
            return;
        }

        // when wifi button is clicked
        wf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                // make sure wifi is turned on
                if (wifi.isWifiEnabled()){
                    String ipAddress = Formatter.formatIpAddress(wifi.getConnectionInfo().getIpAddress());
                    showToast(ipAddress);
                    try{
                        //create a client socket to connect to a server socket
                        //change ip address, this is just for testing
                        s = new Socket("10.248.128.125",6666);
                        dout=new DataOutputStream(s.getOutputStream());

                        // open home page
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
                    showToast("Wifi is not enabled");   // if wifi is not on

            }
        });

        // if bluetooth button is clicked
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // turn on bluetooth
                if (!BA.isEnabled()) {
                    //showToast("Turning ON Bluetooth...");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.BLUETOOTH_CONNECT }, REQUEST_ENABLE_BT);

                        return;
                    }
                    //request to turn on bluetooth
                    startActivityForResult(intent,REQUEST_ENABLE_BT);

                }

                //bluetooth already on, //call function openActivity2
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
                    openActivity2();   //call function openActivity2
                }else{
                    showToast("Bluetooth is not on");
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    // to print on the screen
    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    //go to Device_NetworkList activity
    public void openActivity2() {
        Intent A2 = new Intent(this, Device_NetworkList.class);
        startActivity(A2);
    }
}









