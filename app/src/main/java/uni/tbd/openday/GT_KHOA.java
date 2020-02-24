package uni.tbd.openday;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class GT_KHOA extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gt__khoa);
        TextView TenKhoa = (TextView) findViewById(R.id.ten_khoa);
        TextView TitleKhoa = (TextView) findViewById(R.id.title_khoa);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        switch (KHOA.khoa){
            case 1: {
                TenKhoa.setText(R.string.khoa01);
                TitleKhoa.setText(R.string.k_cntt1);
                break;
            }
            case 2:{
                TenKhoa.setText(R.string.khoa02);
                TitleKhoa.setText(R.string.k_nndlvh1);
                break;
            }
            case 3:{
                TenKhoa.setText(R.string.khoa03);
                TitleKhoa.setText(R.string.k_ktl1);
                break;
            }
        }
    }
}
