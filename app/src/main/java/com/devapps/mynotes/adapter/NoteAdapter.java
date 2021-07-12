package com.devapps.mynotes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devapps.mynotes.R;
import com.devapps.mynotes.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {

    private ArrayList<Note> notes;
    private Context context;

    public NoteAdapter(ArrayList<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Note note = notes.get(position);

//        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.note_item, viewGroup, false);
//        }

        TextView title = view.findViewById(R.id.note_item_title);
        TextView description = view.findViewById(R.id.note_item_description);

        title.setText(note.getTitle());
        description.setText(note.getDescription());

        return view;
    }

}
