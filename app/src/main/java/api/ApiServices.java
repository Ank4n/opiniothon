package api;

import com.directions.entities.User;

import modal.LoginBody;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by abdul on 15/05/16.
 */
public interface ApiServices {


    //--------------------------------- Login -----------------------

    /***
     * @param body passing for get login
     * @return user information;
     */

    @POST("login")
    Call<User> doLogin(@Body LoginBody body);

}
