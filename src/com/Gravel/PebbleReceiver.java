package com.Gravel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PebbleReceiver extends BroadcastReceiver {
	
	public static final String TAG = "PebbleConnectionReceiever";

	public static final String PEBBLE_CONNECTED = "com.getpebble.action.PEBBLE_CONNECTED";
	public static final String PEBBLE_DISCONNECTED = "com.getpebble.action.PEBBLE_DISCONNECTED";
	public static final String PEBBLE_RECEIVE_DATA = "com.getpebble.action.RECEIVE_DATA";

	@Override
	public void onReceive(Context context, Intent intent) {

		final String pebbleMacAddress = intent.getStringExtra("address");
		final String action = intent.getAction();

		String message = "Couldn't match pebble broadcast intent action." + "\n" +
				"MacAdress: " + pebbleMacAddress + "\n" + 
				"Action: " + action;

		if (action.equals(PEBBLE_CONNECTED)) {
			
			message = "PEBBLE_CONNECTED" + "\n" + 
			"MacAdress: " + pebbleMacAddress + "\n" + 
			"Action: " + action;
			
		} else if (action.equals(PEBBLE_DISCONNECTED)) {
			
			message = "PEBBLE_DISCONNECTED" + "\n" + 
			"MacAdress: " + pebbleMacAddress + "\n" + 
			"Action: " + action;
			
		} else if(action.equals(PEBBLE_RECEIVE_DATA)){
			
			final String pebbleAppRecipient = intent.getStringExtra("recipient"); 
			final String pebbleData = intent.getStringExtra("data");
			
			message = "Pebble Receiving Data" + "\n" + 
			"MacAdress: " + pebbleMacAddress + "\n" + 
			"Action: " + action + "\n" + 
			"Recipient: " + pebbleAppRecipient + "\n"+
			"Data: " + pebbleData + "\n";
			 
		}

		Log.i(TAG, message);
	}
}
