package uni.tbd.openday.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.firebase.database.Query;

import uni.tbd.openday.Class.Category;
import uni.tbd.openday.R;

public class FirebaseCategoryListAdapter extends FirebaseRecyclerAdapter<CategoryViewHolder, Category>{
    public FirebaseCategoryListAdapter(Query query, Class<Category> itemClass) {
        super(query, itemClass);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("Good luck Boong 5 ");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);
        System.out.println("Good luck Boong 6 ");
        return new CategoryViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bindCategory(getItem(position));
    }

    @Override
    protected void itemAdded(Category item, String key, int position) {}

    @Override
    protected void itemChanged(Category oldItem, Category newItem, String key, int position) {}

    @Override
    protected void itemRemoved(Category item, String key, int position) {}

    @Override
    protected void itemMoved(Category item, String key, int oldPosition, int newPosition) {}
}
