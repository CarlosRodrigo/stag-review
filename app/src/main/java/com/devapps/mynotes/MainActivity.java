package com.devapps.mynotes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.devapps.mynotes.adapter.NoteAdapter;
import com.devapps.mynotes.adapter.NoteRecyclerViewAdapter;
import com.devapps.mynotes.model.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NoteRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setupListView();
        setupRecyclerView();
        setupButton();
    }

    private void setupButton() {
        Button add = findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                // recuperar o novo item
                String title = data.getStringExtra("title");
                String description = data.getStringExtra("description");

                Note note = new Note(title, description);
                mAdapter.addNote(note);
            }
        }

    }

    private void setupRecyclerView() {
        RecyclerView noteRecycler = findViewById(R.id.note_list);
        ArrayList<Note> notes = initNotes();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        noteRecycler.setLayoutManager(layoutManager);

        mAdapter = new NoteRecyclerViewAdapter(notes, this);
        noteRecycler.setAdapter(mAdapter);
    }

    private void setupListView() {
        ListView noteList = findViewById(R.id.note_list);

        ArrayList<Note> notes = initNotes();

        NoteAdapter noteAdapter = new NoteAdapter(notes, this);
        noteList.setAdapter(noteAdapter);
    }

    private ArrayList<Note> initNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Note note = new Note("Note " + i, "Description " + i);
            notes.add(note);
        }
        return notes;
    }
}