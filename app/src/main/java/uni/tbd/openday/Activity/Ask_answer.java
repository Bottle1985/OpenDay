package uni.tbd.openday.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import uni.tbd.openday.Config;
import uni.tbd.openday.R;

public class Ask_answer extends AppCompatActivity {
    private static final String TAG = "Ask_answer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_answer);
        // Load data from database
        // get reference to 'category' node
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference(Config.FIREBASE_CATEGORIES);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String categories = dataSnapshot.getValue().toString();
                Log.d("Category added", categories);
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
}
