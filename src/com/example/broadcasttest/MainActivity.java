package com.example.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private IntentFilter intentFilter;
//	private NetworkChangeReceiver networkChangeReceiver;
	private LocalReceiver localReceiver;
	private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
//        		Intent intent=new Intent("com.example.broadcasttest.MY_BROADCAST");
//        		sendBroadcast(intent);
        		Intent intent=new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
        		localBroadcastManager.sendBroadcast(intent);
        		
        	}
        });
        intentFilter=new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver=new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver,intentFilter); 
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver=new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

    }
    
    @Override
    protected void onDestroy(){
    	super.onDestroy();
//    	unregisterReceiver(networkChangeReceiver);
    	localBroadcastManager.unregisterReceiver(localReceiver);
    }
    
//    class NetworkChangeReceiver extends BroadcastReceiver{
//    	@Override
//    	public void onReceive(Context context, Intent intent){
//    		Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
//    	}
//    }
//    
    class LocalReceiver extends BroadcastReceiver{
    	@Override
    	public void onReceive(Context context, Intent intent){
    		Toast.makeText(context, "local broadcast", Toast.LENGTH_SHORT).show();
    	}
    }
    
    
}
