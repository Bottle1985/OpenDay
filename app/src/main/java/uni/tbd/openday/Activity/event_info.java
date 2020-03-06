package uni.tbd.openday.Activity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import uni.tbd.openday.Data.EventData;
import uni.tbd.openday.R;
import uni.tbd.openday.SUKIEN;

public class event_info extends AppCompatActivity {
    private static final String TAG = "event_info";
    private EventData event;
    TextView eventname, eventsummary, eventplace, eventtime, eventlecturer;
    ImageView eventimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_event);
        this.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        eventname = findViewById(R.id.event_name);
        eventsummary = findViewById(R.id.event_summary);
        eventplace = findViewById(R.id.event_place);
        eventtime = findViewById(R.id.event_time);
        eventlecturer = findViewById(R.id.event_lecturer);
        eventimg = findViewById(R.id.img_event);
        // Load data from database
        // get reference to 'events' node
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference("events");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int icount = 0;
              for (DataSnapshot child: dataSnapshot.getChildren()){
                    // Get User object and use the values to update the UI
                    event = child.getValue(EventData.class);
                    if (icount == SUKIEN.id_sukien) {
                        assert event != null;
                        eventname.setText(event.EventName);
                        eventsummary.setText(event.EventSummary);
                        eventplace.setText(event.EventPlace);
                        eventtime.setText(event.EventTime);
                        eventlecturer.setText(event.EventLecturer);
                    }
                    icount++;
              }
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

        switch (SUKIEN.id_sukien){
            case 0:{
                eventimg.setImageResource(R.drawable.sukien0);
                break;
            }
            case 1:{
                eventimg.setImageResource(R.drawable.sukien1);
                break;
            }
            case 2:{
                eventimg.setImageResource(R.drawable.sukien2);
                break;
            }
        }
    }
}
