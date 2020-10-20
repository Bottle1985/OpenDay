package uni.tbd.openday.module.postdetail.view.commentsdialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uni.tbd.openday.R;
import uni.tbd.openday.databinding.LayoutCommentHolderBinding;
import uni.tbd.openday.module.postdetail.model.CommentUser;
import uni.tbd.openday.module.profile.view.ProfileActivity;
import uni.tbd.openday.utils.ImageUtils;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {

    private List<CommentUser> list;

    private Context context;
    private Activity activity;
    private RecyclerView recyclerView;

    public CommentAdapter(Context context, Activity activity) {
        list = new ArrayList<>();
        this.context = context;
        this.activity = activity;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void addComment(CommentUser commentUser) {
        list.add(0, commentUser);
        notifyItemInserted(0);
        if (recyclerView != null) recyclerView.scrollToPosition(0);
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutCommentHolderBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {

        private CommentUser commentUser;

        private View.OnClickListener onClickShowUser = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(ProfileActivity.getIntent(commentUser.getOwner_id(), context), ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                        new Pair<View, String>(bind.nickname, "nickname"),
                        new Pair<View, String>(bind.photo, "photo")
                ).toBundle());
            }
        };

        private LayoutCommentHolderBinding bind;

        public CommentHolder(LayoutCommentHolderBinding bind) {
            super(bind.getRoot());
            this.bind = bind;
        }

        public void bind(CommentUser commentUser) {
            this.commentUser = commentUser;
            bind.nickname.setText(commentUser.getOwner_name());
            bind.context.setText(commentUser.getContext());

            bind.nickname.setOnClickListener(onClickShowUser);
            bind.photo.setOnClickListener(onClickShowUser);

            if (commentUser.getOwner_photo_url() != null) Picasso.get().load(commentUser.getOwner_photo_url()).placeholder(R.drawable.user_photo_holder).resize(ImageUtils.SIZE_L, ImageUtils.SIZE_L).into(bind.photo);
        }
    }
}
