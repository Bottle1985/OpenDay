package uni.tbd.openday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


import uni.tbd.openday.Activity.event_info;
import uni.tbd.openday.Adapter.EventAdapter;

public class SUKIEN extends AppCompatActivity {
    public  static int id_sukien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukien);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);
        final ListView list = findViewById(R.id.list_sukien);
        ArrayList<EventData> arrayList = new ArrayList<EventData>();
        arrayList.add(new EventData(getResources().getString(R.string.event_name_1),getResources().getString(R.string.event_summary_1),getResources().getString(R.string.event_place_1),getResources().getString(R.string.event_time_1),getResources().getString(R.string.event_lecturer_1), "http://pou.edu.vn/news/tap-huan-su-dung-moodle-va-trien-khai-e-learning.789", R.drawable.sukien0));
        arrayList.add(new EventData("","","","","", "http://pou.edu.vn/news/giang-vien-han-quoc-cong-tac-voi-nganh-dong-phuong-hoc.787", R.drawable.sukien1));
        arrayList.add(new EventData("","","","","", "http://pou.edu.vn/news/dai-hoc-thai-binh-duong-cho-sinh-vien-nghi-den-16-2-2020.784", R.drawable.sukien2));

        EventAdapter eventAdapter = new EventAdapter(this, arrayList);
        list.setAdapter(eventAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                id_sukien = position;
                Intent ieveninfo = new Intent(SUKIEN.this, event_info.class);
                startActivity(ieveninfo);
            }
        });
    }
}
