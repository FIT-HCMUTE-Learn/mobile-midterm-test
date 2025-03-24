package nix.food.android.data.model.api;

import java.util.List;

// Tạo lớp con để chứa `content`
public class DataWrapper<T> {
    private List<T> content;  // Trường content là danh sách
    private int totalElements;
    private int totalPages;

    public List<T> getContent() {
        return content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
