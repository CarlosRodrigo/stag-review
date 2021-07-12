package com.devapps.mynotes.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devapps.mynotes.R;
import com.devapps.mynotes.model.Note;

import java.util.ArrayList;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.VH> {

    private String TAG = "Recycler";

    private ArrayList<Note> notes;
    private Context context;

    public NoteRecyclerViewAdapter(ArrayList<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
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

    class VH extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_item_title);
            description = itemView.findViewById(R.id.note_item_description);
        }

        public void setValues(String title, String description) {
            this.title.setText(title);
            this.description.setText(description);
        }

    }

}
