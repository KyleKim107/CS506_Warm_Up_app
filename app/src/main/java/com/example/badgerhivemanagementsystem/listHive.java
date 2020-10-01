package com.example.badgerhivemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.badgerhivemanagementsystem.ui.listhive.ListHiveFragment;

public class listHive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_hive_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ListHiveFragment.newInstance())
                .commitNow();
        }
    }
}
