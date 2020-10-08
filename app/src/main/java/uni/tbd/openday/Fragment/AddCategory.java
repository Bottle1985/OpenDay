package uni.tbd.openday.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uni.tbd.openday.Config;
import uni.tbd.openday.R;



public class AddCategory extends Fragment {
    private DatabaseReference mFirebaseDatabase;
    private String categoryId;
    private EditText newCategory;
    private Button addCategoryButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'category' node
        mFirebaseDatabase = mFirebaseInstance.getReference(Config.FIREBASE_CATEGORIES);
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        // Inflate the layout for this fragment
        newCategory = view.findViewById(R.id.newCategoryEditText);

        addCategoryButton = view.findViewById(R.id.addCategoryButton);

        addCategoryButton.setOnClickListener(this::add_Category);


        return view;
    }
    public void add_Category(View view) {
        String category = newCategory.getText().toString();
        // Check for already existed eventId
        if (TextUtils.isEmpty(categoryId)) {
            categoryId = mFirebaseDatabase.push().getKey();
        }
        mFirebaseDatabase.child(categoryId).setValue(category);
    }
}