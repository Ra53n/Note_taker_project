package com.example.note_taker_project.UI.list;

import android.content.Context;

import com.example.note_taker_project.App;
import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.UI.add.AddItemNoteFragment;

class NoteListener implements OnNoteListener {

    private final Context context;

    public NoteListener(Context context) {
        this.context = context;
    }

    @Override
    public void onDeleteNote(Note note) {
        App.get().noteRepository.deleteNote(note);
        App.get().adapter.deleteItem(note);
    }

    @Override
    public void onClickNote(Note note) {
        NoteListFragment.Controller controller = (NoteListFragment.Controller) context;
        controller.showNoteInfo(note);
    }

    @Override
    public void onAddNote() {
        AddItemNoteFragment.Controller controller = (AddItemNoteFragment.Controller) context;
        controller.openAddNote();
    }

}
