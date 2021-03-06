package com.example.badgerhivemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import de.hdodenhof.circleimageview.CircleImageView;
//import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.example.badgerhivemanagementsystem.Model.User;
import com.example.badgerhivemanagementsystem.ui.listhive.ApiaryFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    // Register
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mSignUpBtn;
    TextView mSignInTxt;

    // Login
    EditText email_text, password_text;
    Button mSignInBtn;
    TextView mSignUpTxt;

    String fullName, email, password, phone, photoURL, id;

    // Firebase
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference reference;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.nav_profile);
        bottomNavigation.setOnNavigationItemSelectedListener(navListener);

       getSupportFragmentManager().beginTransaction().replace(R.id.container,
              new LoginFragment()).addToBackStack(null).commit();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        hideBottomBar(true);

        button =  findViewById(R.id.button2);
        button.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

        onStart();
    }

    public void openNewActivity() {
        Intent intent = new Intent(this , HivesFragment.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            openFragment(LoginFragment.newInstance());
        } else {
            //TODO
            createUI(currentUser);
        }
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void signUp(View view) {
        hideBottomBar(true);
        openFragment(RegisterFragment.newInstance());
    }

    public void signIn(View view) {
        hideBottomBar(true);
        openFragment(LoginFragment.newInstance());
    }

    public void login(View view) {
        EditText email_text = findViewById(R.id.email_login);
        EditText password_text = findViewById(R.id.password_login);

        email = email_text.getText().toString().trim();
        password = password_text.getText().toString().trim();

        if (email.isEmpty()) {
            mEmail.setError("Email address required.");
            mEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            mPassword.setError("Password required.");
            mPassword.requestFocus();
            return;
        }

        signInWithEmailAndPassword(email, password);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        openFragment(LoginFragment.newInstance());
        Toast.makeText(this, "Successfully logged out.",
                Toast.LENGTH_LONG).show();
        hideBottomBar(true);
    }

    public void addApiary(View view){
        //For test purposes
        String nameList[] = {"Mad Urban Bees \n\n 215 Martin Luther King Jr Blvd #017, Madison, WI 53703",
                "Dane County BeeKeepers Association \n\n 824 Jacobson Ave, Madison, WI 53714", "Liquid Gold Honey \n\n 617 Woodvale Dr, Madison, WI 53716"};

        ListView apiaryList;
        apiaryList = (ListView)findViewById(R.id.apiaryListView);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);
        apiaryList.setAdapter(myAdapter);

    }

    public void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            currentUser = mAuth.getCurrentUser();
                            id = mAuth.getCurrentUser().getUid();
                            createUI(currentUser);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void register(View view) {
        mFullName = findViewById(R.id.fullname_register);
        mEmail = findViewById(R.id.email_register);
        mPassword = findViewById(R.id.password_register);
        mPhone = findViewById(R.id.phone_register);
        mSignUpBtn = findViewById(R.id.signup_register);
        mSignInTxt = findViewById(R.id.signin_register);

        fullName = mFullName.getText().toString().trim();
        email = mEmail.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        phone = mPhone.getText().toString().trim();

        if (fullName.isEmpty()) {
            mFullName.setError("Full name required.");
            mFullName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            mEmail.setError("Email address required.");
            mEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Enter a valid email address.");
            mEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            mPassword.setError("Password required.");
            mPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            mPassword.setError("Password should be at least 6 characters long.");
            mPassword.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            mPhone.setError("Phone required.");
            mPhone.requestFocus();
            return;
        }
        if (password.length() < 10) {
            mPassword.setError("Enter a valid phone number.");
            mPassword.requestFocus();
            return;
        }
        createAccount(email, password);
    }


    public void createAccount(final String email_address, String password) {
        mAuth.createUserWithEmailAndPassword(email_address, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            currentUser = mAuth.getCurrentUser();
                            id = currentUser.getUid();

                            // User user = new User(id, fullName, "default", phone, new ArrayList<>(), new ArrayList<>());

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(id);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", id);
                            hashMap.put("name", fullName);
                            hashMap.put("imageURL", "default");
                            hashMap.put("phone", phone);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // updates database in real time
                                        createUI(currentUser);
                                    }
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void createUI(FirebaseUser user) {
        if (fullName != null) {
            // User has just signed up - update display name
            if (user != null) {
                UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                        .setDisplayName(fullName)
                        .build();
                user.updateProfile(profile);
            }
        } else {
            fullName = user.getDisplayName();
            phone = user.getPhoneNumber();
//            photoURL = user.getPhotoUrl();
        }
        openFragment(ProfileFragment.newInstance());
        hideBottomBar(false);
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
                           // openNewActivity();
                            selectedFragment = new HivesFragment();
                            break;
                        case R.id.nav_apiary:
                            selectedFragment = new ApiaryFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.container,
                            selectedFragment).addToBackStack(null).commit();

                    return true;
                }
            };

    public void hideBottomBar(boolean isHidden) {
        bottomNavigation.setVisibility(isHidden ? View.GONE : View.VISIBLE);
    }

    public void openSelectionFragment(View view) {
        openFragment(SelectionFragment.newInstance());
    }

    public void openProfileFragment(View view) {
        openFragment(ProfileFragment.newInstance());
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhotoURL() {
        return photoURL;
    }
}
