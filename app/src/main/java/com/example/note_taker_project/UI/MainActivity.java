package com.example.note_taker_project.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.note_taker_project.App;
import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;
import com.example.note_taker_project.UI.add.AddItemNoteFragment;
import com.example.note_taker_project.UI.info.InfoItemNoteFragment;
import com.example.note_taker_project.UI.list.NoteListFragment;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements InfoItemNoteFragment.Controller, AddItemNoteFragment.Controller {
    private Fragment noteListFragment;
    private Fragment infoItemNoteFragment;
    private Fragment addItemNoteFragment;
    private long backPressedCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if (savedInstanceState == null) {
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
                Snackbar snackbar = Snackbar.make(findViewById(R.id.activity_main__second_fragment_container),"При выходе данные не будут сохранены!",Snackbar.LENGTH_LONG);
                snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE);
                snackbar.setAction("Выйти", snackView ->{
                    super.onBackPressed();
                });
                snackbar.setTextColor(Color.BLACK);
                snackbar.setActionTextColor(Color.BLUE);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.show();
            }
        }catch (NullPointerException e){
            if (backPressedCounter + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(this, "Нажмите еще раз чтобы выйти!", Toast.LENGTH_LONG).show();
                backPressedCounter = System.currentTimeMillis();
            }
        }
    }
}