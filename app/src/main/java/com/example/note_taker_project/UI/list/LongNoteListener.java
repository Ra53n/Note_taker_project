package com.example.note_taker_project.UI.list;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.RequiresApi;

import com.example.note_taker_project.Domain.Note;
import com.example.note_taker_project.R;
import com.example.note_taker_project.UI.NoteColorChanger;

public class LongNoteListener implements OnLongNoteListener {

    private final Context context;

    public LongNoteListener(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onLongClickNote(Note note, View itemView) {
        PopupMenu popupMenu = new PopupMenu(context, itemView, Gravity.CENTER);
        popupMenu.setForceShowIcon(true);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                NoteColorChanger noteColorChanger = new NoteColorChanger(note);
                return noteColorChanger.changeNoteColor(item.getItemId(), note);
            }
        });
        popupMenu.inflate(R.menu.color_change_menu);
        popupMenu.show();
    }
}
