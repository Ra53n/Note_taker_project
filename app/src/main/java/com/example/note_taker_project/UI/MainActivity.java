package com.example.note_taker_project.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        noteListener = new NoteListener(this);
        initRecycler();
        addButton = findViewById(R.id.main_activity_add_button);
        addButton.setOnClickListener(v -> {
            noteListener.onAddNote();
        });

    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = App.get().adapter;
        noteRepository = App.get().noteRepository;
        adapter.setData(noteRepository.getNotes());
        adapter.setOnClickListener(noteListener);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private final ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            int index = viewHolder.getAdapterPosition();
            noteRepository.deleteNote(noteRepository.getNotes().get(index));
            adapter.setDataWithRemoveItem(noteRepository.getNotes(), index);
        }
    };
}