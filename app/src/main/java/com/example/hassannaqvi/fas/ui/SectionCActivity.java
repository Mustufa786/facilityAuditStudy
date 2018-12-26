package com.example.hassannaqvi.fas.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.core.MainApp;

public class SectionCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_c);
    }


    public void BtnContinue() {
        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {
//                startActivity(new Intent(getApplicationContext(), routeClass));
            } else {
                Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private boolean UpdateDB() {


        return false;

    }

    private void SaveDraft() {


    }


    private boolean formValidation() {

        return true;
    }

    public void BtnEnd() {

        SaveDraft();
        if (UpdateDB()) {
//            MainApp.endActivity(this, this, EndingActivity.class, false, fc_4_5);
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }
    }
}
