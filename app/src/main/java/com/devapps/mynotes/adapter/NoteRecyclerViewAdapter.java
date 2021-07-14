package com.devapps.mynotes.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devapps.mynotes.R;
import com.devapps.mynotes.model.Note;

import java.util.ArrayList;
import java.util.Collections;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.VH> {

    private String TAG = "Recycler";

    private ArrayList<Note> notes;
    private Context context;
    private OnAdapterItemClickListener adapterItemClickListener;

    public NoteRecyclerViewAdapter(ArrayList<Note> notes, Context context,
                                   OnAdapterItemClickListener listener) {
        this.notes = notes;
        this.context = context;
        this.adapterItemClickListener = listener;
    }

    public void addNote(Note note) {
        this.notes.add(note);
        notifyDataSetChanged();
    }

    public void updateNote(Note note, int position) {
        this.notes.set(position, note);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        this.notes.remove(position);
        notifyItemRemoved(position);
    }

    public void swap(int initPosition, int targetPosition) {
        Collections.swap(this.notes, initPosition, targetPosition);
        notifyItemMoved(initPosition, targetPosition);
    }

    @NonNull
    @Override
    public NoteRecyclerViewAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);

        VH vh = new VH(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteRecyclerViewAdapter.VH holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        Note note = notes.get(position);
        holder.setValues(note.getTitle(), note.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Note getItem(int position) {
        return notes.get(position);
    }

    class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView description;

        public VH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.note_item_title);
            description = itemView.findViewById(R.id.note_item_description);
        }

        public void setValues(String title, String description) {
            this.title.setText(title);
            this.description.setText(description);
        }

        @Override
        public void onClick(View view) {
            adapterItemClickListener.onAdapterClicked(getAdapterPosition());
        }
    }

}
