package uni.tbd.openday.Module.profilesetup.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import uni.tbd.openday.MainActivity;
import uni.tbd.openday.Module.signin.view.PhotoPickDialog;
import uni.tbd.openday.R;
import uni.tbd.openday.Utils.ImageUtils;
import uni.tbd.openday.databinding.ActivityProfileSetupBinding;

public class ProfileSetupActivity extends AppCompatActivity {

    public static final int PROFILE_SETUP_WITH_GOOGLE = 0;
    public static final int PROFILE_SETUP_WITH_TWITTER = 1;
    public static final int PROFILE_SETUP_WITH_PHONE = 2;
    public static final int PROFILE_SETUP_WITH_FACEBOOK = 3;
    public static final int PROFILE_SETUP_WITH_EMAIL_AND_PASSWORD = 4;

    public static final int REQUEST_GALLERY_PICK = 4;
    public static final int REQUEST_CAMERA_PICK = 5;

    public static final String EXTRA_SETUP_METHOD = "uni.tbd.openday.module.profilesetup.view.ProfileSetupActivity.method";

    private ActivityProfileSetupBinding bind;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage storage;

    String tmp_chucvu;
    String thuocKhoa;
    private PhotoPickDialog dialog;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_profile_setup);
        ArrayList<String> arrayKhoa = new ArrayList<String>();
        arrayKhoa.add("Công Nghệ Thông Tin");
        arrayKhoa.add("Luật");
        arrayKhoa.add("Đông Phương Học");
        arrayKhoa.add("Du Lịch");
        arrayKhoa.add("Ngôn Ngữ Anh");
        arrayKhoa.add("Kế Toán");
        arrayKhoa.add("Tài Chính Ngân Hàng");
        arrayKhoa.add("Quản Trị Kinh Doanh");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayKhoa);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bind.spinnerKhoa.setAdapter(arrayAdapter);
        bind.spinnerKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thuocKhoa = arrayKhoa.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(ProfileSetupActivity.this,"Bạn chưa chọn Khoa",Toast.LENGTH_SHORT).show();
                return;
            }
        });
        boolean edit = false;

        switch (getIntent().getIntExtra(EXTRA_SETUP_METHOD, -1)) {
            case PROFILE_SETUP_WITH_GOOGLE :
                bind.methodImg.setImageResource(R.drawable.logo_google);
                bind.methodTv.setText(R.string.google);
                break;
            case PROFILE_SETUP_WITH_FACEBOOK :
                bind.methodImg.setImageResource(R.drawable.logo_facebook);
                bind.methodTv.setText(R.string.facebook);
                break;
            case PROFILE_SETUP_WITH_TWITTER :
                bind.methodImg.setImageResource(R.drawable.logo_twitter);
                bind.methodTv.setText(R.string.twitter);
                break;
            case PROFILE_SETUP_WITH_PHONE :
                bind.methodImg.setImageResource(R.drawable.logo_phone);
                bind.methodTv.setText(R.string.phone_sms);
                break;
            case PROFILE_SETUP_WITH_EMAIL_AND_PASSWORD :
                bind.methodImg.setImageResource(R.drawable.logo_login);
                bind.methodTv.setText(R.string.email_and_password);
                break;
            default:
                edit = true;
                bind.methodImg.setImageResource(R.drawable.icon_profile_setup);
                bind.methodTv.setText(R.string.profile_setup);
                break;
        }

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            if (user.getEmail() != null) bind.info.setText(user.getEmail()); else
                if (user.getPhoneNumber() != null) bind.info.setText(user.getPhoneNumber());
            if (user.getDisplayName() != null) bind.nickname.setText(user.getDisplayName());
            if (user.getPhotoUrl() != null) {
                if (edit) {
                    Picasso.get().load(user.getPhotoUrl()).into(bind.photo);
                } else {
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setPhotoUri(null)
                            .build();
                    user.updateProfile(profileChangeRequest);
                }
            }
        } else finish();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading Image...");
        progressDialog.setCancelable(false);
        if  (bind.isGiangvien.isChecked()){
            if (bind.isSinhvien.isChecked()){
                bind.isGiangvien.setChecked(false);
                Toast.makeText(ProfileSetupActivity.this,"Chỉ được chọn một",Toast.LENGTH_SHORT).show();
            }
        }
        bind.isGiangvien.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    bind.isSinhvien.setVisibility(View.GONE);
                }else {
                    bind.isSinhvien.setVisibility(View.VISIBLE);
                }
                tmp_chucvu ="Giảng viên Khoa ";
            }
        });
        bind.isSinhvien.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    bind.isGiangvien.setVisibility(View.GONE);
                }
                else {
                    bind.isGiangvien.setVisibility(View.VISIBLE);
                }
                tmp_chucvu ="Sinh viên Khoa ";
            }
        });
        bind.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String chucvu = new String();
                    Integer ischucvu = null;
                    String nickname = bind.nickname.getText().toString();
                    FirebaseUser user = auth.getCurrentUser();
                    if (nickname.isEmpty()) {
                        showToast(R.string.empty_field);
                        return;
                    }
                    if(bind.isGiangvien.isChecked()){
                        ischucvu = 1;
                        chucvu = tmp_chucvu + thuocKhoa;
                    }else if (bind.isSinhvien.isChecked()){
                        ischucvu = 0;
                        chucvu = tmp_chucvu + thuocKhoa;
                    }
                    if (user.getPhotoUrl() != null) {
                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(nickname)
                                .build();
                        user.updateProfile(profileChangeRequest);
                        database.getReference().child("user").child(user.getUid()).child("name").setValue(nickname);
                        database.getReference().child("user").child(user.getUid()).child("chuc_vu").setValue(chucvu);
                        database.getReference().child("user").child(user.getUid()).child("isChucvu").setValue(ischucvu);
                        String email = auth.getCurrentUser().getEmail();
                        if (email == null) email = auth.getCurrentUser().getPhoneNumber();
                        database.getReference().child("user").child(user.getUid()).child("email").setValue(email);
                        startActivity(new Intent(ProfileSetupActivity.this, MainActivity.class));
                        finish();
                    }else {
                        showToast(R.string.profile_image_empty_field);
                        return;
                    }
            }
        });

        dialog = new PhotoPickDialog();

        bind.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getSupportFragmentManager(), "mytg");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_PICK && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                updatePhoto(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == REQUEST_CAMERA_PICK && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            updatePhoto(imageBitmap);
        }
    }

    private void updatePhoto(Bitmap bitmap) {
        bind.photo.setImageBitmap(bitmap);

        Bitmap result = ImageUtils.getCircularBitmap(bitmap);
        bind.photo.setImageBitmap(result);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        progressDialog.show();
        dialog.dismiss();

        storage.getReference().child("user").child(auth.getCurrentUser().getUid()).putBytes(data).addOnFailureListener(ProfileSetupActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileSetupActivity.this, "Upload Error: " +
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(ProfileSetupActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while(!uri.isComplete());
                Uri url = uri.getResult();
                database.getReference().child("user").child(auth.getCurrentUser().getUid()).child("photo_url").setValue(url.toString());
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setPhotoUri(url)
                            .build();
                    auth.getCurrentUser().updateProfile(profileUpdates);
                progressDialog.cancel();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                double progressPercent = (100.00 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());

                progressDialog.setMessage(getResources().getString(R.string.loading)+" " + (int) progressPercent +"%");
            }
        });
    }

    public void showToast(@StringRes int res) {
        Toast.makeText(this, getResources().getString(res), Toast.LENGTH_SHORT).show();
    }
}
