package restaurant_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cart.CartActivity;
import model.Menu;
import model.Restaurant;
import temporary.variable.android.decentralizeddeliveryapp.MainActivity;
import temporary.variable.android.decentralizeddeliveryapp.R;

public class RestaurantDetailFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RestaurantDetailActivity mContext;

    private List<Menu> mMenuList;
    private Restaurant mRestaurant;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FloatingActionButton mCartFab;

    public static RestaurantDetailFragment newInstance(Restaurant restaurant) {

        Bundle args = new Bundle();
        RestaurantDetailFragment fragment = new RestaurantDetailFragment();

        args.putSerializable("restaurant", restaurant);
        fragment.setArguments(args);

        return fragment;
    }

    public RestaurantDetailFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (RestaurantDetailActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);

        if(getArguments() != null) {
            mRestaurant = (Restaurant) getArguments().getSerializable("restaurant");
        }

        mCartFab = v.findViewById(R.id.fab_cart);
        mCartFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
            }
        });

//        Log.d("RestaurantDetailFragment", mRestaurant.getName() + "," + mRestaurant.getCategory());

        mCollapsingToolbarLayout = v.findViewById(R.id.toolbar_layout);
        mCollapsingToolbarLayout.setTitle(mRestaurant.getName());
        mCollapsingToolbarLayout.setBackgroundResource(mRestaurant.getImages().get(0));

        mRecyclerView = v.findViewById(R.id.recyclerView);

        mMenuList = new ArrayList<>();
        mMenuList.add(new Menu("음식이름1",  10000, "음식설명1", R.drawable.chicken));
        mMenuList.add(new Menu("음식이름2",  20000, "음식설명2", R.drawable.pizza));
        mMenuList.add(new Menu("음식이름3",  30000, "음식설명3", R.drawable.pork));
        mMenuList.add(new Menu("음식이름4",  40000, "음식설명4", R.drawable.chicken));
        mMenuList.add(new Menu("음식이름5",  50000, "음식설명5", R.drawable.pizza));
        mMenuList.add(new Menu("음식이름6",  60000, "음식설명6", R.drawable.pork));
        mMenuList.add(new Menu("음식이름7",  70000, "음식설명7", R.drawable.chicken));
        mMenuList.add(new Menu("음식이름8",  80000, "음식설명8", R.drawable.pizza));
        mMenuList.add(new Menu("음식이름9",  90000, "음식설명9", R.drawable.pork));

        MenuRecyclerAdapter mRestaurantRecyclerAdapter = new MenuRecyclerAdapter(mMenuList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mRestaurantRecyclerAdapter);
        return v;
    }

    class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuViewHolder> {
        List<Menu> menuList;

        public MenuRecyclerAdapter(List<Menu> list) {
            menuList = list;
        }

        @NonNull
        @Override
        public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu, parent, false);
            return new MenuViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuViewHolder holder, int i) {
            final Menu menu = menuList.get(i);
            holder.name.setText(menu.getName());
            holder.price.setText(menu.getPrice().toString());
            holder.description.setText(menu.getDescription());
            holder.menuImage.setImageResource(menu.getImage());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return menuList.size();
        }

        public void setMenuList(List<Menu> list) {
            menuList = list;
            notifyDataSetChanged();
        }
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        TextView description;

        ImageView menuImage;


        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.menu_name);
            price = itemView.findViewById(R.id.menu_price);
            description = itemView.findViewById(R.id.menu_description);
            menuImage = itemView.findViewById(R.id.menu_image);
        }
    }
}
