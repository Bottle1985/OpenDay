package uni.tbd.openday;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Open activity list building
        Button sodo = (Button) findViewById(R.id.ButtonSoDo);
        Button khoa = (Button) findViewById(R.id.ButtonKhoa);
        Button nganh = (Button) findViewById(R.id.ButtonNganh);
        Button sukien = (Button) findViewById(R.id.ButtonSuKien);

        sodo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ActivityBuilding.class);
                startActivity(myIntent);
            }
        });
        khoa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), KHOA.class);
                startActivityForResult(myIntent, 0);
            }
        });
        nganh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), NGANH.class);
                startActivityForResult(myIntent, 0);
            }
        });
        sukien.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SUKIEN.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
