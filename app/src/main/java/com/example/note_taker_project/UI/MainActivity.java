package com.example.note_taker_project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;
import com.example.note_taker_project.UI.Info.InfoItemNoteFragment;

public class MainActivity extends AppCompatActivity implements NoteListFragment.Controller, InfoItemNoteFragment.Controller {
    private Fragment noteListFragment;
    private Fragment infoItemNoteFragment;


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
}