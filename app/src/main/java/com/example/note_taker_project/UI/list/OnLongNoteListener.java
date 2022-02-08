package com.example.note_taker_project.UI.list;

import android.view.View;

import com.example.note_taker_project.Domain.Note;

public interface OnLongNoteListener {
    void onLongClickNote(Note note, View itemView);
}
