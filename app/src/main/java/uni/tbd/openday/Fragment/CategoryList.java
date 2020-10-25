package uni.tbd.openday.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import uni.tbd.openday.Adapter.FirebaseCategoryListAdapter;
import uni.tbd.openday.Class.Category;
import uni.tbd.openday.Config;
import uni.tbd.openday.R;

public class CategoryList extends Fragment {
    private Query mQuery;
    private DatabaseReference mFirebaseDatabaseRef;
    private RecyclerView mRecyclerView;
    private FirebaseCategoryListAdapter mAdapter;


    public CategoryList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category_list, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        // Load data from database
        // get reference to 'category' node
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabaseRef = mFirebaseInstance.getReference();

        setUpFirebaseQuery();
        //setUpRecyclerView();

        return view;
    }

    private void setUpFirebaseQuery() {
        String category = mFirebaseDatabaseRef.toString();
        System.out.println("Good luck Bottle " + category);
        // Bottle need to check
        //mQuery = new Firebase(category);

        mQuery = mFirebaseDatabaseRef.child(Config.FIREBASE_CATEGORIES);
        System.out.println("Good luck Boong " + mQuery);
    }

    private void setUpRecyclerView() {
        System.out.println("Good luck Boong 1 " + mQuery);
        mAdapter = new FirebaseCategoryListAdapter(mQuery, Category.class);
        System.out.println("Good luck Boong 2 " + mQuery);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        System.out.println("Good luck Boong 3 " + mQuery);
        mRecyclerView.setAdapter(mAdapter);
        System.out.println("Good luck Boong 4 " + mQuery);
    }
}