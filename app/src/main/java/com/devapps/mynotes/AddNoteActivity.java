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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mTitle = findViewById(R.id.editTitle);
        mDescription = findViewById(R.id.editDescription);

        setupButton();
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
                intent.putExtra("title", title);
                intent.putExtra("description", description);

                // enviar de volta pra main activity
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}