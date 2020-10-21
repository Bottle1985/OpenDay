package uni.tbd.openday.module.postdetail.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import uni.tbd.openday.R;
import uni.tbd.openday.databinding.ActivityPostDetailBinding;
import uni.tbd.openday.module.main.profile.model.BlogPost;
import uni.tbd.openday.module.postdetail.view.commentsdialog.CommentsDialog;
import uni.tbd.openday.module.postdetail.view.likedialog.LikesDialog;
import uni.tbd.openday.module.profile.view.ProfileActivity;
import uni.tbd.openday.utils.ImageUtils;
import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "uni.tbd.openday.module.postdetail.view.PostDetailActivity.id";
    private static final String EXTRA_NAME = "uni.tbd.openday.module.postdetail.view.PostDetailActivity.name";
    private static final String EXTRA_OWNER_ID = "uni.tbd.openday.module.postdetail.view.PostDetailActivity.owner_id";
    private static final String EXTRA_OWNER_PHOTO_URL = "uni.tbd.openday.module.postdetail.view.PostDetailActivity.owner_photo_url";
    private static final String EXTRA_DATE = "uni.tbd.openday.module.postdetail.view.PostDetailActivity.date";
    private static final String EXTRA_CONTENT = "uni.tbd.openday.module.postdetail.view.PostDetailActivity.context";
    public static final String EXTRA_LIKE = "uni.tbd.openday.module.postdetail.view.PostDetailActivity.like";
    public static final String EXTRA_COMMENT = "uni.tbd.openday.module.postdetail.view.PostDetailActivity.comment";
    public static final String EXTRA_VIEW = "uni.tbd.openday.module.postdetail.view.PostDetailActivity.view";

    private ActivityPostDetailBinding bind;

    private String ownerId, postId;

    private CommentsDialog dialogComments;
    private LikesDialog dialogLikes;

    private FirebaseDatabase db;
    private FirebaseAuth auth;

    private View.OnClickListener onClickShowUser = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (ownerId == null || ownerId.isEmpty()) return;
            startActivity(ProfileActivity.getIntent(ownerId, PostDetailActivity.this), ActivityOptionsCompat.makeSceneTransitionAnimation(PostDetailActivity.this,
                    new Pair<View, String>(bind.nickname, "nickname"),
                    new Pair<View, String>(bind.photo, "photo")
            ).toBundle());
        }
    };
    
    public static Intent getIntent(Context context, BlogPost post) {
        return new Intent(context, PostDetailActivity.class)
                .putExtra(EXTRA_ID, post.getId())
                .putExtra(EXTRA_NAME, post.getOwner_name())
                .putExtra(EXTRA_OWNER_ID, post.getOwner_id())
                .putExtra(EXTRA_DATE, post.getDate())
                .putExtra(EXTRA_CONTENT, post.getContent())
                .putExtra(EXTRA_LIKE, post.getLike())
                .putExtra(EXTRA_COMMENT, post.getComment())
                .putExtra(EXTRA_VIEW, post.getView())
                .putExtra(EXTRA_OWNER_PHOTO_URL, post.getOwner_photo_url());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_post_detail);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        Intent i = getIntent();
        if (i != null) {
            bind.nickname.setText(i.getStringExtra(EXTRA_NAME));
            bind.date.setText(i.getStringExtra(EXTRA_DATE));
            bind.content.setText(i.getStringExtra(EXTRA_CONTENT));

            ownerId = i.getStringExtra(EXTRA_OWNER_ID);
            postId = i.getStringExtra(EXTRA_ID);

            bind.like.setText(getResources().getString(R.string.like_cnt, i.getLongExtra(EXTRA_LIKE, 0)));
            bind.comment.setText(getResources().getString(R.string.comment_cnt, i.getLongExtra(EXTRA_COMMENT, 0)));
            bind.view.setText(getResources().getString(R.string.view_cnt, i.getLongExtra(EXTRA_VIEW, 0)));

            String photo = i.getStringExtra(EXTRA_OWNER_PHOTO_URL);
            if (photo != null) Picasso.get().load(photo).placeholder(R.drawable.user_photo_holder).resize(ImageUtils.SIZE_XL, ImageUtils.SIZE_XL).into(bind.photo);

            db.getReference().child("post").child(postId).child("view_id").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snap : dataSnapshot.getChildren())
                        if (auth.getCurrentUser().getUid().equals((String)snap.getValue())) return;
                    db.getReference().child("post").child(postId).child("view_id").push().setValue(auth.getCurrentUser().getUid());
                    db.getReference().child("post").child(postId).child("view").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final long x = (long)dataSnapshot.getValue() + 1;
                            db.getReference().child("post").child(postId).child("view").setValue(x).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    bind.view.setText(getResources().getString(R.string.view_cnt, x));
                                    updateResult();
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        bind.nickname.setOnClickListener(onClickShowUser);
        bind.photo.setOnClickListener(onClickShowUser);

        dialogComments = CommentsDialog.getDialog(postId);
        dialogComments.setCallback(new CommentsDialog.OnCloseListener() {
            @Override
            public void onClose() {
                updateResult();
            }
        });

        dialogLikes = LikesDialog.getDialogInstance(postId);

        bind.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.getReference().child("post").child(postId).child("like_id").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snap : dataSnapshot.getChildren())
                            if (auth.getCurrentUser().getUid().equals((String)snap.getValue())) return;
                        db.getReference().child("post").child(postId).child("like_id").push().setValue(auth.getCurrentUser().getUid());
                        db.getReference().child("post").child(postId).child("like").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final long x = (long)dataSnapshot.getValue() + 1;
                                db.getReference().child("post").child(postId).child("like").setValue(x).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        bind.like.setText(getResources().getString(R.string.like_cnt, x));
                                        updateResult();
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        bind.showComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogComments.show(getSupportFragmentManager(), "mytg");
            }
        });

        bind.showLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLikes.show(getSupportFragmentManager(), "mytg");
            }
        });
    }

    public void updateResult() {
        db.getReference().child("post").child(postId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Intent i = new Intent();
                i.putExtra(EXTRA_LIKE, (Long)dataSnapshot.child("like").getValue());
                i.putExtra(EXTRA_COMMENT, (Long)dataSnapshot.child("comment").getValue());
                i.putExtra(EXTRA_VIEW, (Long)dataSnapshot.child("view").getValue());
                i.putExtra(EXTRA_ID, postId);
                setResult(Activity.RESULT_OK, i);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void commentAdded(long x) {
        bind.comment.setText(getResources().getString(R.string.comment_cnt, x));
    }
}
