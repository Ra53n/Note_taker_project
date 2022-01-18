package com.example.note_taker_project;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {
    private String id;
    private String noteName;
    private String noteText;
    private Date noteDate;

    protected Note(Parcel in) {
        id = in.readString();
        noteName = in.readString();
        noteText = in.readString();
        noteDate = new Date(in.readString());
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
    }

    public Note() {
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
    }
}
