package uni.tbd.openday.module.signin.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import uni.tbd.openday.R;
import uni.tbd.openday.databinding.DialogPhotoPickBinding;
import uni.tbd.openday.module.profilesetup.view.ProfileSetupActivity;


public class PhotoPickDialog extends BottomSheetDialogFragment {

    private DialogPhotoPickBinding bind;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        bind = DialogPhotoPickBinding.inflate(LayoutInflater.from(getContext()));
        dialog.setContentView(bind.getRoot());

        bind.cameraPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    getActivity().startActivityForResult(takePictureIntent, ProfileSetupActivity.REQUEST_CAMERA_PICK);
                }
            }
        });

        bind.galleryPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                getActivity().startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_photo)), ProfileSetupActivity.REQUEST_GALLERY_PICK);
            }
        });
    }
}
