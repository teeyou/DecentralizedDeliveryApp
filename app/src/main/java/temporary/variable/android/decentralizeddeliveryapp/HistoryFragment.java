package temporary.variable.android.decentralizeddeliveryapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Menu;
import model.Order;
import model.OrderState;
import model.Restaurant;
import restaurant_detail.RestaurantDetailActivity;

public class HistoryFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MainActivity mContext;
    private List<OrderState> mOrderStateList;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        mOrderStateList = new ArrayList<>();
        mOrderStateList.add(new OrderState("치킨집",R.drawable.chicken_logo,new Menu("메뉴이름",12345,"설명",R.drawable.chicken_logo),"time1","배달중"));
        mOrderStateList.add(new OrderState("피자집",R.drawable.pizza_logo,new Menu("피자이름",12346,"설명2",R.drawable.china_logo),"time2","배달완료"));
        mOrderStateList.add(new OrderState("족발집",R.drawable.china_logo,new Menu("족발이름",12347,"설명3",R.drawable.pizza_logo),"time3","배달완료"));
        mRecyclerView = v.findViewById(R.id.restaurant_recyclerView);
        HistoryRecyclerAdapter mAdapter = new HistoryRecyclerAdapter(mOrderStateList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryViewHolder> {
        List<OrderState> orderStateList;

        public HistoryRecyclerAdapter(List<OrderState> list) {
            orderStateList = list;
        }

        @NonNull
        @Override
        public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_history, parent, false);
            return new HistoryViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryViewHolder holder, int i) {
            final OrderState orderState = orderStateList.get(i);
            holder.day.setText(orderState.getTime());
            holder.state.setText(orderState.getState());
            holder.name.setText(orderState.getRestaurantName());
            holder.menu.setText(orderState.getMenu().getName());
            holder.logo.setImageResource(orderState.getMenu().getImage());
            holder.detail.setOnClickListener(v -> {
                Intent intent = new Intent();

            });
        }

        @Override
        public int getItemCount() {
            return orderStateList.size();
        }

        public void setRestaurantList(List<OrderState> list) {
            orderStateList = list;
            notifyDataSetChanged();
        }
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView day;
        TextView state;
        TextView name;
        TextView menu;

        ImageView logo;

        Button detail;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.list_item_order_day);
            state = itemView.findViewById(R.id.list_item_order_state);
            name = itemView.findViewById(R.id.list_item_restaurant_name);
            menu = itemView.findViewById(R.id.list_item_order_menu);
            logo = itemView.findViewById(R.id.list_item_image);
            detail = itemView.findViewById(R.id.list_item_btn_order_receipt);
        }
    }
}
