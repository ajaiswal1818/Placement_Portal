package com.group6.placementportal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class RecycleNameTouchHelper extends ItemTouchHelper.Callback {

    private AnimationListener animationListener;

    public RecycleNameTouchHelper(AnimationListener animationListener) {
        this.animationListener = animationListener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ;
        int swipeFlags = ItemTouchHelper.ACTION_STATE_IDLE;
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        if(animationListener!=null){
            animationListener.onMove(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    interface  AnimationListener {
        void onMove(int fromPos, int toPos);

        void onSwiped(int direction, int pos);
    }
}
