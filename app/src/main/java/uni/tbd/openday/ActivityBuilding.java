package uni.tbd.openday;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import uni.tbd.openday.Activity.BuildingInfo;
import uni.tbd.openday.Adapter.CustomAdapter;

public class ActivityBuilding extends Activity {
    /** Called when the activity is first created. */
    public static int id_building;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_building);
        // Button template return

        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);

        // List view function
        final ListView list = findViewById(R.id.list);
        ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
        arrayList.add(new SubjectData(getResources().getString(R.string.Giang_duong), "", R.drawable.thumble_giang_duong));
        arrayList.add(new SubjectData(getResources().getString(R.string.Ky_tuc_xa), "", R.drawable.thumble_ky_tuc));
        arrayList.add(new SubjectData(getResources().getString(R.string.Thuc_hanh), "", R.drawable.thumble_da_nang));

        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        list.setAdapter(customAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                id_building = position;
                Intent iBuildingInfo = new Intent(ActivityBuilding.this, BuildingInfo.class);
                startActivity(iBuildingInfo);
            }
        });
    }
}
