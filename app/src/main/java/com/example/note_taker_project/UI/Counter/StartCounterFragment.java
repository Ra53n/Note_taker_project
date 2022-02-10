package com.example.note_taker_project.UI.Counter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.note_taker_project.R;

public class StartCounterFragment extends Fragment {
    private String counter;
    private Controller controller;
    private final String COUNTER_KEY = "COUNTER_KEY";

    public interface Controller {
        void showCounterInfo();
    }

    public StartCounterFragment(int counter) {
        this.counter = String.valueOf(counter);
    }

    public StartCounterFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(COUNTER_KEY, counter);
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
        return inflater.inflate(R.layout.fragment_start_counter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.fragment_start_counter_text_view);
        if (counter != null) {
            textView.setText(String.valueOf(counter));
        } else if (savedInstanceState != null && savedInstanceState.containsKey(COUNTER_KEY)) {
            textView.setText(savedInstanceState.getString(COUNTER_KEY));
        }
    }
}
