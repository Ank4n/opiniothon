package api;

import com.squareup.okhttp.OkHttpClient;

import retrofit.MoshiConverterFactory;
import retrofit.Retrofit;

/**
 * Created by abdul on 15/05/16.
 */
public class RestClient {

    private static ApiServices apiServices;

    public static ApiServices getApiService() {
        if (apiServices != null)
            return apiServices;
        OkHttpClient httpClient = new OkHttpClient();
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(AppConstants.API_URL)//passing API_URL
                .addConverterFactory(MoshiConverterFactory.create()) //passing MoshiConverterFactory to convert json key and value into our object
                .client(httpClient)//passing OkHttpClient object
                .build();
        apiServices = restAdapter.create(ApiServices.class);
        return apiServices;
    }

}
