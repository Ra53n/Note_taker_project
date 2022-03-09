package com.example.note_taker_project;

import android.app.Application;

import com.example.note_taker_project.Data.CacheNoteRepositoryImpl;
import com.example.note_taker_project.Data.FirebaseImpl;
import com.example.note_taker_project.Domain.NoteRepository;
import com.example.note_taker_project.UI.list.NoteAdapter;
import com.google.firebase.FirebaseApp;

public class App extends Application {
    private static App sInstance;

    public NoteRepository noteRepository;
    public NoteAdapter adapter = new NoteAdapter();

    @Override
    public void onCreate() {
        super.onCreate();
        noteRepository = new FirebaseImpl();
        sInstance = this;
    }

    public static App get() {
        return sInstance;
    }
}
