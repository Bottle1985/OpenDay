package uni.tbd.openday.module.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import uni.tbd.openday.MainActivity;
import uni.tbd.openday.R;
import uni.tbd.openday.module.signin.view.SigninActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) startActivity(new Intent(this, SigninActivity.class));
                                                        else startActivity(new Intent(this, MainActivity.class));

        finish();
    }
}
