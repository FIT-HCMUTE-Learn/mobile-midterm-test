package nix.food.android.data.remote;
// TRANG KIM LOI - 22110371 - Le Tan Tru - 22110447
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import nix.food.android.data.model.api.ResponseWrapper;
import nix.food.android.data.model.api.request.login.LoginRequest;
import nix.food.android.data.model.api.request.login.SignUpRequest;
import nix.food.android.data.model.api.request.login.VerifyOtpRequest;
import nix.food.android.data.model.api.response.category.CategoryResponse;
import nix.food.android.data.model.api.response.category.ProductResponse;
import nix.food.android.data.model.api.response.login.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/login")
    @Headers({"IgnoreAuth: 1"})
    Observable<LoginResponse> login(@Body LoginRequest request);

    @GET("api/v1/category/list")
    Observable<ResponseWrapper<CategoryResponse>> getAllCategories();

    @GET("api/v1/product/list")
    Observable<ResponseWrapper<ProductResponse>> getAllProducts();

    @POST("api/register")
    Observable<ResponseWrapper<String>> signUp(@Body SignUpRequest request);

    @POST("api/verify-otp")
    Observable<String> verifyOtp(@Body VerifyOtpRequest request);
}
