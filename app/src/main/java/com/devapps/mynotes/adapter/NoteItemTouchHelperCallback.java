package com.devapps.mynotes.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.devapps.mynotes.R;

public class NoteItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private NoteRecyclerViewAdapter mAdapter;
    private Context context;

    public NoteItemTouchHelperCallback(NoteRecyclerViewAdapter adapter, Context context) {
        this.mAdapter = adapter;
        this.context = context;
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

    @Override
    public void onChildDraw(@NonNull Canvas c,
                            @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX,
                            float dY,
                            int actionState,
                            boolean isCurrentlyActive) {

        ColorDrawable deleteBackground = new ColorDrawable(ContextCompat.getColor(context,
                R.color.delete_red));
        Drawable deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete_foreground);

        View itemView = viewHolder.itemView;

        int iconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;

        if (dX > 0) {
            deleteBackground.setBounds(itemView.getLeft(), itemView.getTop(), (int) dX,
                    itemView.getBottom());
            deleteIcon.setBounds(itemView.getLeft() + iconMargin,itemView.getTop() + iconMargin,
                    itemView.getLeft() + iconMargin + deleteIcon.getIntrinsicWidth() ,
                    itemView.getBottom() - iconMargin);
        } else {
            deleteBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(),
                    itemView.getRight(), itemView.getBottom());
            deleteIcon.setBounds(itemView.getRight() - iconMargin - deleteIcon.getIntrinsicWidth(),
                    itemView.getTop() + iconMargin,itemView.getRight() - iconMargin ,
                    itemView.getBottom() - iconMargin);
        }

        deleteBackground.draw(c);
        deleteIcon.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
