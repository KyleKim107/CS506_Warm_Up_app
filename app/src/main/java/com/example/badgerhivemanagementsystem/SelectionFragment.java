package com.example.badgerhivemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.widget.Button;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SelectionFragment extends Fragment {
    private Button button;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_selection, container, false);
        return v;
    }

    /**
     * This method sets up the Selection Fragment
     */
    public static SelectionFragment newInstance() {
        SelectionFragment fragment = new SelectionFragment();
        return fragment;
    }

}