package com.example.note_taker_project.UI.add;

import android.content.Context;
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
import com.example.note_taker_project.UI.Color;
import com.example.note_taker_project.UI.NoteColorChanger;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class AddItemNoteFragment extends Fragment {
    private Button addButton;
    private EditText nameTextView;
    private EditText contentTextView;
    private TextView dateTextView;
    private View view;
    private Controller controller;
    private Note note = new Note();

    public interface Controller {
        void openAddNote();
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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initViews();
        addButton.setOnClickListener(v -> {
            addNote();
        });
        initToolbar();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void initToolbar() {
        final Toolbar toolbar = getView().findViewById(R.id.fragment_add_item_note__toolbar);
        initMenu(toolbar);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void initMenu(Toolbar toolbar) {
        final MenuInflater menuInflater = getActivity().getMenuInflater();
        final Menu menu = toolbar.getMenu();
        menuInflater.inflate(R.menu.fragment_add_item_note_menu, menu);
        menu.findItem(R.id.fragment_add_item_note_menu__save).setOnMenuItemClickListener(item -> {
            addNote();
            return true;
        });
        menu.findItem(R.id.fragment_add_item_note_menu__change_color).setOnMenuItemClickListener(item -> {
            PopupMenu popupMenu = new PopupMenu(getContext(), toolbar, Gravity.RIGHT);
            popupMenu.setForceShowIcon(true);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    NoteColorChanger noteColorChanger = new NoteColorChanger(note);
                    boolean res = noteColorChanger.changeNoteColor(item.getItemId(), note);
                    ((CardView) getView()).setCardBackgroundColor(note.getColor());
                    return res;
                }
            });
            popupMenu.inflate(R.menu.color_change_menu);
            popupMenu.show();
            return true;
        });
    }


    private void addNote() {
        this.note.setNoteName(nameTextView.getText().toString());
        this.note.setNoteText(contentTextView.getText().toString());
        this.note.setNoteDate(new Date(System.currentTimeMillis()));
        this.note.setId(String.valueOf(App.get().noteRepository.getNotes().size() + 1));
        App.get().noteRepository.addNote(note);
        this.note = new Note();
        App.get().adapter.setData(App.get().noteRepository.getNotes());
        snackbarSaveAlert();
        getParentFragmentManager().popBackStack();
    }

    private void snackbarSaveAlert() {
        Snackbar snackbar = Snackbar.make(view, "Заметка добавлена!", Snackbar.LENGTH_LONG);
        snackbar.setTextColor(Color.BLACK);
        snackbar.setBackgroundTint(Color.WHITE);
        snackbar.show();
    }

    private void initViews() {
        addButton = view.findViewById(R.id.fragment_add_item_note__add_button);
        nameTextView = view.findViewById(R.id.fragment_add_item_note__name_textview);
        contentTextView = view.findViewById(R.id.fragment_add_item_note__text_textview);
        dateTextView = view.findViewById(R.id.fragment_add_item_note__date_textview);
    }
}
