package com.example.note_taker_project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.note_taker_project.App;
import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.Domain.NoteRepository;
import com.example.note_taker_project.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private NoteRepository noteRepository;
    private NoteListener noteListener;

    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        noteListener = new NoteListener();
        initRecycler();
        addButton = findViewById(R.id.main_activity_add_button);
        addButton.setOnClickListener(v -> {
            noteListener.onAddNote();
        });

    }

    private class NoteListener implements OnNoteListener {

        @Override
        public void onDeleteNote(Note note) {
            noteRepository.deleteNote(note);
            adapter.setDataWithRemoveItem(noteRepository.getNotes(), noteRepository.getNotePosition(note));
        }

        @Override
        public void onClickNote(Note note) {
            Intent intent = new Intent(MainActivity.this, InfoItemNoteActivity.class);
            intent.putExtra(InfoItemNoteActivity.NOTE_EXTRA_KEY, note);
            startActivity(intent);
        }

        @Override
        public void onAddNote() {
            Intent intent = new Intent(MainActivity.this, AddItemNoteActivity.class);
            startActivity(intent);
        }

    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = App.get().adapter;
        noteRepository = App.get().noteRepository;
        adapter.setData(noteRepository.getNotes());
        adapter.setOnClickListener(noteListener);
        recyclerView.setAdapter(adapter);
    }
}