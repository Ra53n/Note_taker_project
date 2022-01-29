package com.example.note_taker_project;

import android.app.Application;

import com.example.note_taker_project.Data.CacheNoteRepositoryImpl;
import com.example.note_taker_project.Domain.NoteRepository;
import com.example.note_taker_project.UI.list.NoteAdapter;

public class App extends Application {
    private static App sInstance;

    public NoteRepository noteRepository = new CacheNoteRepositoryImpl();
    public NoteAdapter adapter = new NoteAdapter();

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App get() {
        return sInstance;
    }
}
