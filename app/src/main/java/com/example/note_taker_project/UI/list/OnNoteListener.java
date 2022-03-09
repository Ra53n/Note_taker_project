package com.example.note_taker_project.UI.list;

import com.example.note_taker_project.Domain.Note;

interface OnNoteListener {
    void onDeleteNote(Note note);

    void onClickNote(Note note);

    void onAddNote();

    void onShowCounterInfo();

}
