package uni.tbd.openday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uni.tbd.openday.Activity.CategoryDetailActivity;
import uni.tbd.openday.Class.Category;
import uni.tbd.openday.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private List<Category> mCategories = new ArrayList<>();
    //@Bind(R.id.categoryNameTextView)
    TextView mCategoryNameTextView;
    private Context mContext;

    public CategoryViewHolder(View itemView, ArrayList<Category> categories) {
        super(itemView);

        mContext = itemView.getContext();
        //ButterKnife.bind(this, itemView);
        // Bottle added
        System.out.println("Good luck Boong 7 ");
        mCategoryNameTextView = itemView.findViewById(R.id.categoryNameTextView);
        mCategories = categories;
        System.out.println("Good luck Boong 8 ");
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, CategoryDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                //intent.putExtra("categories", Parcels.wrap(mCategories));
                mContext.startActivity(intent);
            }
        });
        System.out.println("Good luck Boong 9 ");
    }

    public void bindCategory(Category category) {
        mCategoryNameTextView.setText(category.getName());
    }
}
