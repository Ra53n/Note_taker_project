package com.example.note_taker_project.UI;

import android.content.Intent;
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

public class InfoItemNoteActivity extends AppCompatActivity {
    public static final String NOTE_EXTRA_KEY = "NOTE_EXTRA_KEY";
    private EditText nameTextView;
    private EditText contentTextView;
    private TextView dateTextView;
    private Button saveButton;
    private Note note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item_note);
        getSupportActionBar().hide();
        note = getIntent().getParcelableExtra(NOTE_EXTRA_KEY);
        initViews();
        setContentInTextViews();
        saveButton.setOnClickListener(v -> {
            save(note);
        });
    }

    private void save(Note note) {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        Note tempNote = updateNote();
        App.get().noteRepository.saveNote(note, tempNote);
        App.get().adapter.setData(noteArrayList);
        App.get().adapter.notifyDataSetChanged();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private Note updateNote() {
        Note tempNote = new Note();
        tempNote.setId(note.getId());
        tempNote.setNoteName(nameTextView.getText().toString());
        tempNote.setNoteText(contentTextView.getText().toString());
        tempNote.setNoteDate(new Date(System.currentTimeMillis()));
        return tempNote;
    }

    private void initViews() {
        nameTextView = findViewById(R.id.info_note_name_textview);
        contentTextView = findViewById(R.id.info_note_text_textview);
        dateTextView = findViewById(R.id.info_note_date_textview);
        saveButton = findViewById(R.id.save_button);
    }

    private void setContentInTextViews() {
        nameTextView.setText(note.getNoteName());
        contentTextView.setText(note.getNoteText());
        dateTextView.setText(note.getNoteDate().toString());
    }

}
