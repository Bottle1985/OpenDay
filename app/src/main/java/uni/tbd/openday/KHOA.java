package uni.tbd.openday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class KHOA extends Activity {
    public static int khoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoa);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);

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
