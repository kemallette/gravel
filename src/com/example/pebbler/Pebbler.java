package com.example.pebbler;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;	
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Pebbler extends Activity implements OnClickListener {
	public static final String TAG = "Pebbler";
	public static final String TITLE = "title", BODY = "body";
	public static final String SEND_NOTIFICATION = 	"com.getpebble.action.SEND_NOTIFICATION";

	public static final String PEBBLE_ALERT = "PEBBLE_ALERT";

	private EditText eTxtNotificationTitle, eTxtNotificationBody;
	private Button btnSendNotification;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pebbler);

		initViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pebbler, menu);
		return true;
	}

	private void initViews() {

		eTxtNotificationTitle = (EditText) findViewById(R.id.modal_alert_title);
		eTxtNotificationBody = (EditText) findViewById(R.id.modal_alert_body);

		btnSendNotification = (Button) findViewById(R.id.send_modal_alert);
		btnSendNotification.setOnClickListener(this);

	}

	private Map<String, String> getAlertNotification() {
		
		HashMap<String, String> mNotification = new HashMap<String, String>();
		
		mNotification.put(TITLE, eTxtNotificationTitle.getText().toString());
		mNotification.put(BODY, eTxtNotificationBody.getText().toString());
		
		return mNotification;
	}

	public void sendAlertToPebble(Map<String, String> mNotification) {
		
		final Intent i = new Intent(SEND_NOTIFICATION);

		final Map<String, String> data = new HashMap<String, String>();
		data.put(TITLE, mNotification.get(TITLE));
		data.put(BODY, mNotification.get(BODY));
		
		final JSONObject jsonData = new JSONObject(data);
		
		final String notificationData = new JSONArray().put(jsonData)
				.toString();

		i.putExtra("messageType", PEBBLE_ALERT);
		i.putExtra("sender", "MyAndroidApp");
		i.putExtra("notificationData", notificationData);

		Log.d(TAG, "About to send a modal alert to Pebble: " + notificationData);
		sendBroadcast(i);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send_modal_alert:
			sendAlertToPebble(getAlertNotification());
			break;
		default:
			break;
		}
	}
}
