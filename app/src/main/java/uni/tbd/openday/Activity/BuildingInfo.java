package uni.tbd.openday.Activity;

import android.app.Activity;
import android.os.Bundle;

import uni.tbd.openday.R;

public class BuildingInfo  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_building);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);
    }
}
