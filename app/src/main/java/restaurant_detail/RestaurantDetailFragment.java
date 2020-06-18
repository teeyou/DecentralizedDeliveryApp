package restaurant_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cart.CartActivity;
import model.Menu;
import model.Order;
import model.Restaurant;
import temporary.variable.android.decentralizeddeliveryapp.R;
import util.Util;

public class RestaurantDetailFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MenuRecyclerAdapter mRestaurantRecyclerAdapter;
    private RestaurantDetailActivity mContext;

    private List<Menu> mMenuList;
    private Restaurant mRestaurant;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FloatingActionButton mCartFab;

    private BottomSheetBehavior mBottomSheetBehavior;

    private LinearLayout mLinearLayout;

    private ImageView mMinus;
    private ImageView mPlus;

    private TextView mPrice;
    private TextView mCount;
    private TextView mMenuName;
    private TextView mCartCount;

    private Button mButtonAddCart;

    private int cnt = 1;
    private int price = 0;

    private List<Order> mCartList;
    private Menu mMenu;

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

        mCartList = new ArrayList<>();

        mCartCount = v.findViewById(R.id.textView_cart_count);
        mMenuName = v.findViewById(R.id.textView_menu_name);
        mPrice = v.findViewById(R.id.textView_price);
        mCount = v.findViewById(R.id.textView_cnt);
        mMinus = v.findViewById(R.id.imageView_cnt_minus);
        mPlus = v.findViewById(R.id.imageView_cnt_plus);
        mButtonAddCart = v.findViewById(R.id.btn_add_cart);

        mMinus.setOnClickListener(view -> {
            if(cnt >= 2) {
                cnt--;
                price -= mMenu.getPrice();
            }

            mPrice.setText(Util.numberToCommaFormat(price));
            mCount.setText(String.valueOf(cnt));
        });

        mPlus.setOnClickListener(view -> {
            cnt++;
            price += mMenu.getPrice();

            mPrice.setText(Util.numberToCommaFormat(price));
            mCount.setText(String.valueOf(cnt));
        });

        mButtonAddCart.setOnClickListener(view -> {
            Menu menu = new Menu(mMenu.getName(), mMenu.getPrice(), mMenu.getDescription(), mMenu.getImage());
            mCartList.add(new Order(menu,cnt,price));

            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            mCartCount.setText(String.valueOf(mCartList.size()));
        });

        mLinearLayout = v.findViewById(R.id.bottom_sheet_layout);
        mBottomSheetBehavior = BottomSheetBehavior.from(mLinearLayout);

        if(getArguments() != null) {
            mRestaurant = (Restaurant) getArguments().getSerializable("restaurant");
        }

        mCartFab = v.findViewById(R.id.fab_cart);
        mCartFab.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), CartActivity.class);
            intent.putExtra("cartList", (Serializable) mCartList);
            intent.putExtra("restaurant", (Serializable) mRestaurant);
            startActivity(intent);
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

        mRestaurantRecyclerAdapter = new MenuRecyclerAdapter(mMenuList);

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
            holder.price.setText(String.valueOf(menu.getPrice()));
            holder.description.setText(menu.getDescription());
            holder.menuImage.setImageResource(menu.getImage());


            holder.itemView.setOnClickListener(view -> {
                mMenu = menu;

                cnt = 1;
                price = menu.getPrice();

                int state = mBottomSheetBehavior.getState();
//                Log.d("MYTAG", "State : " + state);

                mMenuName.setText(menu.getName());
                mPrice.setText(Util.numberToCommaFormat(price));
                mCount.setText(String.valueOf(cnt));

                if(state == BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                } else if(state == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(mLinearLayout.getHeight());
//                        Log.d("MYTAG", "Height : " + mLinearLayout.getHeight());

                } else if(state == BottomSheetBehavior.STATE_EXPANDED){

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
