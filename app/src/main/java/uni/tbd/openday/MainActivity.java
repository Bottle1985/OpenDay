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
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uni.tbd.openday.Activity.Ask_answer;
import uni.tbd.openday.Activity.Login;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static int mode_webview =0;
    TextView txtUserName;
    ViewFlipper viewFlipper;
    Animation in, out;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
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
        ActionBar();
        accessUserInformation();
        int imgflipper [] ={R.drawable.bg_giang_duong,R.drawable.bg_ky_tuc,R.drawable.bg_da_nang,R.drawable.cntt2,R.drawable.dongphuonghoc2,R.drawable.dulich2,R.drawable.ketoan2,R.drawable.luat2,R.drawable.ngonnguanh2,R.drawable.qtkd2,R.drawable.tcnh2};
        for (int image: imgflipper){
            setViewFlipper(image);
        }
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {super.onBackPressed();}

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
    private void AnhXa(){
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        txtUserName = (TextView) findViewById(R.id.textUserName);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_sukien:
                Intent intent_sukien = new Intent(MainActivity.this, SUKIEN.class);
                startActivity(intent_sukien);
                break;
            case R.id.nav_sodo:
                Intent intent_sodo = new Intent(MainActivity.this, ActivityBuilding.class);
                startActivity(intent_sodo);
                break;
            case R.id.nav_khoa:
                Intent intent_khoa = new Intent(MainActivity.this, KHOA.class);
                startActivity(intent_khoa);
                break;
            case R.id.nav_nganh:
                GT_KHOA.mode = 0;
                Intent intent_nganh = new Intent(MainActivity.this, NGANH.class);
                startActivity(intent_nganh);
                break;
            case R.id.nav_tuyensinh:
                mode_webview = 1;
                Intent intent_tuyensinh = new Intent(MainActivity.this, webview.class);
                startActivity(intent_tuyensinh);
                break;
            case R.id.nav_ask:
                Intent intent_ask = new Intent(MainActivity.this, Ask_answer.class);
                startActivity(intent_ask);
                break;
            case R.id.nav_tracnghiem:
                Intent intent_tracnghiem = new Intent(MainActivity.this, TRAC_NGHIEM.class);
                startActivity(intent_tracnghiem);
                break;
            case R.id.nav_daotao:
                mode_webview = 2;
                Intent intent_daotao = new Intent(MainActivity.this, webview.class);
                startActivity(intent_daotao);
                break;
            case R.id.nav_dangnhap:
                Intent intent_dangnhap = new Intent(MainActivity.this, uni.tbd.openday.Activity.Login.class);
                startActivity(intent_dangnhap);
                break;
        }

        return false;
    }
}
