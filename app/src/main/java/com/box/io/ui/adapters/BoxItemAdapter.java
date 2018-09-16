package com.box.io.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.box.io.databinding.AdapterItemBoxBinding;
import com.box.io.models.AvailBoxWrapper;
import com.box.io.models.Box;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class BoxItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PublishSubject<AvailBoxWrapper> clickSubject = PublishSubject.create();
    private AvailBoxWrapper mSelectedItem = null;

    public Observable<AvailBoxWrapper> getClickSubject() {
        return clickSubject;
    }

    private List<AvailBoxWrapper> items = new ArrayList<>();

    public void setItems(List<AvailBoxWrapper> items) {
        mSelectedItem = null;
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                AdapterItemBoxBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Box getSelected() {
        return mSelectedItem != null ? mSelectedItem.box : null;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final AdapterItemBoxBinding binding;

        public ViewHolder(AdapterItemBoxBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final AvailBoxWrapper item) {
            binding.setBox(item.box);
            binding.boxSize.setChecked(item == mSelectedItem);
            binding.boxSize.setOnClickListener(v -> {
                mSelectedItem = item;
                notifyItemRangeChanged(0, items.size());
                clickSubject.onNext(item);
            });
            binding.executePendingBindings();
        }
    }


}
