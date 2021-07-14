package com.devapps.mynotes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.devapps.mynotes.adapter.NoteAdapter;
import com.devapps.mynotes.adapter.NoteItemTouchHelperCallback;
import com.devapps.mynotes.adapter.NoteRecyclerViewAdapter;
import com.devapps.mynotes.adapter.OnAdapterItemClickListener;
import com.devapps.mynotes.model.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnAdapterItemClickListener {

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
                startActivityForResult(intent, Constants.REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // recuperar o novo item
            String title = data.getStringExtra(Constants.EXTRA_KEY_TITLE);
            String description = data.getStringExtra(Constants.EXTRA_KEY_DESCRIPTION);
            Note note = new Note(title, description);

            if (requestCode == Constants.REQUEST_CODE_ADD) {
                mAdapter.addNote(note);
            } else if (requestCode == Constants.REQUEST_CODE_EDIT) {
                int position = data.getIntExtra(Constants.EXTRA_KEY_POSITION, -1);
                mAdapter.updateNote(note, position);
            }
        }

    }

    private void setupRecyclerView() {
        RecyclerView noteRecycler = findViewById(R.id.note_list);
        ArrayList<Note> notes = initNotes();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        noteRecycler.setLayoutManager(layoutManager);

        mAdapter = new NoteRecyclerViewAdapter(notes, this, this::onAdapterClicked);
        noteRecycler.setAdapter(mAdapter);

        NoteItemTouchHelperCallback itemCallback = new NoteItemTouchHelperCallback(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemCallback);
        itemTouchHelper.attachToRecyclerView(noteRecycler);
    }

    private void setupListView() {
        ListView noteList = findViewById(R.id.note_list);

        ArrayList<Note> notes = initNotes();

        NoteAdapter noteAdapter = new NoteAdapter(notes, this);
        noteList.setAdapter(noteAdapter);
    }

    private ArrayList<Note> initNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Note note = new Note("Note " + i, "Description " + i);
            notes.add(note);
        }
        return notes;
    }

    @Override
    public void onAdapterClicked(int position) {
//        Toast.makeText(this, "position: " + position, Toast.LENGTH_SHORT).show();
        Note note = mAdapter.getItem(position);
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_TITLE, note.getTitle());
        intent.putExtra(Constants.EXTRA_KEY_DESCRIPTION, note.getDescription());
        intent.putExtra(Constants.EXTRA_KEY_POSITION, position);

        startActivityForResult(intent, Constants.REQUEST_CODE_EDIT);
    }
}