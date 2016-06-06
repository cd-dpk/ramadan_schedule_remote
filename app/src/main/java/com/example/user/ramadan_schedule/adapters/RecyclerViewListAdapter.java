package com.example.user.ramadan_schedule.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.ramadan_schedule.datamodels.interfaces.OnRecyclerViewItemListener;


/**
 * Created by chandradasdipok on 5/17/2016.
 */
public class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewListAdapter.MyViewHolder>{

    OnRecyclerViewItemListener onRecyclerViewItemListener;
    int itemsNumber;
    int cardID;

    public RecyclerViewListAdapter(OnRecyclerViewItemListener onRecyclerViewItemListener, int cardID,int itemsNumber) {
        this.onRecyclerViewItemListener = onRecyclerViewItemListener;
        this.itemsNumber = itemsNumber;
        this.cardID = cardID;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(cardID,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        onRecyclerViewItemListener.listenItem(holder.myView, position);
    }
    @Override
    public int getItemCount() {
        return itemsNumber;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        View myView;
        public MyViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
        }
    }
}
