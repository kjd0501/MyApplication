package com.example.myapplication;


import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.os.ParcelUuid;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.AsyncTask;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.content.ContentValues.TAG;

import java.util.Set;


public class Device_NetworkList extends Activity {
    // Return Intent extra
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    // Member fields
    private static final int REQUEST_ENABLE_BT = 2;
    private BluetoothAdapter mBtAdapter;
    private BluetoothSocket socket;
    private ArrayAdapter mPairedDevicesArrayAdapter;
    private ArrayAdapter mNewDevicesArrayAdapter;
    private static UUID MY_UUID;
    InputStream input;
    OutputStream output;

    public CreateConnectThread createConnectThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_device_network_list);


        // Set result CANCELED in case the user backs out
        //setResult(Activity.RESULT_CANCELED);
        // Initialize the button to perform device discovery
        Button scanButton = (Button) findViewById(R.id.button_scan);

        scanButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                doDiscovery();
                //v.setVisibility(View.GONE);
            }
        });
        // Initialize array adapters. One for already paired devices and
        // one for newly discovered devices

        mPairedDevicesArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        mNewDevicesArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        // Find and set up the ListView for paired devices
        ListView pairedListView = (ListView) findViewById(R.id.paired_devices);

        pairedListView.setAdapter(mPairedDevicesArrayAdapter);
        pairedListView.setOnItemClickListener(mDeviceClickListener);
        // Find and set up the ListView for newly discovered devices
        ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
        newDevicesListView.setAdapter(mNewDevicesArrayAdapter);
        newDevicesListView.setOnItemClickListener(mDeviceClickListener);
        // Register for broadcasts when a device is discovered
