package uni.tbd.openday.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import uni.tbd.openday.R;

public class event_add extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);
    }
}
