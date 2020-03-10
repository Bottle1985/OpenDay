package uni.tbd.openday;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button sodo,khoa,nganh,sukien,tuyensinh,tracnghiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Open activity list building
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
        }
        AnhXa();
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
    private void AnhXa(){

        sodo = (Button) findViewById(R.id.ButtonSoDo);
        khoa = (Button) findViewById(R.id.ButtonKhoa);
        nganh = (Button) findViewById(R.id.ButtonNganh);
        sukien = (Button) findViewById(R.id.ButtonSuKien);
        tuyensinh = (Button) findViewById(R.id.ButtonTuyenSinh);
        tracnghiem = (Button) findViewById(R.id.ButtonTracNghiem);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
    private void login(){
        final Dialog login = new Dialog(this);
        login.setContentView(R.layout.login);
        login.setCanceledOnTouchOutside(false);
        final EditText edit_email = login.findViewById(R.id.username);
        final EditText edit_pass = login.findViewById(R.id.password);
        Button loginbt = login.findViewById(R.id.btn_login);

        this.overridePendingTransition(R.anim.dialog_enter,
                R.anim.dialog_exit);
        loginbt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString().trim();
                String pass = edit_pass.getText().toString().trim();
                if (email.equals("123@gmail.com") && pass.equals("123456")){
                    Toast.makeText(MainActivity.this,"Đăng nhập thành công ",Toast.LENGTH_LONG);
                }else {
                    Toast.makeText(MainActivity.this,"Đăng nhập thất bại ",Toast.LENGTH_LONG);
                }
            }
        });

        login.show();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_login: {

                login();
                break;
            }
            case R.id.menu_setting: {

                break;
            }
            case R.id.menu_email:
                break;
            case R.id.menu_phone:
                break;
            case R.id.menu_exit:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_language,menu);
        menu.setHeaderTitle("Chọn ngôn ngữ");
        menu.setHeaderIcon(R.mipmap.ic_vietnam);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.lang_en:{

                break;
            }
            case R.id.lang_vi:{
                break;
            }
        }
        return super.onContextItemSelected(item);
    }
}
