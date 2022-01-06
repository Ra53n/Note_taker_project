package com.example.note_taker_project;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CacheNoteRepositoryImpl implements NoteRepository{
    private ArrayList<Note> cache = new ArrayList<>();

    public CacheNoteRepositoryImpl() {
        this.cache = createSomeNotes();
    }

    public ArrayList<Note> getNotes() {
        return cache;
    }


    @Override
    public void deleteNote(Note note) {

    }

    @Override
    public void addNote(Note note) {

    }

    private ArrayList<Note> createSomeNotes(){
        final ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("0","Привет!","Всем привет!",new Date(System.currentTimeMillis())));
        notes.add(new Note("1","ПОКА!","Всем ПОКА!",new Date(System.currentTimeMillis())));
        return notes;
    }
}
