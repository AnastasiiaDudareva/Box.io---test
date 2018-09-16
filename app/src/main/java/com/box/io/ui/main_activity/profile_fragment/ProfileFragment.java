package com.box.io.ui.main_activity.profile_fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.box.io.BoxIo;
import com.box.io.R;
import com.box.io.database.AppDatabase;
import com.box.io.databinding.FragmentProfileBinding;
import com.box.io.models.User;
import com.box.io.models.UserBox;
import com.box.io.rest.NetworkMockHelper;
import com.box.io.ui.base.BaseFragment;

import java.text.DateFormat;
import java.util.Date;

import javax.inject.Inject;

public class ProfileFragment extends BaseFragment implements ProfileView {

    private FragmentProfileBinding binding;
    @Inject
    public NetworkMockHelper networkMockHelper;
    @Inject
    public AppDatabase appDatabase;
    private ProfilePresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoxIo.getComponent().injectProfileFragment(this);
        setHasOptionsMenu(true);
        presenter = new ProfilePresenter(appDatabase, networkMockHelper);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        setTitle(R.string.order_box);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachToView(this);
        binding.btLogout.setOnClickListener(v -> presenter.logout());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachFromView();
    }

    @Override
    public void showUser(User user) {
        binding.setUser(user);
    }

    @Override
    public void showBox(UserBox box) {
        if (box != null) {
            binding.box.setVisibility(View.VISIBLE);
            binding.boxSize.setText(box.box.getSize());
            binding.background.setCardBackgroundColor(box.color);
            if (box.name)
                binding.boxName.setText(binding.getUser().name);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       if(id == R.id.nav_edit){
          showInputDialog();
       }
        return true;
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Input any text");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);
        builder.setPositiveButton("OK", (dialog, which) ->
                presenter.updateInfo(input.getText().toString()));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public void showLastUpdated(Date date) {
        binding.lastUpdated.setText(DateFormat
                .getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)
                .format(date));
    }
}
