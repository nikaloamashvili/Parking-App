package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    String me;

    private FirebaseAuth mAuth;
//    private HashMap<String, String> userhash = new HashMap<String,String>();
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

    }

    public void LoginFunc(MyCallback2 myCallback){
        String email = ((EditText)findViewById(R.id.username)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"Login Ok",Toast.LENGTH_LONG).show();
                            myCallback.onCallback(Boolean.TRUE);
                        } else {

                            Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void RegFunc(){
        String id = ((EditText)findViewById(R.id.registerID)).getText().toString();
        String email = ((EditText)findViewById(R.id.registerEmailAddress)).getText().toString();
        String password = ((EditText)findViewById(R.id.registerPassword)).getText().toString();
        Log.d("result" , email +" " + password);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                                   Toast.makeText(MainActivity.this,"reg ok!",Toast.LENGTH_LONG).show();
                        } else {
                                    Toast.makeText(MainActivity.this,"reg faild!",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    public void AddData(){
        // Write a message to the database
        String email = ((EditText)findViewById(R.id.registerEmailAddress)).getText().toString();
        String temail=removeInvalidChars(email);
        String password = ((EditText)findViewById(R.id.registerPassword)).getText().toString();
        String ID = ((EditText)findViewById(R.id.registerID)).getText().toString();
        String Phone = ((EditText)findViewById(R.id.registerPhone)).getText().toString();
        User user = new User(ID,temail,Phone,0,0);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(user.getEmail());

        myRef.setValue(user);
    }

    public void GetData(MyCallback3 myCallback){
       String email = ((EditText)findViewById(R.id.username)).getText().toString();
       String temail=removeInvalidChars(email);
       me=temail;

        User user = new User();
        user.setEmail(temail);
        //FirebaseAuth.getInstance().getUserByEmail()
//                UserRecord
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(user.getEmail());

        // Read from the database
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User value =dataSnapshot.getValue(User.class);
                myCallback.onCallback(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this,"SOMETHING WENT WRONG",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void GetLocations(MyCallback myCallback){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Location");
        List<Parking> list = new ArrayList<Parking>();
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    list.add(snapshot.getValue(Parking.class));
                    Log.d("result" , snapshot.getValue(Parking.class).getLongitude().toString());
                    myCallback.onCallback(snapshot.getValue(Parking.class));
                }

            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    public void Addlocation(Parking P){
        // Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Location").child(P.getName());
        myRef.setValue(P);
    }

    public void editData(String addres,int x){
        // Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Location").child(addres).child("isTaken");
        myRef.setValue(x);
    }

    public void editDatauser(String addres,int x){
        // Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(addres).child("add_park");
        myRef.setValue(x);
    }

    public void editDatauser2(String addres,int x){
        // Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(addres).child("likes");
        myRef.setValue(x);
    }

    public void GetDataof(MyCallback4 myCallback){

        User user = new User();
        user.setEmail(me);
        //FirebaseAuth.getInstance().getUserByEmail()
//                UserRecord
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(user.getEmail());

        // Read from the database
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User value = dataSnapshot.getValue(User.class);
                myCallback.onCallback(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this,"SOMETHING WENT WRONG",Toast.LENGTH_LONG).show();
            }
        });
    }


    public void RegisterFunction(View view) {
        RegFunc();
        AddData();
    }


    public static String removeInvalidChars(String text) {
        text = text.replace(".", "");
        text = text.replace("$", "");
        text = text.replace("#", "");
        text = text.replace("[", "");
        text = text.replace("]", "");
        text = text.replace("/", "");
        return text;
    }



    public  interface MyCallback {
        void onCallback(Parking parkings);
    }

    public  interface MyCallback2 {
        void onCallback(Boolean logino);
    }

    public  interface MyCallback3 {
        void onCallback(User user);
    }

    public  interface MyCallback4 {
        void onCallback(User user);
    }





}