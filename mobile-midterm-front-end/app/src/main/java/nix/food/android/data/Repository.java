package nix.food.android.data;

import nix.food.android.data.local.prefs.PreferencesService;
import nix.food.android.data.local.room.RoomService;
import nix.food.android.data.remote.ApiService;


public interface Repository {

    /**
     * ################################## Preference section ##################################
     */
    String getToken();
    void setToken(String token);

    PreferencesService getSharedPreferences();


    /**
     *  ################################## Remote api ##################################
     */
    ApiService getApiService();

    RoomService getRoomService();

}
