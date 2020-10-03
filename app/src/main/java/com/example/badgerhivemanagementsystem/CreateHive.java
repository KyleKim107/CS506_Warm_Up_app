package com.example.badgerhivemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;



public class CreateHive extends AppCompatActivity {

    GridView gridView;

    String[] name = {"Hive"};
    int[] images = {R.drawable.hive};
    Button addButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hive);

        gridView = findViewById(R.id.gridView);

        CustomAdapter customerAdapt = new CustomAdapter(    name , images , this);

        gridView.setAdapter(customerAdapt);
        addButton = findViewById(R.id.add);

        addButton.setOnClickListener( new View.OnClickListener(){

            //new Intent( CreateHive.this , Add_Hive.class)

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CreateHive.this , Add_Hive.class);
                startActivity(intent);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedName = name[i];
                int selectedImage = images[i];

                Log.i( "selectedImage",Integer.toString(selectedImage));



                Intent intend = new Intent(CreateHive.this, clicked_hive.class);
                intend.putExtra("name", selectedName).putExtra("image", selectedImage);
                startActivity(intend);
            }
        });

    }



    public class CustomAdapter extends BaseAdapter{

        private String[] imageName;
        private int[] imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(String[] imageName, int[] imagesPhoto, Context context) {
            this.imageName = imageName;
            this.imagesPhoto = imagesPhoto;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

        @Override
        public int getCount() {
            return  imagesPhoto.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                view = layoutInflater.inflate( R.layout.row_items, viewGroup, false);
            }

            TextView tvName = view.findViewById(R.id.tvName);
            ImageView imagePhoto = view.findViewById(R.id.imageView);

            tvName.setText(imageName[i]);
            imagePhoto.setImageResource(imagesPhoto[i]);


            return view;
        }
    }
}
