package com.example.note_taker_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private ArrayList<Note> data;

    public void setData(List<Note> notesList){
        data = (ArrayList<Note>) notesList;
    }

    public Note getNote(int position){
        return data.get(position);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.item_note,parent,false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final Note note = getNote(position);
        holder.noteNameTextView.setText(note.getNoteName());
        holder.noteTextTextView.setText(note.getNoteText());
        holder.noteDateTextView.setText(note.getNoteDate().toString());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
