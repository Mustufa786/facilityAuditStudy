package com.example.hassannaqvi.fas.ui.tool2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hassannaqvi.fas.R;

public class SectionB_tool_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_b_tool_2);
    }


    public void BtnContinue() {
        if (!formValidation())
            return;
        SaveDraft();
        if (UpdateDB()) {
            startActivity(new Intent(this, SectionB_tool_2Activity.class));
        } else {
            Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean UpdateDB() {
        return true;
    }

    private void SaveDraft() {
    }

    private boolean formValidation() {
        return true;
    }

    public void BtnEnd() {
//        MainApp.endActivity(this, this, EndingActivity.class, false, InfoActivity.fc);
    }
}
