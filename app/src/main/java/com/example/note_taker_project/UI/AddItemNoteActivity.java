package com.example.note_taker_project.UI;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.note_taker_project.App;
import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;

import java.util.ArrayList;
import java.util.Date;

public class AddItemNoteActivity extends AppCompatActivity {
    private Button addButton;
    private EditText nameTextView;
    private EditText contentTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_note);
        getSupportActionBar().hide();
        initViews();
        addButton.setOnClickListener(v -> {
            addNote();
        });

    }

    private void addNote(){
        Note note = new Note();
        note.setNoteName(nameTextView.getText().toString());
        note.setNoteText(contentTextView.getText().toString());
        note.setNoteDate(new Date(System.currentTimeMillis()));
        note.setId(String.valueOf(App.get().noteRepository.getNotes().size()+1));
        App.get().noteRepository.addNote(note);
        App.get().adapter.setData(App.get().noteRepository.getNotes());
        App.get().adapter.notifyDataSetChanged();
        this.finish();
    }

    private void initViews() {
        addButton = findViewById(R.id.add_button);
        nameTextView = findViewById(R.id.add_note_name_textview);
        contentTextView = findViewById(R.id.add_note_text_textview);
        dateTextView = findViewById(R.id.add_note_date_textview);
    }
}
