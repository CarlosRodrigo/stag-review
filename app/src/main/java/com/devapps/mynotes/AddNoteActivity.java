package com.devapps.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.devapps.mynotes.model.Note;

public class AddNoteActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mDescription;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mTitle = findViewById(R.id.editTitle);
        mDescription = findViewById(R.id.editDescription);

        setupButton();
        setupValues();
    }

    private void setupValues() {
        Intent intent = getIntent();

        String title = intent.getStringExtra(Constants.EXTRA_KEY_TITLE);
        String description = intent.getStringExtra(Constants.EXTRA_KEY_DESCRIPTION);
        mPosition = intent.getIntExtra(Constants.EXTRA_KEY_POSITION, -1);

        mTitle.setText(title);
        mDescription.setText(description);
    }

    private void setupButton() {
        Button add = findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // recuperar os dados dos editText
                String title = mTitle.getText().toString();
                String description = mDescription.getText().toString();

                // cria intent de retorno
                Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_TITLE, title);
                intent.putExtra(Constants.EXTRA_KEY_DESCRIPTION, description);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);

                // enviar de volta pra main activity
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}