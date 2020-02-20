package uni.tbd.openday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NGANH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nganh);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);
        Button nganh0 = (Button) findViewById(R.id.bt_nganh0);
        Button nganh1 = (Button) findViewById(R.id.bt_nganh1);
        Button nganh2 = (Button) findViewById(R.id.bt_nganh2);
        Button nganh3 = (Button) findViewById(R.id.bt_nganh3);
        Button nganh4 = (Button) findViewById(R.id.bt_nganh4);
        Button nganh5 = (Button) findViewById(R.id.bt_nganh5);
        Button nganh6 = (Button) findViewById(R.id.bt_nganh6);
        Button nganh7 = (Button) findViewById(R.id.bt_nganh7);

        nganh0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/bophants/sp/luat.500"));
                startActivityForResult(myIntent, 0);
            }
        });
        nganh1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/bophants/sp/dong-phuong-hoc.336"));
                startActivityForResult(myIntent, 0);
            }
        });
        nganh2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/bophants/sp/du-lich.337"));
                startActivityForResult(myIntent, 0);
            }
        });
        nganh3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/bophants/sp/du-lich-nha-hang-khach-san.338"));
                startActivityForResult(myIntent, 0);
            }
        });
        nganh4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/bophants/sp/ngon-ngu-anh.340"));
                startActivityForResult(myIntent, 0);
            }
        });
        nganh5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/bophants/sp/huong-dan-du-lich-va-lu-hanh.339"));
                startActivityForResult(myIntent, 0);
            }
        });
        nganh6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/bophants/sp/nganh-tai-chinh-ngan-hang.501"));
                startActivityForResult(myIntent, 0);
            }
        });
        nganh7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/bophants/sp/quan-tri-kinh-doanh.341"));
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
