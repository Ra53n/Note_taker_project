package com.example.note_taker_project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InfoItemNoteActivity extends AppCompatActivity {
    public static final String NOTE_EXTRA_KEY = "NOTE_EXTRA_KEY";
    private TextView nameTextView;
    private TextView contentTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item_note);
        getSupportActionBar().hide();
        nameTextView = findViewById(R.id.info_note_name_textview);
        contentTextView = findViewById(R.id.info_note_text_textview);
        dateTextView = findViewById(R.id.info_note_date_textview);
        Note note = getIntent().getParcelableExtra(NOTE_EXTRA_KEY);
        nameTextView.setText(note.getNoteName());
        contentTextView.setText(note.getNoteText());
        dateTextView.setText(note.getNoteDate().toString());
    }
}
