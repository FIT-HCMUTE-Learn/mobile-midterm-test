package nix.food.android.ui.main.home.adapter;
//TRANG KIM LOI - 22110371 - Le Tan Tru - 22110447
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
import nix.food.android.data.model.api.response.category.ProductResponse;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.FoodViewHolder> {
    private Context context;
    private List<ProductResponse> products;

    public ProductAdapter(Context context, List<ProductResponse> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        ProductResponse product = products.get(position);
        holder.name.setText(product.getName());
        Glide.with(context)
                .load(product.getImage())
                .error(R.drawable.hamburger)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setData(List<ProductResponse> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    public void updateItem(int position, ProductResponse updatedProduct) {
        products.set(position, updatedProduct);
        notifyItemChanged(position);
    }

    public void removeItem(int position) {
        products.remove(position);
        notifyItemRemoved(position);
    }

    public void clearItems() {
        products.clear();
        notifyDataSetChanged();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
        }
    }
}


