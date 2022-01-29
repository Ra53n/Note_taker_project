package com.example.note_taker_project.UI.add;

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

import java.util.Date;

public class AddItemNoteFragment extends Fragment {
    private Button addButton;
    private EditText nameTextView;
    private EditText contentTextView;
    private TextView dateTextView;
    private View view;
    private Controller controller;

    public interface Controller {
        void openAddNote();

        void closeAddNote();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else throw new IllegalStateException("Activity must implement Controller");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_item_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initViews();
        addButton.setOnClickListener(v -> {
            addNote();
        });
    }


    private void addNote() {
        Note note = new Note();
        note.setNoteName(nameTextView.getText().toString());
        note.setNoteText(contentTextView.getText().toString());
        note.setNoteDate(new Date(System.currentTimeMillis()));
        note.setId(String.valueOf(App.get().noteRepository.getNotes().size() + 1));
        App.get().noteRepository.addNote(note);
        App.get().adapter.setData(App.get().noteRepository.getNotes());
        App.get().adapter.notifyDataSetChanged();
        controller.closeAddNote();
    }

    private void initViews() {
        addButton = view.findViewById(R.id.add_button);
        nameTextView = view.findViewById(R.id.add_note_name_textview);
        contentTextView = view.findViewById(R.id.add_note_text_textview);
        dateTextView = view.findViewById(R.id.add_note_date_textview);
    }
}
