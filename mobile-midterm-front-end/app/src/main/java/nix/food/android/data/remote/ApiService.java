package nix.food.android.data.remote;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import nix.food.android.data.model.api.ResponseWrapper;
import nix.food.android.data.model.api.request.login.LoginRequest;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.data.model.api.response.login.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/login")
    @Headers({"IgnoreAuth: 1"})
    Observable<LoginResponse> login(@Body LoginRequest request);

    @GET("appfoods/categories.php")
    Observable<List<CategoryResponse>> getAllCategories();
}
