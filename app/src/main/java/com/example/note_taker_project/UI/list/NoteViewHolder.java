package com.example.note_taker_project.UI.list;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView noteNameTextView = itemView.findViewById(R.id.item_note__name_textview);
    private final TextView noteTextTextView = itemView.findViewById(R.id.item_note__content_textview);
    private final TextView noteDateTextView = itemView.findViewById(R.id.item_note__date_textview);
    private final Button deleteButton = itemView.findViewById(R.id.item_note__delete_button);

    private Note note;

    public NoteViewHolder(@NonNull View itemView, OnNoteListener onClickListener) {
        super(itemView);
        deleteButton.setOnClickListener(v -> {
            onClickListener.onDeleteNote(note);
        });
        itemView.setOnClickListener(v -> {
            onClickListener.onClickNote(note);
        });
    }

    public void bind(Note note) {
        this.note = note;
        noteNameTextView.setText(note.getNoteName());
        noteTextTextView.setText(note.getNoteText());
        noteDateTextView.setText(note.getNoteDate().toString());
    }


}
