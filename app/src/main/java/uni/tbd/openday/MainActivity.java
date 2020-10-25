package uni.tbd.openday;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import uni.tbd.openday.Module.main.Chats;
import uni.tbd.openday.Module.main.users.model.User;
import uni.tbd.openday.Module.signin.view.SigninActivity;
import uni.tbd.openday.Utils.ImageUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static int mode_webview =0;
    Animation in, out;
    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private User user;
    private FirebaseMessaging messaging;
    CardView btn_card1,btn_card2,btn_card3,btn_card4,btn_card5,btn_card6;
    Button btn_login;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Open activity list building

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        messaging = FirebaseMessaging.getInstance();
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
        ButtonAction ();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            MenuAction(true);
            View view = View.inflate(this, R.layout.header_after_login, null);
            navigationView.addHeaderView(view);
            accessUserInformation();
        }else {
            MenuAction(false);
            View view = View.inflate(this, R.layout.header_no_login, null);
            navigationView.addHeaderView(view);
            View headerView = navigationView.getHeaderView(0);
            btn_login = (Button) headerView.findViewById(R.id.btn_login);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_login = new Intent(MainActivity.this, SigninActivity.class);
                    startActivity(intent_login);
                }
            });
        }

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
    public boolean MenuAction(boolean check){
        Menu menu =navigationView.getMenu();
        menu.findItem(R.id.nav_messenger).setVisible(check);
        menu.findItem(R.id.nav_logout).setVisible(check);
        menu.findItem(R.id.nav_daotao).setVisible(check);
        return check;
    }
    public void ButtonAction (){
        btn_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_sukien = new Intent(MainActivity.this, SUKIEN.class);
                startActivity(intent_sukien);
            }
        });
        btn_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode_webview = 1;
                Intent intent_tuyensinh = new Intent(MainActivity.this, webview.class);
                startActivity(intent_tuyensinh);
            }
        });
        btn_card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GT_KHOA.mode = 0;
                Intent intent_nganh = new Intent(MainActivity.this, NGANH.class);
                startActivity(intent_nganh);
            }
        });
        btn_card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_khoa = new Intent(MainActivity.this, KHOA.class);
                startActivity(intent_khoa);
            }
        });
        btn_card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_sodo = new Intent(MainActivity.this, ActivityBuilding.class);
                startActivity(intent_sodo);
            }
        });
        btn_card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_tracnghiem = new Intent(MainActivity.this, TRAC_NGHIEM.class);
                startActivity(intent_tracnghiem);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {super.onBackPressed();}

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
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        btn_card1 = (CardView) findViewById(R.id.card1);
        btn_card2 = (CardView) findViewById(R.id.card2);
        btn_card3 = (CardView) findViewById(R.id.card3);
        btn_card4 = (CardView) findViewById(R.id.card4);
        btn_card5 = (CardView) findViewById(R.id.card5);
        btn_card6 = (CardView) findViewById(R.id.card6);

    }
    public void accessUserInformation(){
        if(auth.getCurrentUser()== null)
            return;
        db.getReference().child("user").child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                View headerView = navigationView.getHeaderView(0);
                TextView txtEmail = (TextView) headerView.findViewById(R.id.textView);
                TextView txtName = (TextView) headerView.findViewById(R.id.textUserName);
                ImageView profile_image = (ImageView) headerView.findViewById(R.id.profile_image);
                user.setUid(auth.getCurrentUser().getUid());
                txtEmail.setText(user.getEmail());
                txtName.setText(user.getName());
                Picasso.get().load(user.getPhoto_url()).placeholder(R.drawable.user_photo_holder).placeholder(R.drawable.user_photo_holder).resize(ImageUtils.SIZE_XXL, ImageUtils.SIZE_XXL).into(profile_image);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    @Override
    protected void onRestart() {
    // TODO Auto-generated method stub
        super.onRestart();
        //Do your code here
//        accessUserInformation();
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
            case R.id.nav_messenger:
                Intent intent_ask = new Intent(MainActivity.this, Chats.class);
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
            case R.id.nav_logout:
                db.getReference().child("user").child(auth.getCurrentUser().getUid()).child("chat_id").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snap : dataSnapshot.getChildren())
                            messaging.unsubscribeFromTopic((String)snap.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                auth.signOut();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
                break;
        }

        return false;
    }
}
