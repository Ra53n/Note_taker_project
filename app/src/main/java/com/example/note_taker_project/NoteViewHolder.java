package com.example.note_taker_project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    TextView noteNameTextView = itemView.findViewById(R.id.note_name_textview);
    TextView noteTextTextView = itemView.findViewById(R.id.note_text_textview);
    TextView noteDateTextView = itemView.findViewById(R.id.note_date_textview);
    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
