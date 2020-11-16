package data_source;

import java.util.List;

import model_server.Menu;
import model_server.OrderState;
import model_server.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantApi {
    String BASEURL = "http://ddiggo.iptime.org/";

    @GET("/api/restaurants")
    Call<List<List<String>>> getRestaurantList();

    @GET("/api/menu")
    Call<List<Menu>> getMenuList();

    @GET("/api/state")
    Call<List<OrderState>> getOrderStateList();
}
