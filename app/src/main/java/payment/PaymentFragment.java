package payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.List;

import model.Order;
import model.Restaurant;
import temporary.variable.android.decentralizeddeliveryapp.R;

public class PaymentFragment extends Fragment {
    private List<Order> mCartList;

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
        if (getArguments() != null) {
            mCartList = (List<Order>) getArguments().getSerializable("cartList");
        }

        return v;


    }
}
