package uni.tbd.openday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SUKIEN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukien);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);
    }
}
