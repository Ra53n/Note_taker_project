package com.example.note_taker_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final NoteRepository noteRepository = new CacheNoteRepositoryImpl();
    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter();
        adapter.setData(noteRepository.getNotes());
        adapter.setOnDeleteClickListener(new NoteListener());
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
        }
    }
}