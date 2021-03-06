package uni.tbd.openday.Module.main.users.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import uni.tbd.openday.Module.main.users.model.User;
import uni.tbd.openday.Module.profile.view.ProfileActivity;
import uni.tbd.openday.R;
import uni.tbd.openday.Utils.ImageUtils;
import uni.tbd.openday.databinding.LayoutUserHolderBinding;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder>{

    private Context context;
    private Activity activity;

    private List<User> list, filter;
    private String query;

    public UsersAdapter(Context context, Activity activity) {
        list = new LinkedList<>();
        filter = new LinkedList<>();
        query = "";
        this.context = context;
        this.activity = activity;
    }

    public void updateUser(List<User> users) {
        list.clear();
        list.addAll(users);
        refreshFilter();
    }

    public void refreshFilter() {
        filter.clear();
        for (int i = 0; i < list.size(); i++)
            if (valid(list.get(i).getName())) filter.add(list.get(i));
        notifyDataSetChanged();
    }

    public void setQuery(String query) {
        this.query = query.toLowerCase();
        refreshFilter();
    }

    private boolean valid(String name) {
        if (query.isEmpty()) return true;
        return name.toLowerCase().contains(query);
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserHolder(LayoutUserHolderBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.bind(filter.get(position));
    }

    @Override
    public int getItemCount() {
        return filter.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LayoutUserHolderBinding bind;

        private User user;

        public UserHolder(LayoutUserHolderBinding bind) {
            super(bind.getRoot());
            this.bind = bind;
            bind.body.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (user == null || user.getUid() == null || user.getUid().isEmpty()) return;
            context.startActivity(ProfileActivity.getIntent(user.getUid(), context), ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                    new Pair<View, String>(bind.userName, "nickname"),
                    new Pair<View, String>(bind.userEmail, "email"),
                    new Pair<View, String>(bind.userChucvu, "chuc_vu"),
                    new Pair<View, String>(bind.photo, "photo")
            ).toBundle());
        }

        public void bind(User user) {
            this.user = user;
            bind.userName.setText(user.getName());
            bind.userEmail.setText(user.getEmail());
            bind.userChucvu.setText(user.getChuc_vu());
            if (user.getPhoto_url() != null) Picasso.get().load(user.getPhoto_url()).placeholder(R.drawable.user_photo_holder).resize(ImageUtils.SIZE_XXL, ImageUtils.SIZE_XXL).into(bind.photo);
        }
    }
}
