package com.example.note_taker_project.UI;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.note_taker_project.Data.SharedPreferencesStartCounter;
import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;
import com.example.note_taker_project.UI.Counter.StartCounterFragment;
import com.example.note_taker_project.UI.add.AddItemNoteFragment;
import com.example.note_taker_project.UI.info.InfoItemNoteFragment;
import com.example.note_taker_project.UI.list.NoteListFragment;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity implements InfoItemNoteFragment.Controller, AddItemNoteFragment.Controller, StartCounterFragment.Controller {
    private Fragment noteListFragment;
    private Fragment infoItemNoteFragment;
    private Fragment addItemNoteFragment;
    private long backPressedCounter;
    private SharedPreferencesStartCounter sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        sharedPreferences = new SharedPreferencesStartCounter(this);

        if (savedInstanceState == null) {
            sharedPreferences.incrementCounter();
            noteListFragment = new NoteListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main__main_fragment_container, noteListFragment)
                    .commit();
        }
    }

    @Override
    public void showNoteInfo(Note note) {
        infoItemNoteFragment = InfoItemNoteFragment.newInstance(note);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__second_fragment_container, infoItemNoteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openAddNote() {
        addItemNoteFragment = new AddItemNoteFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__second_fragment_container, addItemNoteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        try {
            if (infoItemNoteFragment.getClass().equals(getSupportFragmentManager().findFragmentById(R.id.activity_main__second_fragment_container).getClass())
                    || addItemNoteFragment.getClass().equals(getSupportFragmentManager().findFragmentById(R.id.activity_main__second_fragment_container).getClass())) {
                AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
                alertDialogFragment.show(getSupportFragmentManager(), "");
            }
        } catch (NullPointerException e) {
            if (backPressedCounter + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(this, "Нажмите еще раз чтобы выйти", Toast.LENGTH_LONG).show();
                backPressedCounter = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void showCounterInfo() {
        StartCounterFragment startCounterFragment = new StartCounterFragment(sharedPreferences.getCounter());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__second_fragment_container, startCounterFragment)
                .addToBackStack(null)
                .commit();
    }
}