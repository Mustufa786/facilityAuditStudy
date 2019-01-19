package com.example.hassannaqvi.fas.ui.tool2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hassannaqvi.fas.R;
import com.example.hassannaqvi.fas.databinding.ActivitySectionBTool2Binding;

import org.json.JSONObject;

public class SectionB_tool_2Activity extends AppCompatActivity {


    ActivitySectionBTool2Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b_tool_2);
        bi.setCallback(this);
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

        JSONObject s02 = new JSONObject();


        s02.put("fas01a00",
                bi.fas02a00a.isChecked() ? "1"
                        : bi.fas02a00b.isChecked() ? "2"
                        : bi.fas02a00c.isChecked() ? "3"
                        : bi.fas02a00d.isChecked() ? "4" : "0");

    }

    private boolean formValidation() {
        return true;
    }

    public void BtnEnd() {
//        MainApp.endActivity(this, this, EndingActivity.class, false, InfoActivity.fc);
    }
}
