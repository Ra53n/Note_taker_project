package com.example.note_taker_project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.note_taker_project.Data.CacheNoteRepositoryImpl;
import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.Domain.NoteRepository;
import com.example.note_taker_project.R;

public class MainActivity extends AppCompatActivity {
    protected static NoteRepository noteRepository = new CacheNoteRepositoryImpl();
    private RecyclerView recyclerView;
    protected static NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter();
        adapter.setData(noteRepository.getNotes());
        adapter.setOnClickListener(new NoteListener());
        recyclerView.setAdapter(adapter);
    }

    private class NoteListener implements OnNoteListener {

        @Override
        public void onDeleteNote(Note note) {
            noteRepository.deleteNote(note);
            adapter.setData(noteRepository.getNotes());
        }

        @Override
        public void onClickNote(Note note) {
            Intent intent = new Intent(MainActivity.this, InfoItemNoteActivity.class);
            intent.putExtra(InfoItemNoteActivity.NOTE_EXTRA_KEY, note);
            startActivity(intent);
        }
    }
}