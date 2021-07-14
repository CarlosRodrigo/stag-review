package com.devapps.mynotes.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class NoteItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private NoteRecyclerViewAdapter mAdapter;

    public NoteItemTouchHelperCallback(NoteRecyclerViewAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipe = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        return makeMovementFlags(drag, swipe);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int initPosition = viewHolder.getAdapterPosition();
        int targetPosition = target.getAdapterPosition();
        mAdapter.swap(initPosition, targetPosition);
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        mAdapter.remove(position);
    }
}
