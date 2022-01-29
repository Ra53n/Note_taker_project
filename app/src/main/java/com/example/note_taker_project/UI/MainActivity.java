package com.example.note_taker_project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;
import com.example.note_taker_project.UI.add.AddItemNoteFragment;
import com.example.note_taker_project.UI.info.InfoItemNoteFragment;
import com.example.note_taker_project.UI.list.NoteListFragment;

public class MainActivity extends AppCompatActivity implements NoteListFragment.Controller, InfoItemNoteFragment.Controller, AddItemNoteFragment.Controller {
    private Fragment noteListFragment;
    private Fragment infoItemNoteFragment;
    private Fragment addItemNoteFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if (savedInstanceState == null) {
            noteListFragment = new NoteListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, noteListFragment)
                    .commit();
        }
    }

    @Override
    public void showNoteInfo(Note note) {
        infoItemNoteFragment = InfoItemNoteFragment.newInstance(note);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, infoItemNoteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void saveNoteInfo() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, noteListFragment)
                .commit();
    }


    @Override
    public void openAddNote() {
        addItemNoteFragment = new AddItemNoteFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, addItemNoteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void closeAddNote() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, noteListFragment)
                .commit();
    }
}