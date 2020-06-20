package temporary.variable.android.decentralizeddeliveryapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import model.Restaurant;

public class SearchFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SearchRecyclerAdapter mSearchRecyclerAdapter;
    private MainActivity mContext;

    private List<Restaurant> mRestaurantList;
    private List<Restaurant> mSearchResultList;

    private Toolbar mToolbar;
    private SearchView mSearchView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {

            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            mSearchResultList = new ArrayList<>();

            for (int i = 0; i < mRestaurantList.size(); i++) {
                if(mRestaurantList.get(i).getCategory().equals(newText)) {
                    mSearchResultList.add(mRestaurantList.get(i));
                }
            }

            mSearchRecyclerAdapter.setRestaurantList(mSearchResultList);
            return true;
        }
    };

    public SearchFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        mRestaurantList = new ArrayList<>();

        mToolbar = v.findViewById(R.id.search_toolbar);
        mToolbar.inflateMenu(R.menu.search);

        mSearchView = (SearchView) mToolbar.getMenu().findItem(R.id.menu_search).getActionView();
        mSearchView.setOnQueryTextListener(mOnQueryTextListener);

        mRecyclerView = v.findViewById(R.id.search_recyclerView);
        mSearchRecyclerAdapter = new SearchRecyclerAdapter(new ArrayList<Restaurant>());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mSearchRecyclerAdapter);

        mSwipeRefreshLayout = v.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //새로고침

                mSearchView.setQuery("",false);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }

    static class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchViewHolder> {
        List<Restaurant> restaurantList;

        public SearchRecyclerAdapter(List<Restaurant> list) {
            restaurantList = list;
        }

        @NonNull
        @Override
        public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_restaurant, parent, false);
            return new SearchViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull SearchViewHolder holder, int i) {
            Restaurant restaurant = restaurantList.get(i);
            holder.name.setText(restaurant.getName());
            holder.description.setText(restaurant.getDescription());

            holder.first.setImageResource(restaurant.getImages().get(0));
            holder.second.setImageResource(restaurant.getImages().get(0));
            holder.third.setImageResource(restaurant.getImages().get(0));
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

    static class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;

        ImageView first;
        ImageView second;
        ImageView third;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_item_text_view_name);
            description = itemView.findViewById(R.id.list_item_text_view_description);
            first = itemView.findViewById(R.id.list_item_image_view_first);
            second = itemView.findViewById(R.id.list_item_image_view_second);
            third = itemView.findViewById(R.id.list_item_image_view_third);
        }
    }
}
