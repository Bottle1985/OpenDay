package uni.tbd.openday.module.postdetail.view.likedialog;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import uni.tbd.openday.R;
import uni.tbd.openday.databinding.LayoutLikeHolderBinding;
import uni.tbd.openday.module.main.users.model.User;
import uni.tbd.openday.utils.ImageUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikitagordia on 4/3/18.
 */

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.LikeHolder> {

    private List<User> list;
    private Context context;

    public LikeAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public LikeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LikeHolder(LayoutLikeHolderBinding.inflate(LayoutInflater.from(context)));
    }

    public void addUser(User user) {
        int x = list.size();
        list.add(user);
        notifyItemInserted(x);
    }

    @Override
    public void onBindViewHolder(LikeHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LikeHolder extends RecyclerView.ViewHolder {

        private LayoutLikeHolderBinding bind;

        public LikeHolder(LayoutLikeHolderBinding bind) {
            super(bind.getRoot());
            this.bind = bind;
        }

        public void bind(User user) {
            bind.nickname.setText(user.getName());

            if (user.getPhoto_url() != null) Picasso.get().load(user.getPhoto_url()).placeholder(R.drawable.user_photo_holder).resize(ImageUtils.SIZE_XXL, ImageUtils.SIZE_XXL).into(bind.photo);
        }
    }
}
