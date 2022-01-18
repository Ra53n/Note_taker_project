package com.example.note_taker_project.UI;

import com.example.note_taker_project.Domain.Note;

interface OnNoteListener {
    void onDeleteNote(Note note);

    void onClickNote(Note note);
}
