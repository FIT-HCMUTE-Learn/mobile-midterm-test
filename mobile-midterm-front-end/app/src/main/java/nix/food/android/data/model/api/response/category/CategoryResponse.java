package nix.food.android.data.model.api.response.category;

import lombok.Data;

@Data
public class CategoryResponse {
    private int id;
    private String name;
    private String images;
    private String description;
}