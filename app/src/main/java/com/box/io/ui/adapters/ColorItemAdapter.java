package com.box.io.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.box.io.databinding.AdapterItemColorBinding;

import java.util.ArrayList;
import java.util.List;

public class ColorItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Integer mSelectedItem = null;

    private List<Integer> items = new ArrayList<>();

    public void setItems(List<Integer> items) {
        mSelectedItem = null;
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                AdapterItemColorBinding.inflate(LayoutInflater.from(parent.getContext()),
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

    public Integer getSelected() {
        return mSelectedItem;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final AdapterItemColorBinding binding;

        public ViewHolder(AdapterItemColorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Integer item) {
            binding.background.setCardBackgroundColor(item);
            binding.color.setChecked(item == mSelectedItem);
            binding.color.setOnClickListener(v -> {
                mSelectedItem = item;
                notifyItemRangeChanged(0, items.size());
            });
            binding.executePendingBindings();
        }
    }


}
