package com.example.note_taker_project.Util;

import com.example.note_taker_project.App;
import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;

public class NoteColorChanger {
    private Note note;

    public NoteColorChanger(Note note) {
        this.note = note;
    }

    public boolean changeNoteColor(int item, Note note) {
        Note oldNote = note;
        switch (item) {
            case R.id.color_yellow:
                note.setColor(Color.YELLOW);
                saveChanges(oldNote,note);
                return true;
            case R.id.color_green:
                note.setColor(Color.GREEN);
                saveChanges(oldNote,note);
                return true;
            case R.id.color_pink:
                note.setColor(Color.PINK);
                saveChanges(oldNote,note);
                return true;
            case R.id.color_purple:
                note.setColor(Color.PURPLE);
                saveChanges(oldNote,note);
                return true;
            case R.id.color_blue:
                note.setColor(Color.BLUE);
                saveChanges(oldNote,note);
                return true;
            case R.id.color_grey:
                note.setColor(Color.GREY);
                saveChanges(oldNote,note);
                return true;
        }
        return false;
    }

    private void saveChanges(Note oldNote, Note note){
        App.get().noteRepository.saveNote(oldNote,note);
        App.get().adapter.notifyDataSetChanged();
    }
}
