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
        switch (item) {
            case R.id.color_yellow:
                note.setColor(Color.YELLOW);
                App.get().adapter.notifyDataSetChanged();
                return true;
            case R.id.color_green:
                note.setColor(Color.GREEN);
                App.get().adapter.notifyDataSetChanged();
                return true;
            case R.id.color_pink:
                note.setColor(Color.PINK);
                App.get().adapter.notifyDataSetChanged();
                return true;
            case R.id.color_purple:
                note.setColor(Color.PURPLE);
                App.get().adapter.notifyDataSetChanged();
                return true;
            case R.id.color_blue:
                note.setColor(Color.BLUE);
                App.get().adapter.notifyDataSetChanged();
                return true;
            case R.id.color_grey:
                note.setColor(Color.GREY);
                App.get().adapter.notifyDataSetChanged();
                return true;
        }
        return false;
    }
}
