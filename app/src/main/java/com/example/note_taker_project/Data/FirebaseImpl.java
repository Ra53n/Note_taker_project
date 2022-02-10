package com.example.note_taker_project.Data;



import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.note_taker_project.App;
import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.Domain.NoteRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FirebaseImpl implements NoteRepository {
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private List<Note> data = new ArrayList<>();
    private String DATA_TAG = "DATA_TAG";

    public FirebaseImpl() {
        loadData();
    }

    @Override
    public List<Note> getNotes() {
        loadData();
        return data;
    }

    public void loadData() {
        DatabaseReference reference = db.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    data.add(ds.getValue(Note.class));
                }
                data.sort(new Comparator<Note>() {
                    @Override
                    public int compare(Note o1, Note o2) {
                        return o2.getNoteDate().compareTo(o1.getNoteDate());
                    }
                });
                App.get().adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void deleteNote(Note note) {
        DatabaseReference reference = db.getReference();
        reference.child(note.getId()).setValue(null);

    }

    @Override
    public void addNote(Note note) {
        DatabaseReference reference = db.getReference(note.getId());
        reference.setValue(note);
    }

    @Override
    public void saveNote(Note oldNote, Note newNote) {
        DatabaseReference reference = db.getReference();
        reference.child(oldNote.getId()).setValue(newNote);
    }

    @Override
    public int getNotePosition(Note note) {
        return 0;
    }
}
