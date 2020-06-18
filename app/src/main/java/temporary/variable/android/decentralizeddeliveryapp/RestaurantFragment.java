package temporary.variable.android.decentralizeddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import model.Restaurant;
import restaurant_detail.RestaurantDetailActivity;

import static temporary.variable.android.decentralizeddeliveryapp.R.drawable.chicken;
import static temporary.variable.android.decentralizeddeliveryapp.R.drawable.pizza;
import static temporary.variable.android.decentralizeddeliveryapp.R.drawable.pork;

public class RestaurantFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MainActivity mContext;

    private List<Restaurant> mRestaurantList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant, container, false);

        mRestaurantList = new ArrayList<>();
        List<Integer> imageList1 = new ArrayList<>();
        List<Integer> imageList2 = new ArrayList<>();
        List<Integer> imageList3 = new ArrayList<>();
        imageList1.add(chicken);
        imageList2.add(pork);
        imageList3.add(pizza);

        mRestaurantList.add(new Restaurant("음식점이름1","음식점 설명1",imageList1,"치킨","충무로점"));
        mRestaurantList.add(new Restaurant("음식점이름2","음식점 설명2",imageList2, "삼겹살","명동점"));
        mRestaurantList.add(new Restaurant("음식점이름3","음식점 설명3",imageList3,"피자", "강남점"));
        mRestaurantList.add(new Restaurant("음식점이름4","음식점 설명4",imageList1, "치킨","홍대점"));
        mRestaurantList.add(new Restaurant("음식점이름5","음식점 설명5",imageList2, "삼겹살", "이태원점"));
        mRestaurantList.add(new Restaurant("음식점이름6","음식점 설명6",imageList3, "피자", "혜화점"));

        mRecyclerView = v.findViewById(R.id.restaurant_recyclerView);
        RestaurantRecyclerAdapter mRestaurantRecyclerAdapter = new RestaurantRecyclerAdapter(mRestaurantList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRestaurantRecyclerAdapter);
        return v;
    }

    class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
        List<Restaurant> restaurantList;

        public RestaurantRecyclerAdapter(List<Restaurant> list) {
            restaurantList = list;
        }

        @NonNull
        @Override
        public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_restaurant, parent, false);
            return new RestaurantViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int i) {
            final Restaurant restaurant = restaurantList.get(i);
            holder.name.setText(restaurant.getName());
            holder.description.setText(restaurant.getDescription());

            holder.first.setImageResource(restaurant.getImages().get(0));
            holder.second.setImageResource(restaurant.getImages().get(0));
            holder.third.setImageResource(restaurant.getImages().get(0));

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
                intent.putExtra("restaurant", restaurant);
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return restaurantList.size();
        }

        public void setRestaurantList(List<Restaurant> list) {
            restaurantList = list;
            notifyDataSetChanged();
        }
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;

        ImageView first;
        ImageView second;
        ImageView third;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_item_text_view_name);
            description = itemView.findViewById(R.id.list_item_text_view_description);
            first = itemView.findViewById(R.id.list_item_image_view_first);
            second = itemView.findViewById(R.id.list_item_image_view_second);
            third = itemView.findViewById(R.id.list_item_image_view_third);
        }
    }
}
