package uni.tbd.openday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class KHOA extends AppCompatActivity {
    public static int khoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoa);
        this.overridePendingTransition(R.anim.activity_open_enter,
                R.anim.activity_open_exit);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);

        Button khoa01 = (Button) findViewById(R.id.bt_khoa01);
        Button khoa02 = (Button) findViewById(R.id.bt_khoa02);
        Button khoa03 = (Button) findViewById(R.id.bt_khoa03);

        khoa01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GT_KHOA.class);
                khoa = 1;
                startActivityForResult(myIntent, 0);
            }

        });
        khoa02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GT_KHOA.class);
                khoa = 2;
                startActivityForResult(myIntent, 0);
            }
        });
        khoa03.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GT_KHOA.class);
                khoa = 3;
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
