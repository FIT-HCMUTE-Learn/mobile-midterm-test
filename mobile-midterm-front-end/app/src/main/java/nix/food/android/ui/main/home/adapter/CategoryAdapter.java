package nix.food.android.ui.main.home.adapter;
//TRANG KIM LOI - 22110371 - Le Tan Tru - 22110447
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import nix.food.android.R;
import nix.food.android.data.model.api.response.category.CategoryResponse;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<CategoryResponse> array;
    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(String categoryName);
    }

    public CategoryAdapter(Context context, List<CategoryResponse> array, OnCategoryClickListener listener) {
        this.context = context;
        this.array = array;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categories, null);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryResponse category = array.get(position);
        holder.nameCate.setText(category.getName());
        holder.id_cate.setText(String.valueOf(category.getId()));
        Glide.with(context).load(category.getImage()).into(holder.images);
    }

    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public ImageView images;
        public TextView nameCate, id_cate;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.image_cate);
            nameCate = itemView.findViewById(R.id.tvNameCategory);
            id_cate = itemView.findViewById(R.id.id_cate);


            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onCategoryClick(id_cate.getText().toString());
                }
            });
        }
    }
}
