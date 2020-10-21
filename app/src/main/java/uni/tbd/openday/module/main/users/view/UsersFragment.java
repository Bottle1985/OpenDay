package uni.tbd.openday.module.main.users.view;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import uni.tbd.openday.R;
import uni.tbd.openday.databinding.FragmentUsersBinding;
import uni.tbd.openday.module.main.users.model.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikitagordia on 3/30/18.
 */

public class UsersFragment extends Fragment {

    private FragmentUsersBinding bind;
    private FirebaseDatabase db;
    private FirebaseAuth auth;

    private UsersAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();


        bind.userList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UsersAdapter(getContext(), getActivity());
        bind.userList.setAdapter(adapter);

        updateAdapter();

        bind.nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.setQuery(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bind.onlyFriends.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.getReference().child("user").child(auth.getCurrentUser().getUid()).child("friend_id").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final List<User> list = new LinkedList<User>();
                            long pos = 0, sz = dataSnapshot.getChildrenCount();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                db.getReference().child("user").child((String) snapshot.getValue()).addListenerForSingleValueEvent(new ValueListener((pos == sz - 1), list));
                                pos++;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                } else {
                    updateAdapter();
                }
            }
        });

        return bind.getRoot();
    }

    private void updateAdapter() {
        db.getReference().child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> list = new LinkedList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    user.setUid(snapshot.getKey());
                    list.add(user);
                }
                adapter.updateUser(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public class ValueListener implements ValueEventListener {

        private boolean last;
        private List<User> list;

        public ValueListener(boolean last, List<User> list) {
            this.last = last;
            this.list = list;
        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            list.add(dataSnapshot.getValue(User.class));
            if (last) adapter.updateUser(list);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}
