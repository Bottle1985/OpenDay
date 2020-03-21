package uni.tbd.openday;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {
    Button sodo,khoa,nganh,sukien,tuyensinh,tracnghiem;
    ViewFlipper viewFlipper;
    int [] arrHinh = {R.drawable.sukien0,R.drawable.sukien1,R.drawable.sukien2,R.drawable.sukien3};
    Animation in, out;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Open activity list building
        ActionBar actionBar = getSupportActionBar();
        in = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
        }
        AnhXa();
        ButtonAction();
        ActionBar();
        for (int i = 0;i< arrHinh.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(arrHinh[i]);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);


    }
    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void ButtonAction(){
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
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        sodo = (Button) findViewById(R.id.ButtonSoDo);
        khoa = (Button) findViewById(R.id.ButtonKhoa);
        nganh = (Button) findViewById(R.id.ButtonNganh);
        sukien = (Button) findViewById(R.id.ButtonSuKien);
        tuyensinh = (Button) findViewById(R.id.ButtonTuyenSinh);
        tracnghiem = (Button) findViewById(R.id.ButtonTracNghiem);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
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
}
