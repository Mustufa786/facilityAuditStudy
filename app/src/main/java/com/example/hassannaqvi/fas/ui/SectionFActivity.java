package com.example.hassannaqvi.fas.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hassannaqvi.fas.R;

public class SectionFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_f);
    }

    public void BtnContinue() {
        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {

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

        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }
    }
}
