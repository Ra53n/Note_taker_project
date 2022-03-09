package com.example.note_taker_project.Domain;

import android.os.Parcel;
import android.os.Parcelable;


import com.example.note_taker_project.Util.Color;

import java.util.Date;

public class Note implements Parcelable {
    private String id;
    private String noteName;
    private String noteText;
    private Date noteDate;
    private int color;

    public int getColor() {
        return color;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected Note(Parcel in) {
        id = in.readString();
        noteName = in.readString();
        noteText = in.readString();
        noteDate = new Date(in.readString());
        color = in.readInt();
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getId() {
        return id;
    }

    public Note(String id, String noteName, String noteText, Date noteDate) {
        this.id = id;
        this.noteName = noteName;
        this.noteText = noteText;
        this.noteDate = noteDate;
        this.color = Color.YELLOW;
    }

    public Note() {
        this.color = Color.YELLOW;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(noteName);
        dest.writeString(noteText);
        dest.writeString(noteDate.toString());
        dest.writeInt(color);
    }
}
