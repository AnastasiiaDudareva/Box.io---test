package com.box.io.ui.base;

interface Presenter <T extends BaseView>{

    void attachToView(T attachedView);

    void detachFromView();

}
