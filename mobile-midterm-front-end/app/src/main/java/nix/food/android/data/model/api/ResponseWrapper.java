package nix.food.android.data.model.api;

import java.util.List;

//public class ResponseWrapper<T> {
//    private boolean result;
//    private T data;
//    private String message;
//    private String code;
//
//    public boolean isResult() {
//        return result;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public String getCode() {
//        return code;
//    }
//}
public class ResponseWrapper<T> {
    private boolean result;
    private DataWrapper<T> data;
    private String message;
    private String code;

    public boolean isResult() {
        return result;
    }

    public DataWrapper<T> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}

