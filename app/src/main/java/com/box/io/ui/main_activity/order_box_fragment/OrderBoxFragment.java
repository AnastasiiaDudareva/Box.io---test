package com.box.io.ui.main_activity.order_box_fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.box.io.BoxIo;
import com.box.io.R;
import com.box.io.database.AppDatabase;
import com.box.io.databinding.FragmentOrderBoxBinding;
import com.box.io.models.AvailBoxWrapper;
import com.box.io.models.Box;
import com.box.io.rest.NetworkMockHelper;
import com.box.io.ui.adapters.BoxItemAdapter;
import com.box.io.ui.adapters.ColorItemAdapter;
import com.box.io.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import static android.widget.LinearLayout.HORIZONTAL;

public class OrderBoxFragment extends BaseFragment implements OrderBoxView {

    private BoxItemAdapter boxItemAdapter;
    private ColorItemAdapter colorItemAdapter;
    @Inject
    public NetworkMockHelper networkMockHelper;
    @Inject
    public AppDatabase appDatabase;
    private OrderBoxPresenter presenter;
    private FragmentOrderBoxBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoxIo.getComponent().injectOrderBoxFragment(this);
        presenter = new OrderBoxPresenter(networkMockHelper, appDatabase);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_box, container, false);
        setTitle(R.string.order_box);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachToView(this);
        binding.boxList.setLayoutManager(new LinearLayoutManager(getContext()));
        boxItemAdapter = new BoxItemAdapter();
        binding.boxList.setAdapter(boxItemAdapter);
        binding.colorList.setLayoutManager(new LinearLayoutManager(getContext(), HORIZONTAL, false));
        colorItemAdapter = new ColorItemAdapter();
        binding.colorList.setAdapter(colorItemAdapter);
        presenter.addDisposable(boxItemAdapter.getClickSubject()
                .subscribe(box -> showAvailColors(box.colors)));
        binding.btOrder.setOnClickListener(v -> presenter.orderPressed());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachFromView();
    }

    @Override
    public void showAvailBoxes(List<AvailBoxWrapper> availBoxes) {
        boxItemAdapter.setItems(availBoxes);
    }

    @Override
    public Box getSelectedBox() {
        return boxItemAdapter.getSelected();
    }

    @Override
    public Integer getSelectedColor() {
        return colorItemAdapter.getSelected();
    }

    @Override
    public boolean printName() {
        return binding.printName.isChecked();
    }

    public void showAvailColors(List<Integer> availColors) {
        colorItemAdapter.setItems(availColors);
    }
}
