package com.example.badgerhivemanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class HivesFragment extends Fragment {

    GridView gridView;
    View view;

    Button addButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hives, container, false);

        String[] menuItems = { "1","2","3","4","5","6"};
        //gridView = (GridView) view.findViewById(R.id.gridView);
        ListView listview = (ListView) view.findViewById(R.id.gridView);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String >(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                menuItems
        );

        listview.setAdapter(listViewAdapter);
        return view;
    }

//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
}
//
//        CustomAdapter customerAdapt = new CustomAdapter(    name , images , this);
//        gridView.setAdapter(customerAdapt);
//        addButton = findViewById(R.id.add);
//
//        addButton.setOnClickListener( new View.OnClickListener(){
//
//            //new Intent( CreateHive.this , Add_Hive.class)
//
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(HivesFragment.this , Add_Hive.class);
//                startActivity(intent);
//            }
//        });
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String selectedName = name[i];
//                int selectedImage = images[i];
//
//                Log.i( "selectedImage",Integer.toString(selectedImage));
//
//
//
//                Intent intend = new Intent(HivesFragment.this, clicked_hive.class);
//                intend.putExtra("name", selectedName).putExtra("image", selectedImage);
//                startActivity(intend);
//            }
//        });
