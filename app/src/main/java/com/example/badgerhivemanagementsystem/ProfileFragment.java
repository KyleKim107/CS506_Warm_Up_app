package com.example.badgerhivemanagementsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public String fullName;
    public String phone;
    public String photoURL;
    View view;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        fullName = ((MainActivity)getActivity()).getFullName();
        phone = ((MainActivity)getActivity()).getPhone();
        photoURL = ((MainActivity)getActivity()).getPhotoURL();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        if (currentUser != null) {
            TextView textViewName = (TextView) view.findViewById(R.id.profileName);
            textViewName.setText(fullName);

            TextView textViewPhone = (TextView) view.findViewById(R.id.phoneNumber);
            textViewPhone.setText(phone);

            CircleImageView circleImageViewPhotoURL = (CircleImageView) view.findViewById(R.id.profile_image);
            circleImageViewPhotoURL.setImageResource(R.mipmap.ic_launcher);
//            if (photoURL.equals("default")) {
            //circleImageViewPhotoURL.setImageResource(R.mipmap.ic_launcher);
//            }
            //else {
            // ???????
            //Glide.with(this).load(photoURL).into(circleImageViewPhotoURL);
            //}
        }

        // Inflate the layout for this fragment
        return view;
    }
}
