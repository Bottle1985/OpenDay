package uni.tbd.openday.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uni.tbd.openday.EventData;
import uni.tbd.openday.R;

public class event_add extends AppCompatActivity {

    private EditText editName, editSummary, editPlace, editTime, editLecturer;
    private DatabaseReference mFirebaseDatabase;

    private String userId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);

        editName = (EditText) findViewById(R.id.edit_event_name);
        editSummary = (EditText) findViewById(R.id.edit_event_summary);
        editPlace = (EditText) findViewById(R.id.edit_event_place);
        editTime = (EditText) findViewById(R.id.edit_event_time);
        editLecturer = (EditText) findViewById(R.id.edit_event_lecturer);
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'events' node
        mFirebaseDatabase = mFirebaseInstance.getReference("events");
    }

    /**
     * Creating new event node under 'events'
     */
    private void createEvent(String name, String summary, String place, String time, String lecturer) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        EventData event = new EventData(name, summary,place,time,lecturer);
        mFirebaseDatabase.child(userId).setValue(event);
    }

    public void save_event_click(View view) {
        String name = editName.getText().toString();
        String summary = editSummary.getText().toString();
        String place = editPlace.getText().toString();
        String time = editTime.getText().toString();
        String lecturer = editLecturer.getText().toString();

        // Check for already existed userId
        if (TextUtils.isEmpty(userId)) {
            createEvent(name, summary,place,time,lecturer);
        }
    }
}
