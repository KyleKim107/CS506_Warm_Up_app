package com.example.badgerhivemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    Button mEditPicture;
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mSignInBtn;
    TextView mSignInTxt;

    // Login
    Button mSignUpBtn;
    TextView mSignUpTxt;

    String fullName, email, password, phone, photoURL, id;

    // Profile
    TextView mDisplayName, mPhoneNumber;
    CircleImageView mProfileImage;
    Button logoutBtn;
    TextView profileBackground;

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
        mProfileImage = findViewById(R.id.profile_image);
        logoutBtn = findViewById(R.id.logoutBtn);
        mDisplayName = findViewById(R.id.profileName);
        mPhoneNumber = findViewById(R.id.phoneNumber);
        profileBackground = findViewById(R.id.profileBackground);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

        button.setVisibility(View.GONE);
        mProfileImage.setVisibility(View.GONE);
        logoutBtn.setVisibility(View.GONE);
        mDisplayName.setVisibility(View.GONE);
        mPhoneNumber.setVisibility(View.GONE);
        profileBackground.setVisibility(View.GONE);

        onStart();
    }

    public void openNewActivity() {
        Intent intent = new Intent(this , CreateHive.class);
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
        mEditPicture = findViewById(R.id.editpic_register);
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
        if (!Patterns.PHONE.matcher(phone).matches()) {
            mPhone.setError("Enter a valid phone number.");
            mPhone.requestFocus();
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

        profileBackground.setVisibility(View.VISIBLE);
        mProfileImage.setVisibility(View.VISIBLE);
        logoutBtn.setVisibility(View.VISIBLE);
        mDisplayName.setVisibility(View.VISIBLE);
        mPhoneNumber.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                mDisplayName.setText(user.getName());
                if (user.getImageURL().equals("default")) {
                    mProfileImage.setImageResource(R.mipmap.ic_launcher);

                } else {
                    Glide.with(MainActivity.this).load(user.getImageURL()).into(mProfileImage);
                }
                openFragment(ProfileFragment.newInstance("", ""));
                hideBottomBar(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        openFragment(ProfileFragment.newInstance("", ""));
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
                            openNewActivity();
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
