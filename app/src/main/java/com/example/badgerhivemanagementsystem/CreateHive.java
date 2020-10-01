package com.example.badgerhivemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.badgerhivemanagementsystem.R;

public class CreateHive extends AppCompatActivity {

    GridView gridView;

    String[] name = {"test name"};
    int[] images = {R.drawable.ic_launcher_background};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hive);

        gridView = findViewById(R.id.gridView);
        CustomAdapter customerAdapt = new CustomAdapter(    name , images , this);
        gridView.setAdapter(customerAdapt);

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
