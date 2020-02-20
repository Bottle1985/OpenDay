package uni.tbd.openday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import uni.tbd.openday.Activity.BuildingInfo;
import uni.tbd.openday.Adapter.CustomAdapter;

public class SUKIEN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukien);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);
        final ListView list = findViewById(R.id.list_sukien);
        ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
        arrayList.add(new SubjectData(getResources().getString(R.string.sukien0), "http://pou.edu.vn/news/thong-bao-ve-viec-sinh-vien-khong-den-truong-tranh-dich-benh-covid-19.788", R.drawable.sukien0));
        arrayList.add(new SubjectData(getResources().getString(R.string.sukien1), "http://pou.edu.vn/news/giang-vien-han-quoc-cong-tac-voi-nganh-dong-phuong-hoc.787", R.drawable.sukien1));
        arrayList.add(new SubjectData(getResources().getString(R.string.sukien2), "http://pou.edu.vn/news/dai-hoc-thai-binh-duong-cho-sinh-vien-nghi-den-16-2-2020.784", R.drawable.sukien2));

        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        list.setAdapter(customAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //Toast.makeText(SuggestionActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(arg1.getContext(), BuildingInfo.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
