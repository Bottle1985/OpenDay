package uni.tbd.openday;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import uni.tbd.openday.Adapter.CustomAdapter;

public class ActivityBuilding extends Activity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_building);
        // Button template return
        Button next = (Button) findViewById(R.id.Button02);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        // List view function
        final ListView list = findViewById(R.id.list);
        ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
        arrayList.add(new SubjectData(getResources().getString(R.string.Giang_duong), "https://www.tutorialspoint.com/java/", R.drawable.bg));
        arrayList.add(new SubjectData(getResources().getString(R.string.Ky_tuc_xa), "https://www.tutorialspoint.com/python/", R.drawable.bg_ky_tuc));
        arrayList.add(new SubjectData(getResources().getString(R.string.Thuc_hanh), "https://www.tutorialspoint.com/javascript/", R.drawable.bg_da_nang));
//        arrayList.add(new SubjectData("Cprogramming", "https://www.tutorialspoint.com/cprogramming/", R.drawable.bg));
//        arrayList.add(new SubjectData("Cplusplus", "https://www.tutorialspoint.com/cplusplus/", R.drawable.bg));
//        arrayList.add(new SubjectData("Android", "https://www.tutorialspoint.com/android/", R.drawable.bg));
        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        list.setAdapter(customAdapter);

    }
}
