package com.example.badgerhivemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

    public class MainActivity extends AppCompatActivity {

        BottomNavigationView bottomNavigation;
        private Button btn_edit_pic;
        public FirebaseAuth mAuth;
        String username;
        String email;
        String apiary_address;
    String phone_number;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.nav_profile);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);

//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                new ProfileFragment()).commit();

        // Initialize Firebase Auth

        mAuth = FirebaseAuth.getInstance();

        hideBottomBar(true);

        button =  findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

        //btn_photo_upload = findViewById(R.id.btn_photo);
    }

    public void openNewActivity() {
        Intent intent = new Intent(this , CreateHive.class);
        startActivity(intent);
    }

        @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.nav_hives:
                            selectedFragment = new HivesFragment();
                            break;
                    }

//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            selectedFragment).commit();

                    return true;
                }
            };

    public void hideBottomBar(boolean isHidden) {
        bottomNavigation.setVisibility(isHidden ? View.GONE : View.VISIBLE);
    }
}
