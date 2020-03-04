package uni.tbd.openday;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

//import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import uni.tbd.openday.Activity.event_add;
import uni.tbd.openday.Activity.event_info;
import uni.tbd.openday.Adapter.EventAdapter;
import uni.tbd.openday.Class.User;
import uni.tbd.openday.Data.EventData;

public class SUKIEN extends AppCompatActivity {
    public static int id_sukien;
    private static final String TAG = "SUKIEN";
    private EventData[] user = new EventData[4];
    private String userId;
    private ListView mListView;
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
        // get reference to 'users' node

        final TextView labelGetKey = findViewById(R.id.textView8);
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
                    //Object object = child.getKey();
                    // Get User object and use the values to update the UI
                    user[0] = child.getValue(EventData.class);
                    bookModelList.add(user[0]);
                    //dataKeys.append(child.getKey()).append(user[0].name);
                }
                ListView list = (ListView) findViewById(R.id.list_sukien);
                EventAdapter adapter = new EventAdapter(SUKIEN.this, bookModelList);
                list.setAdapter(adapter);
                labelGetKey.setText(dataKeys.toString());
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


        //////////////////
//        ArrayList<EventData> arrayList = new ArrayList<EventData>();
//        arrayList.add(new EventData(getResources().getString(R.string.event_name_1),getResources().getString(R.string.event_summary_1),getResources().getString(R.string.event_place_1),getResources().getString(R.string.event_time_1),getResources().getString(R.string.event_lecturer_1), "http://pou.edu.vn/news/tap-huan-su-dung-moodle-va-trien-khai-e-learning.789", R.drawable.sukien1));
//        //arrayList.add(new EventData(user[0].name,user[0].email,"","","", "http://pou.edu.vn/news/giang-vien-han-quoc-cong-tac-voi-nganh-dong-phuong-hoc.787", R.drawable.sukien2));
//        arrayList.add(new EventData("","","","","", "http://pou.edu.vn/news/dai-hoc-thai-binh-duong-cho-sinh-vien-nghi-den-16-2-2020.784", R.drawable.sukien3));
//        EventAdapter eventAdapter = new EventAdapter(this, arrayList);
//        final ListView list = findViewById(R.id.list_sukien);
//        list.setAdapter(eventAdapter);
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//                id_sukien = position;
//                Intent ieveninfo = new Intent(SUKIEN.this, event_info.class);
//                startActivity(ieveninfo);
//            }
//        });
    }

    public void add_event_click(View view) {
        Intent eventAdd = new Intent(SUKIEN.this, event_add.class);
        startActivity(eventAdd);
    }
}
