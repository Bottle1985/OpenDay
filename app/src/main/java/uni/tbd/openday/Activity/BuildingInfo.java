package uni.tbd.openday.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import uni.tbd.openday.ActivityBuilding;
import uni.tbd.openday.R;


public class BuildingInfo  extends AppCompatActivity {
    public final int BUILDING_GIANG_DUONG = 0;
    public final int BUILDING_KY_TUC_XA = 1;
    public final int BUILDING_DA_NANG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_building);
        this.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
        }
        ImageView imgBuilding = (ImageView)findViewById(R.id.ImageBuilding);
        TextView txtBuildingName = (TextView)findViewById(R.id.building_name);
        TextView txtBuildingDirect= (TextView)findViewById(R.id.building_direct);
        if (event_info.mode == 0){
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
        }else{
            switch (event_info.id_building)
            {
                case BUILDING_GIANG_DUONG:
                    imgBuilding.setImageResource(R.drawable.bg_giang_duong);
                    txtBuildingName.setText(R.string.Giang_duong);
                    txtBuildingDirect.setText(R.string.chi_dan_giang_duong);
                    event_info.id_building = 0;
                    break;
                case BUILDING_DA_NANG:
                    imgBuilding.setImageResource(R.drawable.bg_da_nang);
                    txtBuildingName.setText(R.string.Thuc_hanh);
                    event_info.id_building = 0;
                    break;
                case BUILDING_KY_TUC_XA:
                    event_info.id_building = 0;
                    imgBuilding.setImageResource(R.drawable.bg_ky_tuc);
                    txtBuildingName.setText(R.string.Ky_tuc_xa);
                    break;
                default:
                    break;
            }
            event_info.mode=0;
        }

    }
}
