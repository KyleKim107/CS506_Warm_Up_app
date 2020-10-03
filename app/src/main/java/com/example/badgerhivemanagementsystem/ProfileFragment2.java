package com.example.badgerhivemanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ProfileFragment2 extends Fragment  {


    private Button myButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        myButton = (Button) view.findViewById(R.id.edit1);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v){

                Intent intent = new Intent(getActivity(), SelectionFragment.class);
                startActivity(intent);
            }

        });

        return view;
    }

}