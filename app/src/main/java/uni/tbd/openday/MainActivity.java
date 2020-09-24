package uni.tbd.openday;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uni.tbd.openday.Activity.Ask_answer;
import uni.tbd.openday.Activity.Login;

public class MainActivity extends AppCompatActivity {
    public static int mode_webview =0;
    Button sodo,khoa,nganh,sukien,tuyensinh,tracnghiem, Login, ask_answer, sinhvien;
    TextView txtUserName;
    ViewFlipper viewFlipper;
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
        accessUserInformation();
        int imgflipper [] ={R.drawable.bg_giang_duong,R.drawable.bg_ky_tuc,R.drawable.bg_da_nang,R.drawable.cntt2,R.drawable.dongphuonghoc2,R.drawable.dulich2,R.drawable.ketoan2,R.drawable.luat2,R.drawable.ngonnguanh2,R.drawable.qtkd2,R.drawable.tcnh2};
        for (int image: imgflipper){
            setViewFlipper(image);
        }

    }
    public void setViewFlipper (int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
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
        Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), uni.tbd.openday.Activity.Login.class);
                startActivityForResult(myIntent, 0);
            }
        });
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
                GT_KHOA.mode = 0;
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
                mode_webview = 1;
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
        ask_answer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Ask_answer.class);
                startActivity(myIntent);
            }
        });
        sinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode_webview = 2;
                Intent myIntent = new Intent(view.getContext(), webview.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }
    private void AnhXa(){
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        Login = (Button) findViewById(R.id.ButtonDangNhap);
        sodo = (Button) findViewById(R.id.ButtonSoDo);
        khoa = (Button) findViewById(R.id.ButtonKhoa);
        nganh = (Button) findViewById(R.id.ButtonNganh);
        sukien = (Button) findViewById(R.id.ButtonSuKien);
        tuyensinh = (Button) findViewById(R.id.ButtonTuyenSinh);
        tracnghiem = (Button) findViewById(R.id.ButtonTracNghiem);
        ask_answer = (Button) findViewById(R.id.ButtonHoiDap);
        sinhvien = (Button) findViewById(R.id.ButtonTTSV);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        txtUserName = (TextView) findViewById(R.id.textUserName);

    }
    public void accessUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            txtUserName.setText(email);
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }
    @Override
    protected void onRestart() {
    // TODO Auto-generated method stub
        super.onRestart();
        //Do your code here
        accessUserInformation();
    }
}
