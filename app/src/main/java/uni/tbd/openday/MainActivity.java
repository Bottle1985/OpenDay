package uni.tbd.openday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Open activity list building
        Button sodo = findViewById(R.id.ButtonSoDo);
        Button khoa =  findViewById(R.id.ButtonKhoa);
        Button nganh =  findViewById(R.id.ButtonNganh);
        Button sukien =  findViewById(R.id.ButtonSuKien);
        Button tuyensinh =  findViewById(R.id.ButtonTuyenSinh);
        Button tracnghiem =  findViewById(R.id.ButtonTracNghiem);

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
        tuyensinh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), webview.class);
                startActivityForResult(myIntent, 0);
            }
        });
        tracnghiem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), TRAC_NGHIEM.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
