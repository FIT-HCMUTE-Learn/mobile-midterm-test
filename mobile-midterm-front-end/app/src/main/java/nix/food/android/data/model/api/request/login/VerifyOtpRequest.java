package nix.food.android.data.model.api.request.login;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String email;
    private String otp;
}

