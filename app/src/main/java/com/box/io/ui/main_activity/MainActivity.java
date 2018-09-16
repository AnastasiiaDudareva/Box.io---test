package com.box.io.ui.main_activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.TextView;

import com.box.io.BoxIo;
import com.box.io.R;
import com.box.io.bus.BusProvider;
import com.box.io.bus.LogoutEvent;
import com.box.io.database.AppDatabase;
import com.box.io.databinding.ActivityMainBinding;
import com.box.io.models.User;
import com.box.io.rest.NetworkMockHelper;
import com.box.io.ui.base.BaseFragment;
import com.box.io.ui.base.BaseFragmentActivity;
import com.box.io.ui.main_activity.order_box_fragment.OrderBoxFragment;
import com.box.io.ui.main_activity.profile_fragment.ProfileFragment;
import com.box.io.utils.ActionsController;
import com.box.io.utils.UserPreferences;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class MainActivity extends BaseFragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    public ActivityMainBinding binding;
    public MainPresenter presenter;

    @Inject
    public AppDatabase appDatabase;

    @Inject
    public NetworkMockHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.register(this);
        BoxIo.getComponent().injectMainActivity(this);
        binding = putContentView(R.layout.activity_main, false);
        presenter = new MainPresenter(appDatabase);
        presenter.attachToView(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, getToolbar(),
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);

        int idMenu = (int) UserPreferences.getData(UserPreferences.MENU_SELECTED);
        if (idMenu == -1) idMenu = R.id.nav_box;
        binding.navView.setCheckedItem(idMenu);
        selectItem(idMenu);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        selectItem(id);
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachFromView();
        BusProvider.unregister(this);
    }

    private void selectItem(int id) {
        if (id == R.id.nav_box) {
            replaceFragment(new OrderBoxFragment(), id);
        } else if (id == R.id.nav_profile) {
            replaceFragment(new ProfileFragment(), id);
        }
    }

    private void replaceFragment(BaseFragment fragment, int menu) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        UserPreferences.saveData(UserPreferences.MENU_SELECTED, menu);
    }

    @Override
    public void showUserData(User user) {
        ((TextView) findViewById(R.id.user_name)).setText(user.name);
        ((TextView) findViewById(R.id.user_email)).setText(user.email);
    }

    @Subscribe
    public void onLogout(LogoutEvent event) {
        ActionsController.openAuthorization(this);
        finish();
    }
}
