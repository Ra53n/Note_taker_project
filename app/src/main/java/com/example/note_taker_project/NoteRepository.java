package com.example.note_taker_project;

import java.util.List;

public interface NoteRepository {
    List<Note> getNotes();
    void deleteNote(Note note);
    void addNote(Note note);
}
