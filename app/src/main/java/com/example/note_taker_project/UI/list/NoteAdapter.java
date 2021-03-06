package com.example.note_taker_project.UI.list;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private ArrayList<Note> data;
    private OnNoteListener onClickListener;
    private OnLongNoteListener onLongNoteListener;

    public void setOnLongNoteListener(OnLongNoteListener onLongNoteListener) {
        this.onLongNoteListener = onLongNoteListener;
    }

    public void setOnClickListener(OnNoteListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setData(List<Note> notesList) {
        data = (ArrayList<Note>) notesList;
        Collections.sort(data, new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o1.getNoteDate().compareTo(o2.getNoteDate());
            }
        });
        notifyDataSetChanged();
    }

    public void deleteItem(Note note) {
        String noteId = note.getId();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(noteId)) {
                data.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
    }

    public Note getNote(int position) {
        return data.get(position);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView, onClickListener, onLongNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final Note note = getNote(position);
        holder.bind(note);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
