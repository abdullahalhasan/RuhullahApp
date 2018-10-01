package dev.hasan.ruhullahapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pushbots.push.Pushbots;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity  {

    private static final String TAG = "NotificationTesting";
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView txtRegId, txtMessage;
    private ListView notificationListView;
    private ArrayAdapter notificationAdapter;
    private ArrayList<String> notificationList;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    private int notiID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        txtRegId = (TextView) findViewById(R.id.txt_reg_id);
        txtMessage = (TextView) findViewById(R.id.txt_push_message);
        notificationListView = findViewById(R.id.notificationListView);
        notificationList = new ArrayList();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(NotificationActivity.this);
        editor = sharedPreferences.edit();
        //Defining Notification ID
        if (sharedPreferences.getInt("NID",0) == 0){
            //notiID = 1;
            Log.e(TAG,"NIDInSPIf: "+sharedPreferences.getInt("NID",0));
            Log.e(TAG,"NIDVarIf: "+notiID);
        } else {
            notiID = sharedPreferences.getInt("NID",0);
            notiID++;
            Log.e(TAG,"NIDInSPElse: "+sharedPreferences.getInt("NID",0));
            Log.e(TAG,"NIDVarElse: "+notiID);
        }

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");
                    sharedPreferences.edit().putInt("NID",notiID).commit();
                    Log.e(TAG,"NOTIBM: "+message);
                    Log.e(TAG,"NOTIBF: "+sharedPreferences.getString("NoficationBody"+notiID,"No new Notification"));

                    //editor.putString()
                    sharedPreferences.edit().putString("NotificationBody"+notiID,message).commit();

                    txtMessage.setText(sharedPreferences.getString("NotificationBody","No new Notification"));
                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                    Log.e(TAG,"NOTIAF: "+sharedPreferences.getString("NotificationBody"+notiID,"No new Notification"));
                    //txtMessage.setText(message);
                }
            }
        };
        Log.e(TAG,"NOTIASTF: "+sharedPreferences.getString("NotificationBody"+notiID,"No new Notification"));

        Log.e(TAG,"NIDVarIf: "+notiID);
        for (int i = 1; i <= notiID; i++) {
            notificationList.add(sharedPreferences.getString("NotificationBody"+i,"No new Notification"));
        }
        Log.e(TAG,"ListOfNoti: "+notificationList);
        notificationAdapter = new ArrayAdapter(NotificationActivity.this,android.R.layout.simple_list_item_1,notificationList);
        notificationListView.setAdapter(notificationAdapter);

        displayFirebaseRegId();
    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            txtRegId.setText("Firebase Reg Id: " + regId);
        else
            txtRegId.setText("Firebase Reg Id is not received yet!");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}