package uni.tbd.openday.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import uni.tbd.openday.ActivityBuilding;
import uni.tbd.openday.R;


public class BuildingInfo  extends Activity {
    public final int BUILDING_GIANG_DUONG = 0;
    public final int BUILDING_KY_TUC_XA = 1;
    public final int BUILDING_DA_NANG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_building);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);
        ImageView imgBuilding = (ImageView)findViewById(R.id.ImageBuilding);
        TextView txtBuildingName = (TextView)findViewById(R.id.building_name);
        TextView txtBuildingDirect= (TextView)findViewById(R.id.building_direct);
        switch (ActivityBuilding.id_building)
        {
            case BUILDING_GIANG_DUONG:
                imgBuilding.setImageResource(R.drawable.bg_giang_duong);
                txtBuildingName.setText(R.string.Giang_duong);
                txtBuildingDirect.setText(R.string.chi_dan_giang_duong);
                break;
            case BUILDING_DA_NANG:
                imgBuilding.setImageResource(R.drawable.bg_da_nang);
                txtBuildingName.setText(R.string.Thuc_hanh);
                break;
            case BUILDING_KY_TUC_XA:
                imgBuilding.setImageResource(R.drawable.bg_ky_tuc);
                txtBuildingName.setText(R.string.Ky_tuc_xa);
                break;
            default:
                break;
        }
    }
}
