package uni.tbd.openday;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import uni.tbd.openday.Adapter.NganhAdapter;
import uni.tbd.openday.Data.NganhData;

public class GT_KHOA extends AppCompatActivity {
    public static int nganh, mode =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gt__khoa);
        this.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
        TextView TenKhoa = (TextView) findViewById(R.id.ten_khoa);
        ImageView IMGKhoa = (ImageView)findViewById(R.id.img_khoa);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
        }
        final ListView list = findViewById(R.id.list_nganhkhoa);



        if (TRAC_NGHIEM.mode == 0){
            switch (KHOA.khoa){
                case 1: {
                    TenKhoa.setText(R.string.khoa01);
                    IMGKhoa.setImageResource(R.drawable.cntt);
                    ArrayList<NganhData> arrayList = new ArrayList<NganhData>();
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh3), "", R.drawable.cntt2));
                    NganhAdapter nganhAdapter = new NganhAdapter(this, arrayList);
                    list.setAdapter(nganhAdapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            nganh = position;
                            Intent nganhinfo = new Intent(GT_KHOA.this, GT_NGANH.class);
                            startActivity(nganhinfo);
                        }
                    });
                    mode = 1;
                    break;
                }
                case 2:{
                    TenKhoa.setText(R.string.khoa02);
                    IMGKhoa.setImageResource(R.drawable.dongphuonghoc);
                    ArrayList<NganhData> arrayList = new ArrayList<NganhData>();
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh1), "", R.drawable.dongphuonghoc2));
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh2), "", R.drawable.dulich2));
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh4), "", R.drawable.ngonnguanh2));
                    NganhAdapter nganhAdapter = new NganhAdapter(this, arrayList);
                    list.setAdapter(nganhAdapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            nganh = position;
                            Intent nganhinfo = new Intent(GT_KHOA.this, GT_NGANH.class);
                            startActivity(nganhinfo);
                        }
                    });
                    mode = 2;
                    break;
                }
                case 3:{
                    TenKhoa.setText(R.string.khoa03);
                    IMGKhoa.setImageResource(R.drawable.ketoan);
                    ArrayList<NganhData> arrayList = new ArrayList<NganhData>();
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh5), "", R.drawable.ketoan2));
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh6), "", R.drawable.tcnh2));
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh7), "", R.drawable.qtkd2));
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh0), "", R.drawable.luat2));
                    NganhAdapter nganhAdapter = new NganhAdapter(this, arrayList);
                    list.setAdapter(nganhAdapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            nganh = position;
                            Intent nganhinfo = new Intent(GT_KHOA.this, GT_NGANH.class);
                            startActivity(nganhinfo);
                        }
                    });
                    mode = 3;
                    break;
                }
            }
        }else {
            switch (TRAC_NGHIEM.idkhoa){
                case 1: {
                    TenKhoa.setText(R.string.khoa01);
                    ArrayList<NganhData> arrayList = new ArrayList<NganhData>();
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh3), "3", R.drawable.cntt));
                    NganhAdapter nganhAdapter = new NganhAdapter(this, arrayList);
                    list.setAdapter(nganhAdapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            nganh = position;
                            Intent nganhinfo = new Intent(GT_KHOA.this, GT_NGANH.class);
                            startActivity(nganhinfo);
                        }
                    });
                    mode = 1;
                    break;
                }
                case 2:{
                    TenKhoa.setText(R.string.khoa02);
                    ArrayList<NganhData> arrayList = new ArrayList<NganhData>();
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh1), "1", R.drawable.dongphuonghoc));
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh2), "2", R.drawable.dulich));
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh4), "4", R.drawable.ngonguanh));
                    NganhAdapter nganhAdapter = new NganhAdapter(this, arrayList);
                    list.setAdapter(nganhAdapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            nganh = position;
                            Intent nganhinfo = new Intent(GT_KHOA.this, GT_NGANH.class);
                            startActivity(nganhinfo);
                        }
                    });
                    mode = 2;
                    break;
                }
                case 3:{
                    TenKhoa.setText(R.string.khoa03);
                    ArrayList<NganhData> arrayList = new ArrayList<NganhData>();
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh5), "5", R.drawable.ketoan));
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh6), "6", R.drawable.taichinhnganhang));
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh7), "7", R.drawable.quantrikinhdoanh));
                    arrayList.add(new NganhData(getResources().getString(R.string.nganh0), "0", R.drawable.luat));
                    NganhAdapter nganhAdapter = new NganhAdapter(this, arrayList);
                    list.setAdapter(nganhAdapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            nganh = position;
                            Intent nganhinfo = new Intent(GT_KHOA.this, GT_NGANH.class);
                            startActivity(nganhinfo);
                        }
                    });
                    mode = 3;
                    break;
                }
            }
            TRAC_NGHIEM.mode=0;
        }

    }
}
