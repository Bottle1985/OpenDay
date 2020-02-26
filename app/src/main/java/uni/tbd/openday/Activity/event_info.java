package uni.tbd.openday.Activity;

import android.app.Activity;
import android.os.Bundle;

import uni.tbd.openday.R;

public class event_info extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_event);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);
    }
}
