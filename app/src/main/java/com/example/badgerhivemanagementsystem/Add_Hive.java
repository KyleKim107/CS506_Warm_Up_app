package com.example.badgerhivemanagementsystem;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Add_Hive extends AppCompatActivity {

    private ImageView Image;
    private EditText Name;
    private EditText Honey_Stores;
    private EditText Information;
    private EditText Gain_Loss;
    private EditText Inventory;
    private EditText Q_Production;
    private EditText Inspection;
    private EditText Health_Quality;
    private EditText Owner;

    private Bitmap ImageI;
    private String NameS;
    private String Honey_StoresS;
    private String InformationS;
    private String Gain_LossS;
    private String InventoryS;
    private String Q_ProductionS;
    private String InspectionS;
    private String Health_QualityS;
    private String OwnerS;


    DatabaseReference mDBReference = null;
    HashMap<String, Object> childUpdates = null;
    Map<String, Object> HiveValue = null;
    Hive_Data HiveInfo = null;


    private Button submit;

    Hive_Data hive;

    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__hive);
        Image = (ImageView) findViewById(R.id.imageView2);
        Name = (EditText) findViewById(R.id.editText9);
        Honey_Stores= (EditText) findViewById(R.id.editText5);
        Information = (EditText) findViewById(R.id.editText6);
        Gain_Loss = (EditText) findViewById(R.id.editText8);
        Inventory = (EditText) findViewById(R.id.editText7);
        Q_Production = (EditText) findViewById(R.id.editText4);
        Inspection = (EditText) findViewById(R.id.editText11);
        Health_Quality = (EditText) findViewById(R.id.editText12);
        Owner = (EditText) findViewById(R.id.editText10);

        submit = (Button) findViewById(R.id.button1);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();

        mDBReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentFirebaseUser.getUid());
        hive = new Hive_Data();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageI = null;
                //hive.setImage(R.drawable.hive);
                NameS = Name.getText().toString();
                hive.setName(NameS);
                Honey_StoresS = Honey_Stores.getText().toString();
                hive.setHoney_Stores(Honey_StoresS);
                InformationS = Information.getText().toString();
                hive.setInformation(InformationS);
                Gain_LossS = Gain_Loss.getText().toString();
                hive.setGain_Loss(Gain_LossS);
                InventoryS =Inventory.getText().toString();
                hive.setInventory(InventoryS);
                Q_ProductionS = Q_Production.getText().toString();
                hive.setQ_Production(Q_ProductionS);
                InspectionS = Inspection.getText().toString();
                hive.setInspection(InspectionS);
                Health_QualityS = Health_Quality.getText().toString();
                hive.setHealth_Quality(Health_QualityS);
                OwnerS = Owner.getText().toString();
                hive.setOwner(OwnerS);

                Log.w("Check the data" ,ImageI+ " "+NameS + " "+Honey_StoresS + " "+InformationS + " "+InspectionS  );

                mDBReference.child("Hives").child(NameS).setValue(hive);

                    mDBReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            String value = dataSnapshot.getValue().toString();
                            Log.d("Database", "Value is: " + value);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("Database", "Failed to read value.", error.toException());
                        }
                    });

            Intent intent = new Intent(Add_Hive.this , MainActivity.class);
            startActivity(intent);
            }
        });
    }

}
