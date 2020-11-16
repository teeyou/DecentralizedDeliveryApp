package temporary.variable.android.decentralizeddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import data_source.Repository;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mRestaurantLayout;
    private FrameLayout mSearchLayout;
    private FrameLayout mHistoryLayout;
    private FrameLayout mSettingsLayout;
    private FrameLayout mCurrVisibleLayout;

    private RestaurantFragment mRestaurantFragment;
    private SearchFragment mSearchFragment;
    private HistoryFragment mHistoryFragment;
    private SettingsFragment mSettingsFragment;

    private FragmentManager mFragmentManager;
    private Repository mRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRepository = Repository.getInstance(getApplicationContext());
        mRepository.getRestaurantList();
        mRepository.getOrderStateList();
        mRepository.getMenuList();


        mFragmentManager = getSupportFragmentManager();

        mRestaurantFragment = new RestaurantFragment();
        mSearchFragment = new SearchFragment();
        mHistoryFragment = new HistoryFragment();
        mSettingsFragment = new SettingsFragment();

        mRestaurantLayout = findViewById(R.id.fragment_restaurant);
        mSearchLayout = findViewById(R.id.fragment_search);
        mHistoryLayout = findViewById(R.id.fragment_history);
        mSettingsLayout = findViewById(R.id.fragment_settings);

        mCurrVisibleLayout = mRestaurantLayout;

        mFragmentManager.beginTransaction().add(R.id.fragment_restaurant,mRestaurantFragment).commit();

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_restaurant:
                    setFragmentVisible(mRestaurantFragment, R.id.fragment_restaurant, mRestaurantLayout);
                    return true;
                case R.id.navigation_search:
                    setFragmentVisible(mSearchFragment, R.id.fragment_search, mSearchLayout);
                    return true;
                case R.id.navigation_receipt:
                    setFragmentVisible(mHistoryFragment, R.id.fragment_history, mHistoryLayout);
                    return true;
                case R.id.navigation_settings:
                    setFragmentVisible(mSettingsFragment, R.id.fragment_settings, mSettingsLayout);
                    return true;
            }
            return false;
        }
    };

    private void setFragmentVisible(Fragment fragment, int layoutId, FrameLayout frameLayout) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(layoutId, fragment);
            fragmentTransaction.commit();
        }

        mCurrVisibleLayout.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        mCurrVisibleLayout = frameLayout;
    }
}
