package com.box.io.ui.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends BaseView> implements Presenter<T> {

    protected T view;
    protected CompositeDisposable destroyDisposable = new CompositeDisposable();

    @Override
    public void attachToView(T attachedView) {
        view = attachedView;

    }

    @Override
    public void detachFromView() {
        if (!destroyDisposable.isDisposed())
            destroyDisposable.dispose();
        view = null;
    }

    public void addDisposable(Disposable disposable){
        destroyDisposable.add(disposable);
    }

}
