package data_source;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Menu;
import model.OrderState;
import model.Restaurant;
import model.Review;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static Repository sRepository;
    private static List<Restaurant> sRestaurantList;
    private static List<OrderState> sOrderStateList;
    private static List<Menu> sMenuList;
    private static List<Review> sReviewList;

    private Repository() {
        sRestaurantList = new ArrayList<>();
        sOrderStateList = new ArrayList<>();
        sMenuList = new ArrayList<>();
        sReviewList = new ArrayList<>();
    }

    public static Repository getInstance(Context context) {
        if (sRepository == null)
            sRepository = new Repository();

        return sRepository;
    }

    public List<Restaurant> getRestaurantList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestaurantApi.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestaurantApi restaurantApi = retrofit.create(RestaurantApi.class);
        restaurantApi.getRestaurantList().enqueue(new Callback<List<List<String>>>() {
            @Override
            public void onResponse(Call<List<List<String>>> call, Response<List<List<String>>> response) {
                Log.d("MYTAG", "data : " + response.body().get(0).get(0));
            }

            @Override
            public void onFailure(Call<List<List<String>>> call, Throwable t) {
                Log.d("MYTAG", "fail : " + t.getMessage());
            }
        });
        return sRestaurantList;
    }

    public List<OrderState> getOrderStateList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestaurantApi.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestaurantApi restaurantApi = retrofit.create(RestaurantApi.class);
        restaurantApi.getOrderStateList().enqueue(new Callback<List<model_server.OrderState>>() {
            @Override
            public void onResponse(Call<List<model_server.OrderState>> call, Response<List<model_server.OrderState>> response) {
                Log.d("MYTAG", "data : " + response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<model_server.OrderState>> call, Throwable t) {
                Log.d("MYTAG", "fail : " + t.getMessage());
            }
        });
        return sOrderStateList;
    }

    public List<Menu> getMenuList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestaurantApi.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestaurantApi restaurantApi = retrofit.create(RestaurantApi.class);
        restaurantApi.getMenuList().enqueue(new Callback<List<model_server.Menu>>() {
            @Override
            public void onResponse(Call<List<model_server.Menu>> call, Response<List<model_server.Menu>> response) {
                Log.d("MYTAG", "data : " + response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<model_server.Menu>> call, Throwable t) {
                Log.d("MYTAG", "fail : " + t.getMessage());
            }
        });
        return sMenuList;
    }

    public List<Review> getReviewList() {
        return sReviewList;
    }
}
