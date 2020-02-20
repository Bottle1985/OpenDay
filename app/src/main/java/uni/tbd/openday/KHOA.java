package uni.tbd.openday;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URL;

public class KHOA extends Activity {

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
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/khoacntt"));
                startActivityForResult(myIntent, 0);
            }

        });
        khoa02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/khoanndlvh"));
                startActivityForResult(myIntent, 0);
            }
        });
        khoa03.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pou.edu.vn/khoakinhtevaluat"));
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
