package uni.tbd.openday;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
//import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



import uni.tbd.openday.Activity.event_add;

import uni.tbd.openday.Activity.event_info;
import uni.tbd.openday.Adapter.EventAdapter;

import uni.tbd.openday.Data.EventData;

public class SUKIEN extends AppCompatActivity {
    public static int id_sukien;
    private static final String TAG = "SUKIEN";
    private EventData[] event = new EventData[4];
    //private String userId;
    //private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukien);
        this.overridePendingTransition(R.anim.activity_open_enter,
                R.anim.activity_open_exit);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        // Load data from database
        // get reference to 'events' node

//        final TextView labelGetKey = findViewById(R.id.textView8);
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference("events");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // ...
                String key = dataSnapshot.getKey();
                StringBuilder dataKeys= new StringBuilder();
                ArrayList<EventData> bookModelList = new ArrayList<>();
                for (DataSnapshot child: dataSnapshot.getChildren()){

                    // Get User object and use the values to update the UI
                    event[0] = child.getValue(EventData.class);
                    bookModelList.add(event[0]);

                }
                ListView list = (ListView) findViewById(R.id.list_sukien);
                EventAdapter adapter = new EventAdapter(SUKIEN.this, bookModelList);
                list.setAdapter(adapter);
//                labelGetKey.setText(dataKeys.toString());
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    id_sukien = position;
                    Intent ieveninfo = new Intent(SUKIEN.this, event_info.class);
                    startActivity(ieveninfo);
                }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mFirebaseDatabase.addValueEventListener(postListener);
        // End load data from database
    }

    public void add_event_click(View view) {
        Intent eventAdd = new Intent(SUKIEN.this, event_add.class);
        startActivity(eventAdd);
    }
}
