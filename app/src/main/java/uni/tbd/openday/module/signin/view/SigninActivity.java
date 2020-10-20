package uni.tbd.openday.module.signin.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import uni.tbd.openday.MainActivity;
import uni.tbd.openday.R;
import uni.tbd.openday.databinding.ActivitySigninBinding;
import uni.tbd.openday.module.profilesetup.view.ProfileSetupActivity;
import uni.tbd.openday.utils.Const;

public class SigninActivity extends AppCompatActivity {

    private static final int GOOGLE_REQUEST_CODE = 1;

    private ActivitySigninBinding bind;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private GoogleApiClient client;

    private OnCompleteListener<AuthResult> signInCallback;

    private ProgressDialog dialog;

    private View lastOpenImg, lastOpenTv;
    private int lastOpenId;

    private boolean isAnimated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bind = DataBindingUtil.setContentView(this, R.layout.activity_signin);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        isAnimated = false;

        dialog = new ProgressDialog(this);
        dialog.setMessage(getResources().getString(R.string.loading));
        dialog.setCancelable(false);

        signInCallback = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                final FirebaseUser user = auth.getCurrentUser();
                if (task.isSuccessful() && user != null) {
                    database.getReference().child("user").child(user.getUid()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dialog.cancel();

                            if (dataSnapshot.getValue() == null) {
                                if (lastOpenImg == null || lastOpenTv == null) {
                                    startActivity(new Intent(SigninActivity.this, ProfileSetupActivity.class));
                                    return;
                                }
                                Intent i = new Intent(SigninActivity.this, ProfileSetupActivity.class);
                                i.putExtra(ProfileSetupActivity.EXTRA_SETUP_METHOD, lastOpenId);
                                startActivity(i, ActivityOptionsCompat.makeSceneTransitionAnimation(SigninActivity.this,
                                        new Pair<View, String>(lastOpenImg, "provider_img"),
                                        new Pair<View, String>(lastOpenTv, "provider_tv")
                                ).toBundle());
                            } else {
                                startActivity(new Intent(SigninActivity.this, MainActivity.class));
                            }
                            finish();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            dialog.cancel();
                        }
                    });

                } else {
                    dialog.cancel();
                    showToast(R.string.wrong);
                    bind.password.setText("");
                }
            }
        };

        bind.center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnimated) return;
                isAnimated = true;
                showAnimation();
            }
        });

        googleSetup();
        emailPasswordSetup();
        phoneSetup();
        loginSetup();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        dialog.show();
        if (requestCode == GOOGLE_REQUEST_CODE) {
            GoogleSignInAccount account = Auth.GoogleSignInApi.getSignInResultFromIntent(data).getSignInAccount();
            if (account == null) {
                showToast(R.string.google_trouble);
                dialog.cancel();
                return;
            }
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            auth.signInWithCredential(credential).addOnCompleteListener(signInCallback);
        }
    }


    private void emailPasswordSetup() {
        bind.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bind.email.getText().toString().isEmpty() || bind.password.getText().toString().isEmpty()) {
                    showToast(R.string.empty_field);
                    return;
                }
                dialog.setTitle(R.string.server_loading);
                dialog.show();
                auth.createUserWithEmailAndPassword(bind.email.getText().toString(), bind.password.getText().toString()).addOnCompleteListener(signInCallback);
            }
        });
    }

    private void loginSetup() {
        bind.loginImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastOpenId = ProfileSetupActivity.PROFILE_SETUP_WITH_EMAIL_AND_PASSWORD;
                lastOpenImg = bind.loginImg;

                AlertDialog.Builder builder = new AlertDialog.Builder(SigninActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_email_password_holder, null);
                final EditText emailHolder = (EditText) view.findViewById(R.id.email_holder);
                final EditText passwordHolder = (EditText) view.findViewById(R.id.password_holder);
                final TextView done = (TextView) view.findViewById(R.id.done);
                builder.setView(view);
                final AlertDialog d = builder.create();
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d.show();
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.setTitle(R.string.server_loading);
                        dialog.show();
                        d.cancel();
                        emailPasswordSignin(emailHolder.getText().toString(), passwordHolder.getText().toString());
                    }
                });
            }
        });
    }

    private void phoneSetup() {
        bind.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastOpenId = ProfileSetupActivity.PROFILE_SETUP_WITH_PHONE;
                lastOpenImg = bind.phoneImg;
                lastOpenTv = bind.phoneTv;

                AlertDialog.Builder builder = new AlertDialog.Builder(SigninActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_phone_holder, null);
                final EditText phoneHolder = (EditText) view.findViewById(R.id.phone_holder);
                final TextView done = (TextView) view.findViewById(R.id.done);
                builder.setView(view);
                final AlertDialog d = builder.create();
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                d.show();
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.cancel();
                        phoneSignin(phoneHolder.getText().toString());
                    }
                });
            }
        });
    }

    private void googleSetup() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        dialog.cancel();
                        showToast(R.string.google_trouble);
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        bind.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastOpenImg = bind.googleImg;
                lastOpenTv = bind.googleTv;
                lastOpenId = ProfileSetupActivity.PROFILE_SETUP_WITH_GOOGLE;
                dialog.setTitle(R.string.google_loading);
                dialog.show();
                startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(client), GOOGLE_REQUEST_CODE);
            }
        });
    }

    private void phoneSignin(String number) {
        if (number.isEmpty()) return;
        dialog.setTitle(R.string.phone_loading);
        dialog.show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number, 60, TimeUnit.SECONDS, this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(signInCallback);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        showToast(R.string.server_trouble);
                    }
                });
    }

    private void emailPasswordSignin(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(signInCallback);
    }

    public void showToast(@StringRes int res) {
        Toast.makeText(this, getResources().getString(res), Toast.LENGTH_SHORT).show();
    }

    private void showAnimation() {

        showLoginText();

        showCreateText();

        hideTapHere();

        double r = 3d * Math.min(bind.area.getMeasuredWidth(), bind.area.getMeasuredHeight()) / 8d;
        double centerX = bind.area.getMeasuredWidth() / 2d;
        double centerY = bind.area.getMeasuredHeight() / 2d;

        showAnimation(bind.phone,
                (float)(centerX + r * Math.cos(Const.PHONE_ANGLE) - bind.phone.getMeasuredWidth() / 2d),
                (float)(centerY - r * Math.sin(Const.PHONE_ANGLE) - bind.phone.getMeasuredHeight() / 2d),
                Const.PHONE_SHOW_DURAION);

        showAnimation(bind.google,
                (float)(centerX + r * Math.cos(Const.GOOGLE_ANGLE) - bind.google.getMeasuredWidth() / 2d),
                (float)(centerY - r * Math.sin(Const.GOOGLE_ANGLE) - bind.google.getMeasuredHeight() / 2d),
                Const.GOOGLE_SHOW_DURAION);

        showAnimation(bind.loginImg,
                bind.area.getMeasuredWidth() / 2 - bind.loginImg.getMeasuredWidth() / 2,
                3 * bind.area.getMeasuredHeight() / 4 - bind.loginImg.getMeasuredHeight() / 2,
                Const.LOGIN_SHOW_DURATION);
    }

    private void hideTapHere() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(bind.tapHere, "alpha", 1f, 0);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    private void showLoginText() {
        bind.loginTv.setX(bind.area.getMeasuredWidth() / 2 - bind.loginTv.getMeasuredWidth() / 2);
        bind.loginTv.setY(3 * bind.area.getMeasuredHeight() / 4 + bind.loginImg.getMeasuredHeight() / 2 + bind.loginTv.getMeasuredHeight() / 2);
        bind.loginTv.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(bind.loginTv, "alpha", 0f, 1f).setDuration(Const.LOGIN_SHOW_DURATION);
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(bind.loginTv, "scaleX", 0.7f, 1f).setDuration(Const.LOGIN_SHOW_DURATION);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(bind.loginTv, "scaleY", 0.7f, 1f).setDuration(Const.LOGIN_SHOW_DURATION);
        animator.setInterpolator(new AccelerateInterpolator());
        animatorScaleX.setInterpolator(new DecelerateInterpolator());
        animatorScaleY.setInterpolator(new DecelerateInterpolator());
        animator.start();
        animatorScaleX.start();
        animatorScaleY.start();
    }

    private void showCreateText() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(bind.create, "scaleX", 1f, 0.8f).setDuration(Const.FACEBOOK_SHOW_DURATION);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(bind.create, "scaleY", 1f, 0.8f).setDuration(Const.FACEBOOK_SHOW_DURATION);
        animatorX.setInterpolator(new DecelerateInterpolator());
        animatorY.setInterpolator(new DecelerateInterpolator());
        animatorX.start();
        animatorY.start();
    }

    private void showAnimation(View v, float x, float y, int duration) {
        int fromY = bind.area.getMeasuredHeight();

        v.setX(x);
        v.setY(fromY);
        v.setVisibility(View.VISIBLE);

        AnimatorSet set = new AnimatorSet();

        ObjectAnimator animatorHeight = ObjectAnimator.ofFloat(v, "translationY", fromY, y).setDuration(duration);
        animatorHeight.setInterpolator(new DecelerateInterpolator());

        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(v, "scaleX", 0.8f, 1f).setDuration(duration);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(v, "scaleY", 0.8f, 1f).setDuration(duration);

        set.play(animatorHeight)
                .with(animatorScaleX)
                .with(animatorScaleY);

        set.start();
    }
}