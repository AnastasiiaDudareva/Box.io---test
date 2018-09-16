package com.box.io.ui.main_activity.order_box_fragment;

import com.box.io.models.AvailBoxWrapper;
import com.box.io.models.Box;
import com.box.io.ui.base.BaseView;

import java.util.List;

public interface OrderBoxView extends BaseView {
    void showAvailBoxes(List<AvailBoxWrapper> availBoxes);

    Box getSelectedBox();

    Integer getSelectedColor();
    boolean printName();
}
