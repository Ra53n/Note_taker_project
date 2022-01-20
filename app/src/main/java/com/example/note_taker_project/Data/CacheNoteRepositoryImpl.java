package com.example.note_taker_project.Data;

import com.example.note_taker_project.Domain.NoteRepository;
import com.example.note_taker_project.Domain.Note;

import java.util.ArrayList;
import java.util.Date;

public class CacheNoteRepositoryImpl implements NoteRepository {
    private ArrayList<Note> cache = new ArrayList<>();

    public CacheNoteRepositoryImpl() {
        this.cache = createSomeNotes();
    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(cache);
    }


    @Override
    public void deleteNote(Note note) {
        try {
            this.cache.remove(note);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addNote(Note note) {
        cache.add(note);
    }

    @Override
    public void saveNote(Note oldNote, Note newNote) {
        try {
            int index = findPosition(oldNote);
            this.cache.set(index, newNote);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getNotePosition(Note note) {
        String noteId = note.getId();
        for(Note notes : cache){
            if(notes.getId().equals(noteId)){
                return cache.indexOf(notes);
            }
        }
        return -1;
    }

    private int findPosition(Note note) {
        try {
            for (Note n : cache) {
                if (n.getId().equals(note.getId())) {
                    return cache.indexOf(n);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private ArrayList<Note> createSomeNotes() {
        final ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("0", "Привет!", "Всем привет!", new Date(System.currentTimeMillis())));
        notes.add(new Note("1", "ПОКА!", "Всем ПОКА!", new Date(System.currentTimeMillis())));
        notes.add(new Note("2", "Привет!", "Всем привет!", new Date(System.currentTimeMillis())));
        notes.add(new Note("3", "ПОКА!", "Всем ПОКА!", new Date(System.currentTimeMillis())));
        notes.add(new Note("4", "Привет!", "Всем привет!", new Date(System.currentTimeMillis())));
        notes.add(new Note("5", "ПОКА!", "Всем ПОКА!", new Date(System.currentTimeMillis())));
        return notes;
    }
}
