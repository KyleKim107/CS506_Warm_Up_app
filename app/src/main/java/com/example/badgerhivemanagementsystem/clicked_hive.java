package com.example.badgerhivemanagementsystem;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.badgerhivemanagementsystem.R;

public class clicked_hive  extends AppCompatActivity {


    ImageView imageView;
    TextView textView;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clicked_hive);

        imageView = imageView.findViewById(R.id.imageView);
        textView = textView.findViewById(R.id.tvName);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            String selectedName = intent.getStringExtra("name");
            int selectedImage = intent.getIntExtra("image", 0);

            textView.setText(selectedName);
            imageView.setImageResource(selectedImage);
        }

    }
}