//        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        this.registerReceiver(mReceiver, filter);
//
//
//        // Register for broadcasts when discovery has finished
//        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//        //this.registerReceiver(mReceiver2, filter1);
//
//        IntentFilter filter0 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
//        this.registerReceiver(mReceiver1, filter0);
        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        // Get a set of currently paired devices
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);

            return;
        }


        Set pairedDevices = mBtAdapter.getBondedDevices();
        Set<BluetoothDevice> devices = mBtAdapter.getBondedDevices();

        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size() > 0) {
            findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice device : devices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
        /*
             else {
            String noDevices = getResources().getText(R.string.none_paired).toString();
            mPairedDevicesArrayAdapter.add(noDevices);
            }
            */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Make sure we're not doing discovery anymore
        if (mBtAdapter != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, 0);

                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

            }
            mBtAdapter.cancelDiscovery();
        }
        // Unregister broadcast listeners
        this.unregisterReceiver(mReceiver);
        this.unregisterReceiver(mReceiver1);
        this.unregisterReceiver(mReceiver2);
    }

    /**
     * Start device discover with the BluetoothAdapter
     */
    private void doDiscovery() {
        // Indicate scanning in the title
        setProgressBarIndeterminateVisibility(true);


        setTitle("scanning");
        // Turn on sub-title for new devices
        findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);
        // If we're already discovering, stop it
        if (ActivityCompat.checkSelfPermission(Device_NetworkList.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, 3);
            showToast("scanning permission");
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();

        }
        // Request discover from BluetoothAdapter
        //ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 3);
        //ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 3);
        //showToast("scanning location");
        if (ContextCompat.checkSelfPermission(Device_NetworkList.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        if (ContextCompat.checkSelfPermission(Device_NetworkList.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }
        mBtAdapter.startDiscovery();


        // Register for broadcasts when discovery has finished
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        Device_NetworkList.this.registerReceiver(mReceiver2, filter1);

        IntentFilter filter0 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        Device_NetworkList.this.registerReceiver(mReceiver1, filter0);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        Device_NetworkList.this.registerReceiver(mReceiver, filter);


    }

    // The on-click listener for all devices in the ListViews
    private OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {
            // Cancel discovery because it's costly and we're about to connect
            if (ActivityCompat.checkSelfPermission(Device_NetworkList.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, 3);

                return;
            }
            mBtAdapter.cancelDiscovery();

            // Get the device MAC address, which is the last 17 chars in the View

            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            BluetoothDevice device = mBtAdapter.getRemoteDevice(address);
            showToast(address);
            //createConnectThread = new CreateConnectThread(mBtAdapter, address);
            //createConnectThread.run();
            //CreateConnectThread=new CreateConnectThread(mBtAdapter,address);
            //CreateConnectThread.start();

            BluetoothSocket tmp = null;

            if (ActivityCompat.checkSelfPermission(Device_NetworkList.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 0);

                return;
            }
            //UUID uuid = device.getUuids()[0].getUuid();
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

            try {
                tmp = device.createRfcommSocketToServiceRecord(uuid);

            } catch (IOException e) {
                Log.e(TAG, "Socket create failed", e);
            }
            socket = tmp;

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    String message = "d";
                    byte[] toSend = message.getBytes();
                    BluetoothAdapter bA = BluetoothAdapter.getDefaultAdapter();
                    if (ActivityCompat.checkSelfPermission(Device_NetworkList.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, 0);
                        return;
                    }
                    bA.cancelDiscovery();
                    try {
                        socket.connect();
                        showToast("connected");
                        openActivity();
                        // Get the BluetoothSocket input and output streams
                        try {
                            input = socket.getInputStream();
                            output = socket.getOutputStream();
                            //showToast(toSend.toString());
                            output.write(toSend);
                        } catch (IOException e) {
                            showToast("error with stream");
                            //return;
                        }

                        Log.e("status", "connected");
                    } catch (IOException connectException) {
                        try {
                            socket.close();
                            if (socket.isConnected()) {
                                showToast("already connected");
                            }
                            showToast("failed to connect");
                            Log.e("status", "failed to connect");
                        } catch (IOException closeException) {
                            Log.e(TAG, "could not close socket", closeException);

                        }
                        return;
                    }
                }
            });

        }
    };

    //The BroadcastReceiver that listens for discovered devices and
    //changes the title when discovery is finished

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override

        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            // When discovery finds a device

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                if (ActivityCompat.checkSelfPermission(Device_NetworkList.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 0);

                    return;
                }
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
                // When discovery is finished, change the Activity title
            }

        }
    };

    private final BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {

            }
        }
    };

    private final BroadcastReceiver mReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {

                setProgressBarIndeterminateVisibility(false);
                setTitle("select device");
            }
        }
    };

    public void openActivity() {
        Intent act2 = new Intent(this, MainActivity2.class);
        startActivity(act2);
    }
    public class CreateConnectThread extends Thread {
        public CreateConnectThread(BluetoothAdapter bA, String address) {
            BluetoothDevice device = bA.getRemoteDevice(address);
            BluetoothSocket tmp = null;

            if (ActivityCompat.checkSelfPermission(Device_NetworkList.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 0);

                return;
            }
            //UUID uuid = device.getUuids()[0].getUuid();
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

            try {
                tmp = device.createRfcommSocketToServiceRecord(uuid);

            } catch (IOException e) {
                Log.e(TAG, "Socket create failed", e);
            }
            socket = tmp;


        }



            @Override
            public void run () {
                super.run();
                //showToast("in run");
                String message = "d";
                byte[] toSend = message.getBytes();
                BluetoothAdapter bA = BluetoothAdapter.getDefaultAdapter();
                if (ActivityCompat.checkSelfPermission(Device_NetworkList.this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Device_NetworkList.this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, 0);
                    return;
                }
                bA.cancelDiscovery();
                try {
                    socket.connect();
                    showToast("connected");
                    // Get the BluetoothSocket input and output streams
                    try {
                        input = socket.getInputStream();
                        output = socket.getOutputStream();
                        //showToast(toSend.toString());
                        output.write(toSend);
                    } catch (IOException e) {
                        showToast("error with stream");
                        //return;
                    }

                    Log.e("status", "connected");
                } catch (IOException connectException) {
                    try {
                        socket.close();
                        if (socket.isConnected()) {
                            showToast("already connected");
                        }
                        showToast("failed to connect");
                        Log.e("status", "failed to connect");
                    } catch (IOException closeException) {
                        Log.e(TAG, "could not close socket", closeException);

                    }
                    return;
                }
            }

        public void cancel(){
            try{
                socket.close();
            }catch(IOException e){
                Log.e(TAG,"could not close socket1", e);
            }
        }
    }




    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}