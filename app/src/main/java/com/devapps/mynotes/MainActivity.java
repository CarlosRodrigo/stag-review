package com.devapps.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.devapps.mynotes.adapter.NoteAdapter;
import com.devapps.mynotes.adapter.NoteRecyclerViewAdapter;
import com.devapps.mynotes.model.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setupListView();
        setupRecyclerView();

    }

    private void setupRecyclerView() {
        RecyclerView noteRecycler = findViewById(R.id.note_list);
        ArrayList<Note> notes = initNotes();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        noteRecycler.setLayoutManager(layoutManager);

        NoteRecyclerViewAdapter adapter = new NoteRecyclerViewAdapter(notes, this);
        noteRecycler.setAdapter(adapter);
    }

    private void setupListView() {
        ListView noteList = findViewById(R.id.note_list);

        ArrayList<Note> notes = initNotes();

        NoteAdapter noteAdapter = new NoteAdapter(notes, this);
        noteList.setAdapter(noteAdapter);
    }

    private ArrayList<Note> initNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Note note = new Note("Note " + i, "Description " + i);
            notes.add(note);
        }
        return notes;
    }
}