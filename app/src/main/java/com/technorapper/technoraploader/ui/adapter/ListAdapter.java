package com.technorapper.technoraploader.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.technorapper.technoraploader.R;
import com.technorapper.technoraploader.data.model.UnSplashImageListModelItem;
import com.technorapper.technoraploader.databinding.ListCellBinding;
import com.technorapper.technoraploader.interfaces.RecyclerViewClickListener;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {


    private Context context;
    private List<UnSplashImageListModelItem> list;
    private RecyclerViewClickListener listener;


    public ListAdapter(List<UnSplashImageListModelItem> list, Context context, RecyclerViewClickListener listener) {
        this.list = list;
        this.listener = listener;
        this.context = context;
        //    this.width = width;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListCellBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_cell, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        holder.binding.setBaseUrl(preference.getImageBaseUrl());
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ListCellBinding binding;

        public MyViewHolder(@NonNull ListCellBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.clearText.setOnClickListener(view -> {

                listener.onClick(view, getAdapterPosition());
            });


        }
    }

}
