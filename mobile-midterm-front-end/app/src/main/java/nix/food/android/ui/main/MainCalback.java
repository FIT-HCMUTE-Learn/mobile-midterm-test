package nix.food.android.ui.main;

import nix.food.android.ui.base.activity.BaseCallback;

public interface MainCalback<T> extends BaseCallback {
    default void doSuccess(T object) {

    }
}