package restaurant_detail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import model.Restaurant;
import temporary.variable.android.decentralizeddeliveryapp.R;

public class RestaurantDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        Restaurant restaurant = (Restaurant) getIntent().getSerializableExtra("restaurant");

//        Log.d("RestaurantDetailActivity", restaurant.getName() + "," + restaurant.getDescription());

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, RestaurantDetailFragment.newInstance(restaurant)).commit();
    }
}
