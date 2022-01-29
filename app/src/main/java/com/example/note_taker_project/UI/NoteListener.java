package com.example.note_taker_project.UI;

import android.content.Intent;

import com.example.note_taker_project.App;
import com.example.note_taker_project.Domain.Note;

class NoteListener implements OnNoteListener {

    private final MainActivity mainActivity;

    public NoteListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onDeleteNote(Note note) {
        App.get().noteRepository.deleteNote(note);
        App.get().adapter.deleteItem(note);
    }

    @Override
    public void onClickNote(Note note) {
        Intent intent = new Intent(mainActivity, InfoItemNoteActivity.class);
        intent.putExtra(InfoItemNoteActivity.NOTE_EXTRA_KEY, note);
        mainActivity.startActivity(intent);
    }

    @Override
    public void onAddNote() {
        Intent intent = new Intent(mainActivity, AddItemNoteActivity.class);
        mainActivity.startActivity(intent);
    }

}
