package restaurant_detail;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import model.Restaurant;
import temporary.variable.android.decentralizeddeliveryapp.RestaurantFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    private int cnt;
    private Restaurant mRestaurant;

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int cnt, Restaurant restaurant) {
        super(fragmentActivity);
        this.cnt = cnt;
        mRestaurant = restaurant;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0 :
                return RestaurantMenuFragment.newInstance(mRestaurant);
            case 1:
                return new RestaurantReviewFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return cnt;
    }
}
