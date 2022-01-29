package com.example.note_taker_project.UI.Info;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.note_taker_project.App;
import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;
import com.example.note_taker_project.UI.NoteListFragment;

import java.util.ArrayList;
import java.util.Date;

public class InfoItemNoteFragment extends Fragment {
    public static final String NOTE_ARG_KEY = "NOTE_ARG_KEY";
    private EditText nameTextView;
    private EditText contentTextView;
    private TextView dateTextView;
    private Button saveButton;
    private Note note;
    private View view;
    private Controller controller;

    public interface Controller {
        void saveNoteInfo();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NoteListFragment.Controller) {
            controller = (Controller) context;
        } else throw new IllegalStateException("Activity must implement Controller");
    }

    public static InfoItemNoteFragment newInstance(Note note) {

        Bundle args = new Bundle();
        args.putParcelable(NOTE_ARG_KEY, note);
        InfoItemNoteFragment fragment = new InfoItemNoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_item_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        note = getArguments().getParcelable(NOTE_ARG_KEY);
        initViews();
        setContentInTextViews();
        saveButton.setOnClickListener(v -> {
            save(note);
        });
    }

    private void save(Note note) {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        Note tempNote = updateNote();
        App.get().noteRepository.saveNote(note, tempNote);
        App.get().adapter.setData(noteArrayList);
        App.get().adapter.notifyDataSetChanged();
        controller.saveNoteInfo();
    }

    private Note updateNote() {
        Note tempNote = new Note();
        tempNote.setId(note.getId());
        tempNote.setNoteName(nameTextView.getText().toString());
        tempNote.setNoteText(contentTextView.getText().toString());
        tempNote.setNoteDate(new Date(System.currentTimeMillis()));
        return tempNote;
    }

    private void initViews() {
        nameTextView = view.findViewById(R.id.info_note_name_textview);
        contentTextView = view.findViewById(R.id.info_note_text_textview);
        dateTextView = view.findViewById(R.id.info_note_date_textview);
        saveButton = view.findViewById(R.id.save_button);
    }

    private void setContentInTextViews() {
        nameTextView.setText(note.getNoteName());
        contentTextView.setText(note.getNoteText());
        dateTextView.setText(note.getNoteDate().toString());
    }
}
