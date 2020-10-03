package uni.tbd.openday.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uni.tbd.openday.Adapter.UserAdapter;
import uni.tbd.openday.Class.User;
import uni.tbd.openday.R;

public class CategoryList extends Fragment {
    private RecyclerView recyclerView;
    UserAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category_list, container, false);
        // Create a instance of the database and get
        // its reference
        mbase                = FirebaseDatabase.getInstance().getReference();

        recyclerView = view.findViewById(R.id.recyclerView);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<User> options
                = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(mbase, User.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new UserAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
        return view;
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}