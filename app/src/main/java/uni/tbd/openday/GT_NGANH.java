package uni.tbd.openday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GT_NGANH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gt__nganh);
        TextView TenNganh = (TextView) findViewById(R.id.ten_nganh);
        TextView MaNganh = (TextView) findViewById(R.id.ma_nganh);
        TextView GTNganh = (TextView) findViewById(R.id.gt_nganh);
        TextView ChuyenNganh = (TextView) findViewById(R.id.cac_chuyen_nganh);
        TextView CoHoiViecLam = (TextView) findViewById(R.id.co_hoi_vl);
        ImageView IMGNganh = (ImageView)findViewById(R.id.img_nganh);
        ImageView IMGXetTuyen = (ImageView)findViewById(R.id.img_dkts);
        switch (NGANH.nganh){
            case 0: {
                TenNganh.setText(R.string.nganh0);
                MaNganh.setText(R.string.ma_nganh_luat);
                GTNganh.setText(R.string.gt_nganh_luat);
                ChuyenNganh.setText(R.string.cac_chuyen_nganh_luat);
                CoHoiViecLam.setText(R.string.co_hoi_vl_luat);
                IMGNganh.setImageResource(R.drawable.luat);
                IMGXetTuyen.setImageResource(R.drawable.xt_luat);
                break;
            }
            case 1:{
                TenNganh.setText(R.string.nganh1);
                MaNganh.setText(R.string.ma_nganh_dong_phuong_hoc);
                GTNganh.setText(R.string.gt_nganh_dong_phuong_hoc);
                ChuyenNganh.setText(R.string.cac_chuyen_nganh_dong_phuong_hoc);
                CoHoiViecLam.setText(R.string.co_hoi_vl_dong_phuong_hoc);
                IMGNganh.setImageResource(R.drawable.dongphuonghoc);
                IMGXetTuyen.setImageResource(R.drawable.xt_dong_phuong_hoc);
                break;
            }
            case 2:{
                TenNganh.setText(R.string.nganh2);
                MaNganh.setText(R.string.ma_nganh_du_lich);
                GTNganh.setText(R.string.gt_nganh_du_lich);
                ChuyenNganh.setText(R.string.cac_chuyen_nganh_cntt);
                CoHoiViecLam.setText(R.string.co_hoi_vl_du_lich);
                IMGNganh.setImageResource(R.drawable.dulich);
                IMGXetTuyen.setImageResource(R.drawable.xt_du_lich);
                break;
            }
            case 3:{
                TenNganh.setText(R.string.nganh3);
                MaNganh.setText(R.string.ma_nganh_cntt);
                GTNganh.setText(R.string.gt_nganh_cntt);
                ChuyenNganh.setText(R.string.cac_chuyen_nganh_cntt);
                CoHoiViecLam.setText(R.string.co_hoi_vl_cntt);
                IMGNganh.setImageResource(R.drawable.cntt);
                IMGXetTuyen.setImageResource(R.drawable.xt_cntt);
                break;
            }
            case 4:{
                TenNganh.setText(R.string.nganh4);
                MaNganh.setText(R.string.ma_nganh_ngon_ngu_anh);
                GTNganh.setText(R.string.gt_nganh_ngon_ngu_anh);
                ChuyenNganh.setText(R.string.cac_chuyen_nganh_ngon_ngu_anh);
                CoHoiViecLam.setText(R.string.co_hoi_vl_ngon_ngu_anh);
                IMGNganh.setImageResource(R.drawable.ngonguanh);
                IMGXetTuyen.setImageResource(R.drawable.xt_ngon_ngu_anh);
                break;
            }
            case 5:{
                TenNganh.setText(R.string.nganh5);
                MaNganh.setText(R.string.ma_nganh_ke_toan);
                GTNganh.setText(R.string.gt_nganh_ke_toan);
                ChuyenNganh.setText(R.string.cac_chuyen_nganh_ke_toan);
                CoHoiViecLam.setText(R.string.co_hoi_vl_ke_toan);
                IMGNganh.setImageResource(R.drawable.ketoan);
                IMGXetTuyen.setImageResource(R.drawable.xt_ke_toan);
                break;
            }
            case 6:{
                TenNganh.setText(R.string.nganh6);
                MaNganh.setText(R.string.ma_nganh_tc_nn);
                GTNganh.setText(R.string.gt_nganh_tc_nn);
                ChuyenNganh.setText(R.string.cac_chuyen_nganh_tc_nn);
                CoHoiViecLam.setText(R.string.co_hoi_vl_tc_nn);
                IMGNganh.setImageResource(R.drawable.taichinhnganhang);
                IMGXetTuyen.setImageResource(R.drawable.xt_tc_nh);
                break;
            }
            case 7:{
                TenNganh.setText(R.string.nganh7);
                MaNganh.setText(R.string.ma_nganh_qtkd);
                GTNganh.setText(R.string.gt_nganh_qtkd);
                ChuyenNganh.setText(R.string.cac_chuyen_nganh_qtkd);
                CoHoiViecLam.setText(R.string.co_hoi_vl_qtkd);
                IMGNganh.setImageResource(R.drawable.quantrikinhdoanh);
                IMGXetTuyen.setImageResource(R.drawable.xt_qtkd);
                break;
            }
        }
    }
}
