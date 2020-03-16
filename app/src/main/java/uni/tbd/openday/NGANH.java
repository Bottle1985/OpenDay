package uni.tbd.openday;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import uni.tbd.openday.Activity.event_info;
import uni.tbd.openday.Adapter.EventAdapter;
import uni.tbd.openday.Adapter.NganhAdapter;
import uni.tbd.openday.Data.EventData;
import uni.tbd.openday.Data.NganhData;
import uni.tbd.openday.Data.SubjectData;

public class NGANH extends AppCompatActivity {
    public static int nganh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nganh);
        this.overridePendingTransition(R.anim.activity_open_enter,
                R.anim.activity_open_exit);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
        }
        final ListView list = findViewById(R.id.listview_nganh);
        ArrayList<NganhData> arrayList = new ArrayList<NganhData>();
        arrayList.add(new NganhData(getResources().getString(R.string.nganh0), "", R.drawable.luat2));
        arrayList.add(new NganhData(getResources().getString(R.string.nganh1), "", R.drawable.dongphuonghoc2));
        arrayList.add(new NganhData(getResources().getString(R.string.nganh2), "", R.drawable.dulich2));
        arrayList.add(new NganhData(getResources().getString(R.string.nganh3), "", R.drawable.cntt2));
        arrayList.add(new NganhData(getResources().getString(R.string.nganh4), "", R.drawable.ngonnguanh2));
        arrayList.add(new NganhData(getResources().getString(R.string.nganh5), "", R.drawable.ketoan2));
        arrayList.add(new NganhData(getResources().getString(R.string.nganh6), "", R.drawable.tcnh2));
        arrayList.add(new NganhData(getResources().getString(R.string.nganh7), "", R.drawable.qtkd2));
        NganhAdapter nganhAdapter = new NganhAdapter(this, arrayList);
        list.setAdapter(nganhAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                nganh = position;
                Intent nganhinfo = new Intent(NGANH.this, GT_NGANH.class);
                startActivity(nganhinfo);
            }
        });
    }
}
