package payment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Order;
import model.Restaurant;
import temporary.variable.android.decentralizeddeliveryapp.R;
import util.Util;

//import static restaurant_detail.RestaurantDetailFragment.mCartList;
import static restaurant_detail.RestaurantDetailActivity.mCartList;

public class PaymentFragment extends Fragment {
    private static final String PRIMARY_COLOR = "#FF247CE1";
//    private List<Order> mCartList;
    private LinearLayout mLinearLayout;
    private TextView mTotalPrice;

    private Button mButtonCard;
    private Button mButtonPhone;
    private Button mButtonKakaopay;
    private Button mButtonPayco;
    private Button mButtonNaverpay;
    private Button mButtonSmilepay;

    private GridLayout mGridLayout;
    private List<Button> mButtonList;
    private int selectedIdx = -1;

    private RadioButton mRadioInapp;
    private RadioButton mRadioOffline;

    public static PaymentFragment newInstance(List<Order> list) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putSerializable("cartList", (Serializable) list);
        fragment.setArguments(args);
        return fragment;
    }

    public PaymentFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_payment, container, false);

        mButtonCard = v.findViewById(R.id.btn_payment_card);
        mButtonPhone = v.findViewById(R.id.btn_payment_phone);
        mButtonKakaopay = v.findViewById(R.id.btn_payment_kakao_pay);
        mButtonPayco = v.findViewById(R.id.btn_payment_payco);
        mButtonNaverpay = v.findViewById(R.id.btn_payment_naver_pay);
        mButtonSmilepay = v.findViewById(R.id.btn_payment_smile_pay);

        mGridLayout = v.findViewById(R.id.grid_layout);
        mLinearLayout = v.findViewById(R.id.linearLayout_order_info);
        mTotalPrice = v.findViewById(R.id.textView_payment_total_price);

        mRadioInapp = v.findViewById(R.id.radio_in_app);
        mRadioOffline = v.findViewById(R.id.radio_offline);

        mRadioInapp.setOnClickListener(view -> {
            mRadioInapp.setChecked(true);
            mRadioOffline.setChecked(false);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mGridLayout.setLayoutParams(params);
//            mGridLayout.setVisibility(View.VISIBLE);
        });

        mRadioOffline.setOnClickListener(view -> {
            mRadioInapp.setChecked(false);
            mRadioOffline.setChecked(true);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 0);
            mGridLayout.setLayoutParams(params);
//            mGridLayout.setVisibility(View.INVISIBLE);
        });

//        if (getArguments() != null) {
//            mCartList = (List<Order>) getArguments().getSerializable("cartList");
//            setOrderInfo();
//        }

        setOrderInfo();
        mButtonList = Arrays.asList(mButtonCard,mButtonPhone,mButtonKakaopay,mButtonPayco,mButtonNaverpay,mButtonSmilepay);

        mButtonCard.setOnClickListener(view -> {
            setTextColor(0);
        });

        mButtonPhone.setOnClickListener(view -> {
            setTextColor(1);
        });

        mButtonKakaopay.setOnClickListener(view -> {
            setTextColor(2);
        });

        mButtonPayco.setOnClickListener(view -> {
            setTextColor(3);
        });

        mButtonNaverpay.setOnClickListener(view -> {
            setTextColor(4);
        });

        mButtonSmilepay.setOnClickListener(view -> {
            setTextColor(5);
        });

        return v;
    }

    private void setTextColor(int selected) {
        if(selectedIdx != -1)
            mButtonList.get(selectedIdx).setTextColor(Color.BLACK);

        mButtonList.get(selected).setTextColor(Color.parseColor(PRIMARY_COLOR));
        selectedIdx = selected;
    }

    private void setOrderInfo() {
        int total = 0;
        for(Order o : mCartList) {
            total += o.getOrderPrice();

            LinearLayout layout = new LinearLayout(getContext());
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout.setOrientation(LinearLayout.HORIZONTAL);

            TextView menuName = new TextView(getContext());
            menuName.setText(o.getMenu().getName() + " * " + o.getCount());
            menuName.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            TextView menuPrice = new TextView(getContext());
            menuPrice.setText(Util.numberToCommaFormat(o.getMenu().getPrice() * o.getCount()) + "원");
            menuPrice.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            menuPrice.setGravity(Gravity.END);

            layout.addView(menuName);
            layout.addView(menuPrice);

            mLinearLayout.addView(layout);
        }

        mTotalPrice.setText(Util.numberToCommaFormat(total) + "원");
    }
}
