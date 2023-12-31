package com.example.sharedpreferenceslesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //instantiate EditText + layout
    EditText name, age, food;
    ConstraintLayout mainLayout;

    //instantiate sharedPreferences object + another object used to edit it
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextText);
        age = findViewById(R.id.editTextText2);
        food = findViewById(R.id.editTextText3);

        //set view to layout
        mainLayout = findViewById(R.id.mainLayout);

        //create/retrieve the phone's unique sharedPreferences object from phone's private sharedPreferences
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
    }

    public void saveInfo(View v){
        Log.d("Sai", "point");

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", name.getText().toString());
        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
        myEdit.putString("food", food.getText().toString());
        myEdit.apply();

        Snackbar snackbar = Snackbar.make(v, "Info saved", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void clearInfo(){
        name.setText("");
        age.setText("");
        food.setText("");
    }

    public void refreshInfo(View v) {
        //take the data from sharedPreferences and turn it into string
        String s1 = sharedPreferences.getString("name", "");
        int a = sharedPreferences.getInt("age", 0);
        String s2 = sharedPreferences.getString("food", "");

        //set the editTexts to the values of the sharedPreferences
        name.setText(s1);
        age.setText(String.valueOf(a));
        food.setText(s2);

        Snackbar snackbar = Snackbar.make(v, "Info retrieved", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}