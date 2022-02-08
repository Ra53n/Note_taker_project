package com.example.note_taker_project.UI.info;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.note_taker_project.App;
import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;
import com.example.note_taker_project.UI.NoteColorChanger;
import com.google.android.material.snackbar.Snackbar;

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

        void showNoteInfo(Note note);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
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

    @RequiresApi(api = Build.VERSION_CODES.Q)
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
        initToolbar();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void initToolbar(){
        final Toolbar toolbar = getView().findViewById(R.id.fragment_info_item_note__toolbar);
        initMenu(toolbar);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void initMenu(Toolbar toolbar){
        final MenuInflater menuInflater = getActivity().getMenuInflater();
        final Menu menu = toolbar.getMenu();
        menuInflater.inflate(R.menu.fragment_info_item_note_menu,menu);
        menu.findItem(R.id.fragment_info_item_note_menu__save).setOnMenuItemClickListener(item ->{
            save(note);
            return true;
        });
        menu.findItem(R.id.fragment_info_item_note_menu__change_color).setOnMenuItemClickListener(item ->{
            PopupMenu popupMenu = new PopupMenu(getContext(), toolbar, Gravity.RIGHT);
            popupMenu.setForceShowIcon(true);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    NoteColorChanger noteColorChanger = new NoteColorChanger(note);
                    boolean res =  noteColorChanger.changeNoteColor(item.getItemId(), note);
                    ((CardView)getView()).setCardBackgroundColor(note.getColor());
                    return res;
                }
            });
            popupMenu.inflate(R.menu.color_change_menu);
            popupMenu.show();
            return true;
        });
    }

    private void save(Note note) {
        Note tempNote = updateNote();
        App.get().noteRepository.saveNote(note, tempNote);
        App.get().adapter.setData(App.get().noteRepository.getNotes());
        Snackbar snackbar = Snackbar.make(view, "Заметка сохранена!", Snackbar.LENGTH_LONG);
        snackbar.setTextColor(Color.BLACK);
        snackbar.setBackgroundTint(Color.WHITE);
        snackbar.show();
        getParentFragmentManager().popBackStack();
    }

    private Note updateNote() {
        Note tempNote = new Note();
        tempNote.setId(note.getId());
        tempNote.setNoteName(nameTextView.getText().toString());
        tempNote.setNoteText(contentTextView.getText().toString());
        tempNote.setNoteDate(new Date(System.currentTimeMillis()));
        tempNote.setColor(note.getColor());
        return tempNote;
    }

    private void initViews() {
        nameTextView = view.findViewById(R.id.fragment_info_item_note__name_textview);
        contentTextView = view.findViewById(R.id.fragment_info_item_note__text_textview);
        dateTextView = view.findViewById(R.id.fragment_info_item_note__date_textview);
        saveButton = view.findViewById(R.id.fragment_info_item_note__save_button);
    }

    private void setContentInTextViews() {
        nameTextView.setText(note.getNoteName());
        contentTextView.setText(note.getNoteText());
        dateTextView.setText(note.getNoteDate().toString());
        ((CardView) view).setCardBackgroundColor(note.getColor());
    }
}
