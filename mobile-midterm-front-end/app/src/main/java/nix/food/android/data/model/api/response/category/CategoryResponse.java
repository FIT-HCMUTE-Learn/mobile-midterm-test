package nix.food.android.data.model.api.response.category;

import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String image;
    private String description;
}